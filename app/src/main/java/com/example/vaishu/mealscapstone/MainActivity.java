package com.example.vaishu.mealscapstone;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vaishu.mealscapstone.Roomdatabase.Mydatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText name;
    @BindView(R.id.password)
    EditText userpassword;
    String email,stringpassword;
    FirebaseAuth firebaseAuth;
    ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
           firebaseAuth=FirebaseAuth.getInstance();

            if(firebaseAuth.getCurrentUser()!=null)
            {
                Intent i=new Intent(this,AreaActivity.class);
                startActivity(i);
                finish();
            }
    }


    public void signin(View view) {
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()==NetworkInfo.State.CONNECTED) {
            email = name.getText().toString();
            stringpassword = userpassword.getText().toString();
            if (email.length() == 0 && stringpassword.length() == 0) {
                Toast.makeText(this, getResources().getString(R.string.data), Toast.LENGTH_SHORT).show();
            }
            else if(email.length()==0 || stringpassword.length()==0) {
                Toast.makeText(this, getResources().getString(R.string.data), Toast.LENGTH_SHORT).show();
            }
            else {
                firebaseAuth.signInWithEmailAndPassword(email, stringpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (stringpassword.length() < 6) {
                                userpassword.setError(getResources().getString(R.string.length));
                            }
                            Toast.makeText(MainActivity.this, getResources().getString(R.string.fail), Toast.LENGTH_SHORT).show();
                        } else {
                            Intent i = new Intent(MainActivity.this, AreaActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                });
            }
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.internet), Toast.LENGTH_SHORT).show();

        }


        }



    public void signup(View view) {
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()== NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()==NetworkInfo.State.CONNECTED) {

            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.internet), Toast.LENGTH_SHORT).show();

        }


    }


    }

