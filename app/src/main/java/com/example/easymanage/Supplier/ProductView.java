package com.example.easymanage.Supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.easymanage.DataBase.DataBase;
import com.example.easymanage.Product;
import com.example.easymanage.R;


public class ProductView extends AppCompatActivity {

    Product _product ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Product product = (Product) getIntent().getSerializableExtra("Product");
        _product = product ;
        SetProductName(product);
        SetProductDescription(product);
        SetProductPrice(product);
    }
    public void SetProductName(Product product){
        TextView ProductName = (TextView)findViewById(R.id.ProductNameProductView);
        ProductName.setText(product.getProductName());
    }
    public void SetProductDescription(Product product){
        TextView ProductDescription = (TextView)findViewById(R.id.ProductDescriptionProductView);
        ProductDescription.setText(product.getDescription());
    }
    public void SetProductPrice(Product product){
        TextView ProductPrice = (TextView)findViewById(R.id.PriceProductView);
        ProductPrice.setText(product.getInfo().get("price"));
    }

    public void onClickDelete(View view){
        DataBase.DeletingProduct(_product);
        finish();
    }

}
