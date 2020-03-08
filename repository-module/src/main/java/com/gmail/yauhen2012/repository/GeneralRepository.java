package com.gmail.yauhen2012.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GeneralRepository<T> {

    T add(Connection connection, T shop) throws SQLException;

    int delete(Connection connection, Integer id) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

}
