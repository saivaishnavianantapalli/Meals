package com.example.vaishu.mealscapstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedbackActivity extends AppCompatActivity {
    @BindView(R.id.feedback)
    EditText feed;
    String userfeeedback;
    DatabaseReference databaseReference;
    @BindView(R.id.list)
    ListView listView;
    List<Feedback> feedbackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        feedbackList=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("review");
    }

    public void submit(View view) {
        addreview();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                feedbackList.clear();
                    for(DataSnapshot reviewsnapshot : dataSnapshot.getChildren())
                    {
                        Feedback feedback=reviewsnapshot.getValue(Feedback.class);
                        feedbackList.add(feedback);
                    }
                    Reviewlist adapter=new Reviewlist(FeedbackActivity.this,feedbackList);
                    listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void addreview()
    {
        userfeeedback=feed.getText().toString().trim();
        if(!TextUtils.isEmpty(userfeeedback))
        {
              String id=databaseReference.push().getKey();
              Feedback feedback=new Feedback(id,userfeeedback);
              databaseReference.child(id).setValue(feedback);
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.feed), Toast.LENGTH_SHORT).show();
        }
    }
}
