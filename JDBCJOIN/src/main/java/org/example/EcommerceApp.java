package org.example;

import org.example.models.Customers;
import org.example.service.CustomerService;
import org.example.service.OrderService;

import java.util.List;

public class EcommerceApp {
    public static void getCustomers(){
        CustomerService service = new CustomerService();
        for(Customers ct: service.getAllCustomers()){
            System.out.println(ct.getCustomer_name());
        }
    }

    public static void createCustomer(){
        CustomerService service = new CustomerService();
        service.addCustomer();
    }

    public static void deleteCustomer(){
        CustomerService service = new CustomerService();
        service.deleteCustomer();
    }

    public static void getAllCustomerOrders(){
        OrderService service = new OrderService();
        service.getAllCustomerOrders();
    }
}