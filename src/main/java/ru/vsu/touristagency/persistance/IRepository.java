package ru.vsu.touristagency.persistance;

import java.util.List;

public interface IRepository <T> {
    T find(Long id);
    List<T> findAll();
    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
}
