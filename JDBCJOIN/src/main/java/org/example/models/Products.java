package org.example.models;

public class Products {
    private int product_id;
    private String product_name;
    private  Suppliers supplier;
    private Category category;
    private String unit;
    private double price;


    public Products(){

    }

    public Products(int product_id, String product_name, Suppliers supplier, Category category, String unit, double price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.supplier = supplier;
        this.category = category;
        this.unit = unit;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}