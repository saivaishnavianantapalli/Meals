package com.example.vaishu.mealscapstone;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vaishu on 13-01-2019.
 */

public class Reviewlist extends ArrayAdapter<Feedback> {
    private Activity context;
    private List<Feedback> feedbackList;
    public Reviewlist(Activity context,List<Feedback> feedbackList)
    {
            super(context,R.layout.reviewlist,feedbackList);
            this.context=context;
            this.feedbackList=feedbackList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.reviewlist,null,true);
        TextView textView=view.findViewById(R.id.userreview);
        Feedback feedback=feedbackList.get(position);
        textView.setText(feedback.getFeedback());
        return view;
    }
}
