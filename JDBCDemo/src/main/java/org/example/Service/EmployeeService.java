package org.example.Service;

import org.example.dao.EmployeeDAOImpl;
import org.example.model.Employees;

import java.util.List;

public class EmployeeService {
    EmployeeDAOImpl dao;

    public EmployeeService(){
        this.dao = new EmployeeDAOImpl();
    }
    public List<Employees> getAllEmployees(){
        List<Employees> employees = dao.getAll();
        for(Employees s : employees){
            System.out.println(s.getFirst_name() +" "+s.getLast_name());
        }

        return employees;
    }

    public String updateEmployee(){
        String return_val = dao.update("praveen",1);
        return return_val;
    }

    public  void addEmployee(){
        Employees employee = new Employees(11,"Akash","A","1955-03-04","EmpID908.pic","Akash is a developer");
        System.out.println(dao.create(employee).getFirst_name());
    }

    public  void deleteEmployee(){
        System.out.println(dao.delete(11));
    }
}
