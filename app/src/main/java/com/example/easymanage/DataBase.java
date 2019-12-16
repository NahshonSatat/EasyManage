package com.example.easymanage;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static final DataBase ourInstance = new DataBase();
    private static  FirebaseDatabase database = FirebaseDatabase.getInstance(); ;
    public static DataBase getInstance() {
        return ourInstance;
    }
    private DataBase() {

    }
    public static void insertUser(String UID , String name , String Email ,Type type )
    {

        DatabaseReference myRef = database.getReference("users");
        myRef.child(UID) ;
        myRef = myRef.child(UID);
        myRef.child("Name").setValue(name);
        myRef.child("Email").setValue(Email);
        myRef.child("Type").setValue(type);
        Log.d(TAGS.INFO,"Inserting a new user to database  / Values : UID " + UID + "Name : " + name  +  " Email : " + Email + " Type : "  + type  );

    }

    public static void insertOrder(String UID , String userID , String supplierID , String productID , HashMap<String,String> info)
    {

        DatabaseReference myRef = database.getReference("orders");
        myRef.child(UID) ;
        myRef = myRef.child(UID);
        myRef.child("Product ID").setValue(productID);
        myRef.child("SupplierID").setValue(supplierID);
        myRef.child("UserID").setValue(userID);
        myRef = myRef.child("Additional information");
        for (Map.Entry<String, String> entry : info.entrySet()) {
           myRef.child(entry.getKey()).setValue(entry.getValue());
        }
        Log.d(TAGS.INFO,"Inserting a new order to database  / Values : UID " + UID + "userID : " + userID  +  " supploerID : " + supplierID + " productID : "  + productID   );
    }

}
