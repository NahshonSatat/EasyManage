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
import android.app.AlertDialog;
import android.content.DialogInterface;
public class MakeNewProduct extends AppCompatActivity {

    public  AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_new_product);
    }


    public void makeNewOrder(View view){
        dialog = new AlertDialog.Builder(MakeNewProduct.this)
                .setTitle("products")
                .setMessage("are you Sure you want to make this product ?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        makeNewOrder();
                        finish();
                    }
                })
                .setNegativeButton("cancel", null)
                .show();

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
