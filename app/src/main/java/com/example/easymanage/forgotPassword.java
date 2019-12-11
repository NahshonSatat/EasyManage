package com.example.easymanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgotPassword extends AppCompatActivity {

    private FirebaseAuth mAuth ;
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
    }

    public void method(View view)
    {
       if(isValidEmail(getEmail())) {
           Log.d(TAGS.INFO,getEmail());
           Task<Void> task  = mAuth.sendPasswordResetEmail(getEmail());
           boolean Succeed = task.isSuccessful();
           if(Succeed)
           {
               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
               startActivity(intent);
           }
       }
       else Toast.makeText(getApplicationContext(), "invalid Email address", Toast.LENGTH_SHORT).show();



    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private String getEmail() {
        return email = ((EditText) (findViewById(R.id.emailForgotPassword))).getText().toString();
    }
}
