package com.example.vaishu.mealscapstone;

import android.app.ActionBar;
import android.app.LoaderManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vaishu.mealscapstone.Roomdatabase.Favmeal;
import com.example.vaishu.mealscapstone.Roomdatabase.Mydatabase;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    @BindView(R.id.arearecyclerview)
    RecyclerView arearecycler;
    String area;
    int id;
    FirebaseAuth firebaseAuth;
    ArrayList<Pojo> arrayList;
    Mydatabase mydatabase;
    List<Favmeal> list1;
    MealModel mealModel;
    FavouritesAdapter favouritesAdapter;
    ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        ButterKnife.bind(this);
        firebaseAuth=FirebaseAuth.getInstance();
        arearecycler.setLayoutManager(new LinearLayoutManager(this));
        connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()==NetworkInfo.State.CONNECTED) {
            getLoaderManager().initLoader(1, null, this);
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.internet), Toast.LENGTH_SHORT).show();

        }


    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<String>(this) {
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Override
            public String loadInBackground() {
                try {
                    URL url=new URL(BuildConfig.API_KEY);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    httpURLConnection.setRequestMethod("GET");
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    return stringBuilder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        arrayList=new ArrayList<>();
        try {
            JSONObject root=new JSONObject(data);
            JSONArray meals=root.getJSONArray(getResources().getString(R.string.meals));
            for(int i=0;i<meals.length();i++)
            {
                JSONObject index=meals.getJSONObject(i);
                area=index.getString(getResources().getString(R.string.strArea));
                Pojo pojo=new Pojo(area);
                arrayList.add(pojo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        arearecycler.setAdapter(new AreaAdapter(this,arrayList));

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        id=item.getItemId();
        if(id==R.id.favourite)
        {
            fav();
        }

        if(id==R.id.logout) {
            firebaseAuth.signOut();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void fav() {
        Intent intent=new Intent(this,FavouritesActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
