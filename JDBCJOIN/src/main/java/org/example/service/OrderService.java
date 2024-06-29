package org.example.service;

import org.example.dao.OrderDAO;
import org.example.models.OrderDetails;

public class OrderService {
    OrderDAO dao;

    public OrderService() {
        dao = new OrderDAO();
    }

    public void getAllCustomerOrders(){
        for(OrderDetails od : dao.getAllCustomerOrders(5)){
            System.out.println(od.getProducts().getProduct_name() + " " + od.getQuantity());
        }
    }

}