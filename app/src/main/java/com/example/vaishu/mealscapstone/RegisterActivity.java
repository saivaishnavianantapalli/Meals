package com.example.vaishu.mealscapstone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
@BindView(R.id.username)
    EditText name;
@BindView(R.id.password)
EditText userpassword;
String email,stringpassword;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void signup(View view) {
        email = name.getText().toString();
        stringpassword = userpassword.getText().toString();
        if (email.length() == 0 && stringpassword.length() == 0) {
            Toast.makeText(this, getResources().getString(R.string.data), Toast.LENGTH_SHORT).show();
        } else if (email.length() == 0 || stringpassword.length() == 0) {
            Toast.makeText(this, getResources().getString(R.string.data), Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, stringpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        if (stringpassword.length() < 6) {
                            userpassword.setError(getResources().getString(R.string.length));
                        }
                        Toast.makeText(RegisterActivity.this, getResources().getString(R.string.regfail), Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(RegisterActivity.this, AreaActivity.class);
                        startActivity(i);
                        finish();
                    }

                }
            });
        }
    }
}
