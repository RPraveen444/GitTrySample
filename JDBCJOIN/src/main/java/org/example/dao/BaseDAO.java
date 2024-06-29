package org.example.dao;

import java.util.List;

public interface BaseDAO<T> {
    public T create(T t);
    public boolean delete(int id);
    public T update(T t);
    public T getId(int id);
    public List<T> getAll();

}