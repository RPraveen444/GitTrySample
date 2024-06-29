package org.example.models;

public class Suppliers {

    private int supplier_id;
    private String supplier_name;
    private String contact_name;
    private String address;
    private String city;
    private String postal_code;
    private String country;
    private String phone;

    public Suppliers(int suppier_id, String supplier_name, String contact_name, String address, String city, String postal_code, String country, String phone) {
        this.supplier_id = suppier_id;
        this.supplier_name = supplier_name;
        this.contact_name = contact_name;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.phone = phone;
    }

    public Suppliers(){

    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int suppier_id) {
        this.supplier_id = suppier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}