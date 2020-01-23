package ru.vsu.touristagency.persistance;

public interface IDBMapper<R, T> {
    R map(T data);
}
