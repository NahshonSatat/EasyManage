package com.example.easymanage;

import java.util.HashMap;

public class Product {

    private String UID ;
    private String supplierID ;
    private String productID ;
    private HashMap<String,String> info;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public HashMap<String, String> getInfo() {
        return info;
    }

    public void setInfo(HashMap<String, String> info) {
        this.info = info;
    }
}
