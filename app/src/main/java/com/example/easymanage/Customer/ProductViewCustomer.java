package com.example.easymanage.Customer;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easymanage.DataBase.DataBase;
import com.example.easymanage.LoginFlow.MainActivity;
import com.example.easymanage.Order;
import com.example.easymanage.Product;
import com.example.easymanage.R;

import java.util.HashMap;
import java.util.UUID;


public class ProductViewCustomer extends AppCompatActivity {
    public AlertDialog dialog;
    Product _product ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view_customer);
        Product product = (Product) getIntent().getSerializableExtra("Product");
        _product = product ;
        SetProductName(product);
        SetProductDescription(product);
        SetProductPrice(product);
    }
    public void SetProductName(Product product){
        TextView ProductName = (TextView)findViewById(R.id.ProductNameProductViewCustomer);
        ProductName.setText(product.getProductName());
    }
    public void SetProductDescription(Product product){
        TextView ProductDescription = (TextView)findViewById(R.id.ProductDescriptionProductViewCustomer);
        ProductDescription.setText(product.getDescription());
    }
    public void SetProductPrice(Product product){
        TextView ProductPrice = (TextView)findViewById(R.id.PriceProductViewCustomer);
        ProductPrice.setText(product.getInfo().get("price"));
    }

    public void onClickCheckOut(View view){
        //String UID,String userID , String supplierID ,String productID,HashMap<String,String> info
        dialog = new AlertDialog.Builder(this)
                .setTitle("orders")
                .setMessage("are you Sure you want to make this order ?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        HashMap<String,String> info = new HashMap<>();
                        info.put("from","israel");
                        info.put("to","israel");
                        DataBase.insertOrder(new Order(UUID.randomUUID().toString(), MainActivity.user.getUid(),_product.getSupplierID(),_product.getuid(),info));
                        finish();
                    }
                })
                .setNegativeButton("cancel", null)
                .show();
    }

}
