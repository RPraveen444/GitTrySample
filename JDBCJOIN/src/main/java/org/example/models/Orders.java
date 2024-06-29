package org.example.models;

import java.util.List;

public class Orders {
    private int order_id;
    private Customers customer;
    private int employee_id;
    private String order_date;
    private int shipper_id;


    public Orders(int order_id, Customers customer, int employee_id, String order_date, int shipper_id) {
        this.order_id = order_id;
        this.customer = customer;
        this.employee_id = employee_id;
        this.order_date = order_date;
        this.shipper_id = shipper_id;
    }

    public Orders(){

    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public int getEmployee() {
        return employee_id;
    }

    public void setEmployee(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getShipper() {
        return shipper_id;
    }

    public void setShipper(int shipper_id) {
        this.shipper_id = shipper_id;
    }
}