package com.example.vaishu.mealscapstone;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    @BindView(R.id.mealrecyclerview)
    RecyclerView mealsrecycler;
    String area;
    String name,image;
    ArrayList<Pojo> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        ButterKnife.bind(this);
        area=getIntent().getStringExtra(getResources().getString(R.string.strArea));
        mealsrecycler.setLayoutManager(new LinearLayoutManager(this));
        getLoaderManager().initLoader(2,null,this);
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
                    URL url=new URL(BuildConfig.API_KEYAREA+area);
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
                name=index.getString(getResources().getString(R.string.strMeal));
                image=index.getString(getResources().getString(R.string.strMealThumb));
                Pojo pojo=new Pojo(name,image);
                arrayList.add(pojo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
            mealsrecycler.setAdapter(new MealsAdapter(this,arrayList));
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,AreaActivity.class);
        startActivity(intent);
    }
}
