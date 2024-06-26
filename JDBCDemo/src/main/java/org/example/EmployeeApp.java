package org.example;

import org.example.Service.EmployeeService;
import org.example.model.Employees;

public class EmployeeApp {
    public void getEmployeeName(){
        EmployeeService service = new EmployeeService();

        for(Employees s : service.getAllEmployees()){
//            System.out.println(s.getFirst_name());
        }
    }

    public String updateEmp(){
        EmployeeService service = new EmployeeService();
        String return_val = service.updateEmployee();
        return return_val;
    }

    public void createEmployee(){
        EmployeeService service = new EmployeeService();
        service.addEmployee();
    }

    public void deleteEmployee(){
        EmployeeService service = new EmployeeService();
        service.deleteEmployee();
    }
}
