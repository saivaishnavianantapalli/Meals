package com.example.vaishu.mealscapstone;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsActivity extends AppCompatActivity {
    @BindView(R.id.textview1)
    TextView ingredient1;
    @BindView(R.id.textview2)
    TextView ingredient2;
    @BindView(R.id.textview3)
    TextView ingredient3;
    @BindView(R.id.textview4)
    TextView ingredient4;
    @BindView(R.id.textview5)
    TextView ingredient5;
    @BindView(R.id.textview6)
    TextView ingredient6;
    String ing1,ing2,ing3,ing4,ing5,ing6;
    String meas1,meas2,meas3,meas4,meas5,meas6;
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        ButterKnife.bind(this);
        stringBuilder=new StringBuilder();
        ing1=getIntent().getStringExtra(getResources().getString(R.string.strIngredient1));
        ing2=getIntent().getStringExtra(getResources().getString(R.string.strIngredient2));
        ing3=getIntent().getStringExtra(getResources().getString(R.string.strIngredient3));
        ing4=getIntent().getStringExtra(getResources().getString(R.string.strIngredient4));
        ing5=getIntent().getStringExtra(getResources().getString(R.string.strIngredient5));
        ing6=getIntent().getStringExtra(getResources().getString(R.string.strIngredient6));
        meas1=getIntent().getStringExtra(getResources().getString(R.string.strMeasure1));
        meas2=getIntent().getStringExtra(getResources().getString(R.string.strMeasure2));
        meas3=getIntent().getStringExtra(getResources().getString(R.string.strMeasure3));
        meas4=getIntent().getStringExtra(getResources().getString(R.string.strMeasure4));
        meas5=getIntent().getStringExtra(getResources().getString(R.string.strMeasure5));
        meas6=getIntent().getStringExtra(getResources().getString(R.string.strMeasure6));
        ingredient1.setText(ing1+"  "+meas1);
        ingredient2.setText(ing2+"  "+meas2);
        ingredient3.setText(ing3+"  "+meas3);
        ingredient4.setText(ing4+"  "+meas4);
        ingredient5.setText(ing5+"  "+meas5);
        ingredient6.setText(ing6+"  "+meas6);
        SharedPreferences sharedPreferences=getSharedPreferences("pref",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        stringBuilder.append(ing1+"  "+meas1+"\n"+ing2+"  "+meas2+"\n"+ing3+"  "+meas3+"\n"+ing4+"  "+meas4+"\n"+ing5+"  "+meas5+"\n"+ing6+"  "+meas6);
        editor.putString("string",stringBuilder.toString());
        editor.commit();
        Intent i=new Intent(this,NewAppWidget.class);
        i.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int[] widgets= AppWidgetManager.getInstance(this).getAppWidgetIds(new ComponentName(this,NewAppWidget.class));
        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,widgets);
        sendBroadcast(i);
    }
}
