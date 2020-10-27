package model.dao;

import java.util.List;

public interface GenericDao<T> extends AutoCloseable{
    void create (T entity) throws Exception;
    T findById(int id) throws Exception;
    List<T> findAll() throws Exception;
    void update(T entity) throws Exception;
    void delete(int id) throws Exception;
}
