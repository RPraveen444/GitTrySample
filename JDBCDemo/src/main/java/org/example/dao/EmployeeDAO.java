package org.example.dao;

import org.example.model.Employees;

import java.util.List;

public interface EmployeeDAO<T> {
    public Employees create(Employees employee);

    public boolean delete(int id);

    public String update(String s1,int num);

    public T getId (String id);

    public List<Employees> getAll();

}
