package com.example.easymanage.DataBase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.easymanage.Order;
import com.example.easymanage.Product;
import com.example.easymanage.TAGS;
import com.example.easymanage.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DataBase {
    private static final DataBase ourInstance = new DataBase();
    private static  FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DataBase getInstance() {
        return ourInstance;
    }
    private DataBase() {

    }

    // --------------------------------- Inserting Data --------------------------------------------


    public static void insertUser(User user)
    {
        DatabaseReference myRef = database.getReference("users");
        myRef.child(user.getUID()).setValue(user);
        Log.d(TAGS.INFO,"Inserting a new user to database  / Values : UID " + user.getUID()+ "Name : " + user.getName()  +  " Email : " + user.getEmail() + " Type : "  + user.getType()  );
    }

    public static void insertOrder(Order order)
    {

        DatabaseReference myRef = database.getReference("orders");
        myRef.child(order.getUID()).setValue(order);
        myRef = database.getReference("supplier_orders");
        myRef.child(order.getSupplierID()).child(order.getUID()).setValue(order);
        myRef = database.getReference("customer_orders");
        myRef.child(order.getUserID()).child(order.getUID()).setValue(order);

        Log.d(TAGS.INFO,"Inserting a new order to database  / Values : UID " +
                order.getUID() + "userID : " + order.getUserID()
                + " supploerID : " + order.getSupplierID()
                + " productID : "  + order.getProductID());
    }
    public static void insertProduct(Product product)
    {
        DatabaseReference myRef = database.getReference("products");
        myRef.child(product.getuid()).setValue(product);
        myRef = database.getReference("supplier_products");
        myRef.child(product.getSupplierID()).child(product.getuid()).setValue(product);

        Log.d(TAGS.INFO,"Inserting a new product to database  / Values : UID "
                + product.getuid() +   " supploerID : "
                + product.getSupplierID() + " productName : "
                + product.getProductName()   );

    }
    // --------------------------------- Deleting Data --------------------------------------------
    public static void DeletingProduct(Product product){

        DatabaseReference myRef = database.getReference("products").child(product.getuid());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot _dataSnapshot : dataSnapshot.getChildren())
                {
                    _dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef = database.getReference("supplier_products").child(product.getSupplierID()).child(product.getuid());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot _dataSnapshot : dataSnapshot.getChildren())
                {
                    _dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static void DeletingOrder(Order order) {
        DatabaseReference myRef = database.getReference("orders").child(order.getUID());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot _dataSnapshot : dataSnapshot.getChildren())
                {
                    _dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef = database.getReference("supplier_orders").child(order.getSupplierID()).child(order.getUID());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot _dataSnapshot : dataSnapshot.getChildren())
                {
                    _dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef = database.getReference("customer_orders").child(order.getUserID()).child(order.getUID());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot _dataSnapshot : dataSnapshot.getChildren())
                {
                    _dataSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
