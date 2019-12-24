package com.example.easymanage.DataBase;

import android.util.Log;

import com.example.easymanage.Order;
import com.example.easymanage.Product;
import com.example.easymanage.TAGS;
import com.example.easymanage.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DataBase {
    private static final DataBase ourInstance = new DataBase();
    private static  FirebaseDatabase database = FirebaseDatabase.getInstance(); ;
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
        myRef.child(product.getUID()).setValue(product);
        myRef = database.getReference("supplier_products");
        myRef.child(product.getSupplierID()).child(product.getUID()).setValue(product);

        Log.d(TAGS.INFO,"Inserting a new product to database  / Values : UID "
                + product.getUID() +   " supploerID : "
                + product.getSupplierID() + " productName : "
                + product.getProductName()   );

    }
}
