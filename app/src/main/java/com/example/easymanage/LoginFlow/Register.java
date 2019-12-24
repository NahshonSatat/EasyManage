package com.example.easymanage.LoginFlow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easymanage.DataBase.DataBase;
import com.example.easymanage.R;
import com.example.easymanage.TAGS;
import com.example.easymanage.Type;
import com.example.easymanage.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String email = "" ,password = "" , name = "" , lastName = "";
    Type type  = Type.Supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mAuth = FirebaseAuth.getInstance();
    }


    public void registerUser(View view)
    {
        if (!MainActivity.isValidEmail(getEmail())) {
            Toast.makeText(getApplicationContext(), "invalid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!getPassword().equals(getPasswordRtype())){
            Toast.makeText(getApplicationContext(), "The password isn't the same , Please enter password again", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(getEmail(), getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAGS.INFO, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            DataBase.insertUser(new User(user.getEmail(),user.getUid(),type.toString(),getName(),getLastName()));
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAGS.ERROR, "createUserWithEmail:failure", task.getException());

                        }
                    }
                });
    }

    public String getEmail() {
        return email = ((EditText) (findViewById(R.id.emailForgotPassword))).getText().toString();
    }
    public String getName() {
        name = ((EditText) (findViewById(R.id.RegisterName))).getText().toString();
        return name ;
    }
    public String getLastName(){
        lastName = ((EditText) (findViewById(R.id.RegisterLastName))).getText().toString();
        return lastName;
    }

    public String getPassword() {
        return password = ((EditText) (findViewById(R.id.passwordRegister))).getText().toString();
    }
    public String getPasswordRtype() {
        return password = ((EditText) (findViewById(R.id.passwordRegisterR))).getText().toString();
    }
    public void supplierButton(View view){
        type = Type.Supplier ;
    }
    public void customerButton(View view){
        type = Type.Customer ;
    }
    public void managerButton(View view){
        type = Type.Manager ;
    }




}
