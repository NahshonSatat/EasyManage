package com.example.easymanage.Supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class OrderView extends AppCompatActivity {


    Order _order ;
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view);
        Order order = (Order) getIntent().getSerializableExtra("Order");
        _order = order ;
        SetProductName(_order);
        SetOrderDescription(_order);
        SetOrderID(_order);
    }




    public void SetProductName(Order order){
        final TextView ProductName = (TextView)findViewById(R.id.ProductNameOrderView);
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
        TextView ProductDescription = (TextView)findViewById(R.id.ProductDescriptionOrderView);
        Map<String,String> map = order.getInfo();
        StringBuilder Description = new StringBuilder();
        for (Map.Entry<String,String> entry : map.entrySet()){
            Description.append("" + entry.getKey() + " " + entry.getValue() + "\n");
        }

        ProductDescription.setText(Description.toString());
    }
    public void SetOrderID(Order order){
        TextView ProductPrice = (TextView)findViewById(R.id.PriceOrderView);
        ProductPrice.setText(order.getUID());
    }

//    public void onClickDelete(View view){
//        DataBase.DeletingProduct(_product);
//        finish();
//    }
}
