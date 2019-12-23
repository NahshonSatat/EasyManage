package com.example.easymanage.LoginFlow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easymanage.DataBase.DataBase;
import com.example.easymanage.Order;
import com.example.easymanage.Product;
import com.example.easymanage.R;
import com.example.easymanage.Supplier.HomePage;
import com.example.easymanage.TAGS;
import com.example.easymanage.Type;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String password = "", email = "";
    public static  FirebaseUser user  = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
    }




    public void Login(View view) {
        if (!isValidEmail(getEmail())) {
            Toast.makeText(getApplicationContext(), "invalid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(getEmail(), getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("[INFO]", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_SHORT).show();
                            Log.w("[ERROR]", "signInWithEmail:failure", task.getException());

                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

        HashMap<String,String> info = new HashMap<>();
        info.put("from","israel");
        info.put("to","italy");
        info.put("TEST","TEST");
        info.put("description","MacBook Pro screen protector");

        DataBase.insertOrder(new Order("DanielAbergel","UrielAbergel",user.getUid(),"1234",info));
//          public Product(String UID ,String supplierID , String productName , HashMap<String,String> info)
        DataBase.insertProduct(new Product("1234",user.getUid(),"DanielAbergel" ,info));


        Log.d(TAGS.INFO,"" + "Supplier");



        this.user = user;
        Intent intent = new Intent(getApplicationContext(), HomePage.class);
        startActivity(intent);
    }

    private String getEmail() {
        return email = ((EditText) (findViewById(R.id.EmailLogin))).getText().toString();
    }

    private String getPassword() {
        return password = ((EditText) (findViewById(R.id.PasswordLogin))).getText().toString();
    }
    public void onRegisterClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }
    public void onforgotPasswordClick(View view) {
        Intent intent = new Intent(getApplicationContext(), forgotPassword.class);
        startActivity(intent);
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }




}

