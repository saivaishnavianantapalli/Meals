package com.example.vaishu.mealscapstone;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class DetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    @BindView(R.id.detailsrecycler)
    RecyclerView detailsrecyclerview;
    String name;
    String id,mealname,category,mealimage;
    String ingredient1,ingredient2,ingredient3,ingredient4,ingredient5,ingredient6;
    String measure1,measure2,measure3,measure4,measure5,measure6;
    String instructions,source,link;
    ArrayList<Pojo> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        name=getIntent().getStringExtra(getResources().getString(R.string.strMeal));
        detailsrecyclerview.setLayoutManager(new LinearLayoutManager(this));
        getLoaderManager().initLoader(3,null,this);
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
                    URL url=new URL(BuildConfig.API_KEYMEAL+name);
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
                id=index.getString(getResources().getString(R.string.idMeal));
                mealname=index.getString(getResources().getString(R.string.strMeal));
                mealimage=index.getString(getResources().getString(R.string.strMealThumb));
                category=index.getString(getResources().getString(R.string.strCategory));
                ingredient1=index.getString(getResources().getString(R.string.strIngredient1));
                ingredient2=index.getString(getResources().getString(R.string.strIngredient2));
                ingredient3=index.getString(getResources().getString(R.string.strIngredient3));
                ingredient4=index.getString(getResources().getString(R.string.strIngredient4));
                ingredient5=index.getString(getResources().getString(R.string.strIngredient5));
                ingredient6=index.getString(getResources().getString(R.string.strIngredient6));
                measure1=index.getString(getResources().getString(R.string.strMeasure1));
                measure2=index.getString(getResources().getString(R.string.strMeasure2));
                measure3=index.getString(getResources().getString(R.string.strMeasure3));
                measure4=index.getString(getResources().getString(R.string.strMeasure4));
                measure5=index.getString(getResources().getString(R.string.strMeasure5));
                measure6=index.getString(getResources().getString(R.string.strMeasure6));
                instructions=index.getString(getResources().getString(R.string.strInstructions));
                source=index.getString(getResources().getString(R.string.strSource));
                link=index.getString(getResources().getString(R.string.strYoutube));
                Pojo pojo=new Pojo(mealname,mealimage,category,ingredient1,ingredient2,ingredient3,ingredient4,ingredient5,ingredient6,measure1,measure2,measure3,measure4,measure5,measure6,instructions,source,link);
                arrayList.add(pojo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

detailsrecyclerview.setAdapter(new DetailsAdapter(this,arrayList));
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    public void ingredients(View view) {
        Intent intent=new Intent(this,IngredientsActivity.class);
        intent.putExtra(getResources().getString(R.string.strIngredient1),ingredient1);
        intent.putExtra(getResources().getString(R.string.strIngredient2),ingredient2);
        intent.putExtra(getResources().getString(R.string.strIngredient3),ingredient3);
        intent.putExtra(getResources().getString(R.string.strIngredient4),ingredient4);
        intent.putExtra(getResources().getString(R.string.strIngredient5),ingredient5);
        intent.putExtra(getResources().getString(R.string.strIngredient6),ingredient6);
        intent.putExtra(getResources().getString(R.string.strMeasure1),measure1);
        intent.putExtra(getResources().getString(R.string.strMeasure2),measure2);
        intent.putExtra(getResources().getString(R.string.strMeasure3),measure3);
        intent.putExtra(getResources().getString(R.string.strMeasure4),measure4);
        intent.putExtra(getResources().getString(R.string.strMeasure5),measure5);
        intent.putExtra(getResources().getString(R.string.strMeasure6),measure6);
        startActivity(intent);
    }

    public void description(View view) {
        Intent intent=new Intent(this,InstructionsActivity.class);
        intent.putExtra(getResources().getString(R.string.idMeal),id);
        intent.putExtra(getResources().getString(R.string.strMeal),mealname);
        intent.putExtra(getResources().getString(R.string.strMealThumb),mealimage);
        intent.putExtra(getResources().getString(R.string.strInstructions),instructions);
        intent.putExtra(getResources().getString(R.string.strSource),source);
        intent.putExtra(getResources().getString(R.string.strYoutube),link);
        startActivity(intent);
    }

}
