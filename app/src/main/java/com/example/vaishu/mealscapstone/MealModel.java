package com.example.vaishu.mealscapstone;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.vaishu.mealscapstone.Roomdatabase.Favmeal;

import java.util.List;

/**
 * Created by Vaishu on 12-01-2019.
 */

public class MealModel extends ViewModel {
    MutableLiveData<List<Favmeal>> list1;
    public MealModel()
    {
            list1=new MutableLiveData<>();
    }

    public MealModel(MutableLiveData<List<Favmeal>> list) {
        this.list1 = list;
    }
    public MutableLiveData<List<Favmeal>> getList()
    {
        if(list1==null)
        {
            list1=new MutableLiveData<>();
        }
        return list1;
    }

    public void setList(List<Favmeal> list) {
        list1.setValue(list);
    }
}
