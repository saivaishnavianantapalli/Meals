package com.example.vaishu.mealscapstone.Roomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Vaishu on 12-01-2019.
 */
@Dao
public interface DAO {
    @Query("select * from myfav")
    List<Favmeal> getAllData();
    @Insert
    void insert(Favmeal fm);
    @Delete
    void delete(Favmeal fm);
}
