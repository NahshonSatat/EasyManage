package com.example.easymanage.Supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import com.example.easymanage.DataBase.DataBase;
import com.example.easymanage.LoginFlow.MainActivity;
import com.example.easymanage.Product;
import com.example.easymanage.R;

import java.util.HashMap;
import java.util.UUID;

public class MakeNewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_product);
    }


    public void makeNewOrder(View view){
        makeNewOrder();
    }
    public void makeNewOrder(){
        DataBase.insertProduct(new Product(getUID(),supplierID(),productName(),description(),getPrice()));
    }
    private String getUID(){
        return UUID.randomUUID().toString();
    }
    private String supplierID(){
        return MainActivity.user.getUid();
    }
    private String productName(){
        return ((EditText) (findViewById(R.id.productName))).getText().toString();
    }
    private String description(){
       return  ((EditText) (findViewById(R.id.ProductDescription))).getText().toString();
    }
    private HashMap<String,String> getPrice(){
        HashMap<String,String> info = new HashMap<>();
        info.put("price", ((EditText) (findViewById(R.id.priceProduct))).getText().toString());
        return info ;
    }
}
