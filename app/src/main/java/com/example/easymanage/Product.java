package com.example.easymanage;

import java.util.HashMap;

public class Product {

    private String UID ;
    private String supplierID ;
    private String productName ;
    private String description ;
    private HashMap<String,String> info;

    public Product(){}
    public Product(String UID ,String supplierID , String productName, String description , HashMap<String,String> info)
    {
        this.UID = UID ;
        this.supplierID = supplierID ;
        this.productName = productName ;
        this.info = info ;
        this.description = description ;
    }
    public String getUID() {
        return UID;
    }


    public String getSupplierID() {
        return supplierID;
    }

    public HashMap<String, String> getInfo() {
        return info;
    }


    public String getProductName() { return productName; }


    public String getDescription() {
        return description;
    }
}
