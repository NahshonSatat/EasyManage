package com.example.easymanage.Customer;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easymanage.DataBase.DataBase;
import com.example.easymanage.Order;
import com.example.easymanage.Product;
import com.example.easymanage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class OrderViewCustomer extends AppCompatActivity {


    Order _order ;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_customer);
        Order order = (Order) getIntent().getSerializableExtra("Order");
        _order = order ;
        SetProductName(_order);
        SetOrderDescription(_order);
        SetOrderID(_order);
    }




    public void SetProductName(Order order){
        final TextView ProductName = (TextView)findViewById(R.id.ProductNameOrderViewCustomer);
        DatabaseReference myRef = database.getReference("products").child(order.getProductID());
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Product product = dataSnapshot.getValue(Product.class);
                ProductName.setText( product.getProductName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void SetOrderDescription(Order order){
        TextView ProductDescription = (TextView)findViewById(R.id.ProductDescriptionOrderViewCustomer);
        Map<String,String> map = order.getInfo();
        StringBuilder Description = new StringBuilder();
        for (Map.Entry<String,String> entry : map.entrySet()){
            Description.append("" + entry.getKey() + " " + entry.getValue() + "\n");
        }

        ProductDescription.setText(Description.toString());
    }
    public void SetOrderID(Order order){
        TextView ProductPrice = (TextView)findViewById(R.id.PriceOrderViewCustomer);
        ProductPrice.setText(order.getUID());
    }

    public void onClickDeliverd(View view){
        DataBase.DeletingOrder(_order);
        finish();
    }
}
