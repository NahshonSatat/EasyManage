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

import com.example.easymanage.Customer.HomePageCustomer;
import com.example.easymanage.DataBase.DataBase;
import com.example.easymanage.Order;
import com.example.easymanage.Product;
import com.example.easymanage.R;
import com.example.easymanage.Supplier.HomePage;
import com.example.easymanage.TAGS;
import com.example.easymanage.Type;
import com.example.easymanage.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String password = "", email = "" ,type = "";
    private DatabaseReference DataRefOrders ;
    public static  FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser() ;

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
        this.user = user;
        
        DataRefOrders = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
        DataRefOrders.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                type = user.getType();
                if(type.equals("Supplier"))
                {
                    Intent intent = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(intent);
                }else if(type.equals("Customer")) {
                    Intent intent = new Intent(getApplicationContext(), HomePageCustomer.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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

