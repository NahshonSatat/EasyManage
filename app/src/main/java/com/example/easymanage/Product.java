package com.example.easymanage;

import java.io.Serializable;
import java.util.HashMap;

public class Product implements Serializable {

    private String uid ;
    private String supplierID ;
    private String productName ;
    private String description ;
    private HashMap<String,String> info;

    public Product(){}
    public Product(String uid ,String supplierID , String productName, String description , HashMap<String,String> info)
    {
        this.uid = uid ;
        this.supplierID = supplierID ;
        this.productName = productName ;
        this.info = info ;
        this.description = description ;
    }
    public String getuid() {
        return uid;
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
