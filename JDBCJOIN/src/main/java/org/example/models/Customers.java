package org.example.models;

public class Customers {
    private int customer_id;
    private String customer_name;
    private String contact_name;
    private String address;
    private String city;
    private String postal_code;
    private String country;

    public Customers(int customer_id, String customer_name, String contact_name, String address, String city, String postal_code, String country) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.contact_name = contact_name;
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
    }

    public Customers(){

    }


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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
}