package sample.Repository;

import sample.entity.Account;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Vector;

interface GenericRepository<T> {
    void insertData(T data) throws SQLException;

    public void deleteData(String id) throws SQLException;

    public void updateData(String id) throws SQLException;

    public Vector<T> getAll() throws SQLException;

}
