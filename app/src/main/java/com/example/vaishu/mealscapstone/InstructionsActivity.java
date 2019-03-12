package com.example.vaishu.mealscapstone;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaishu.mealscapstone.Roomdatabase.Favmeal;
import com.example.vaishu.mealscapstone.Roomdatabase.Mydatabase;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InstructionsActivity extends AppCompatActivity {
    @BindView(R.id.instructions)
    TextView mealinstructions;
    String name,image,source,instructions,link,id;
    @BindView(R.id.like)
    LikeButton likeButton;
    Mydatabase mydatabase;
    List<Favmeal> list1;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        ButterKnife.bind(this);
        name=getIntent().getStringExtra(getResources().getString(R.string.strMeal));
        image=getIntent().getStringExtra(getResources().getString(R.string.strMealThumb));
        source=getIntent().getStringExtra(getResources().getString(R.string.strSource));
        instructions=getIntent().getStringExtra(getResources().getString(R.string.strInstructions));
        link=getIntent().getStringExtra(getResources().getString(R.string.strYoutube));
        id=getIntent().getStringExtra(getResources().getString(R.string.idMeal));
        i=Integer.parseInt(id);
        mealinstructions.setText(instructions);
        final Favmeal favmeal=new Favmeal();
        favmeal.setId(i);
        favmeal.setName(name);
        favmeal.setImage(image);
        mydatabase= Room.databaseBuilder(this,Mydatabase.class,"Favmeal").allowMainThreadQueries().build();
        list1=new ArrayList<>();
        list1=mydatabase.dao().getAllData();
        for(int k=0;k<list1.size();k++)
        {
            if(list1.get(k).getId()==i)
            {
                likeButton.setLiked(true);
            }
        }
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mydatabase.dao().insert(favmeal);

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mydatabase.dao().delete(favmeal);
            }
        });
    }
    public void link(View view) {
        Uri uri=Uri.parse(link);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void source(View view) {
        if(source.length()!=0) {
            Uri uri = Uri.parse(source);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, getResources().getString(R.string.src), Toast.LENGTH_SHORT).show();
        }
    }
    public void review(View view)
    {
        Intent intent=new Intent(this,FeedbackActivity.class);
        startActivity(intent);
    }

}
