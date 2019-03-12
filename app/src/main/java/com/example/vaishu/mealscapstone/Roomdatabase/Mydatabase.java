package com.example.vaishu.mealscapstone.Roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Vaishu on 12-01-2019.
 */
@Database(entities = Favmeal.class,version =2,exportSchema = false)
public abstract class Mydatabase extends RoomDatabase {
    public abstract DAO dao();
}
