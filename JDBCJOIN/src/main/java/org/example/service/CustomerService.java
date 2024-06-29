package org.example.service;

import org.example.dao.CustomerDAO;
import org.example.models.Customers;

import java.util.List;

public class CustomerService {
    CustomerDAO dao ;
    public CustomerService(){
        this.dao = new CustomerDAO();
    }

    public List<Customers> getAllCustomers(){

        List<Customers> customers = dao.getAll();
        return customers;
    }

    public void addCustomer(){
        Customers customer = new Customers(106,"Mr.qwerty","QWERTY","1,chennia,TN","Teynampet","601234","India");
        System.out.println(dao.create(customer).getCustomer_name());
    }

    public void deleteCustomer(){
        System.out.println(dao.delete(105));
    }


}