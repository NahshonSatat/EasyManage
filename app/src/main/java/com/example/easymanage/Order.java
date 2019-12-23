package com.example.easymanage;

import java.util.HashMap;

public class Order {


    private String uid ;
    private String userID ;
    private String supplierID ;
    private String productID ;
    private HashMap<String,String> info;



    public Order(){}

    public Order(String UID,String userID , String supplierID ,String productID,HashMap<String,String> info )
    {
        this.uid = UID ;
        this.userID = userID;
        this.supplierID = supplierID ;
        this.productID = productID ;
        this.info = info ;
    }

    public HashMap<String, String> getInfo() {
        return info;
    }



    public String getProductID() {
        return productID;
    }


    public String getSupplierID() {
        return supplierID;
    }


    public String getUserID() {
        return userID;
    }


    public String getUID() {
        return uid;
    }

}
