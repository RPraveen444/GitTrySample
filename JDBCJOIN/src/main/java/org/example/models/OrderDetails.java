package org.example.models;

public class OrderDetails {
    private int order_detail_id;
    private Orders order;
    private Products products;
    private int quantity;

    public OrderDetails(){}

    public OrderDetails(int order_detail_id, Orders order, Products products, int quantity) {
        this.order_detail_id = order_detail_id;
        this.order = order;
        this.products = products;
        this.quantity = quantity;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}