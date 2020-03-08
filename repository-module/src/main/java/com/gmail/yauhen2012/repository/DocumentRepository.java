package com.gmail.yauhen2012.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.gmail.yauhen2012.repository.model.Document;

public interface DocumentRepository extends GeneralRepository<Document> {

    Document findById(Connection connection, Integer id) throws SQLException;

}
