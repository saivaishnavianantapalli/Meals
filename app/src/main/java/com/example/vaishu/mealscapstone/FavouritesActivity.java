package com.example.vaishu.mealscapstone;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vaishu.mealscapstone.Roomdatabase.Favmeal;
import com.example.vaishu.mealscapstone.Roomdatabase.Mydatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavouritesActivity extends AppCompatActivity {
    @BindView(R.id.favrecycler)
    RecyclerView favoriterecycler;
    Mydatabase mydatabase;
    List<Favmeal> list1;
    MealModel mealModel;
    FavouritesAdapter favouritesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ButterKnife.bind(this);
        favoriterecycler.setLayoutManager(new LinearLayoutManager(this));
        mydatabase = Room.databaseBuilder(this, Mydatabase.class, "Favmeal").allowMainThreadQueries().build();
        mealModel= ViewModelProviders.of(this).get(MealModel.class);
        mealModel= ViewModelProviders.of(this).get(MealModel.class);
        list1 = mydatabase.dao().getAllData();
        mealModel.setList(list1);
        Observer<List<Favmeal>> observer=new Observer<List<Favmeal>>() {
            @Override
            public void onChanged(@Nullable List<Favmeal> favoritemovies) {
                favouritesAdapter=new FavouritesAdapter(FavouritesActivity.this,list1);
                favoriterecycler.setAdapter(favouritesAdapter);

            }
        };
        mealModel.getList().observe(this,observer);
    }
}
