package com.manager.managerfx.model.repositories;

import java.util.List;

public interface IGenericRepository<T>{
    T Get(int id);
    List<T> GetAll();
    void Save(T entity);
    void Update(T entity);
    void Delete(T entity);
}
