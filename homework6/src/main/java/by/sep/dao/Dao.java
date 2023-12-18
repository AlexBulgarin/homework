package by.sep.dao;

import java.io.Serializable;

public interface Dao<T> {
    String create(T t);

    T read(Class<T> clazz, Serializable id);

    boolean update(T t);

    boolean delete(T t);

}
