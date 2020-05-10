package com.gmail.yauhen2012.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.yauhen2012.repository.DocumentRepository;
import com.gmail.yauhen2012.repository.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepositoryImpl extends GeneralRepositoryImpl<Document> implements DocumentRepository {

    @Override
    public Document add(Connection connection, Document document) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO documents (unique_number, description) VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, document.getUniqueNumber());
            statement.setString(2, document.getDescription());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating document failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    document.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating document failed, no ID obtained.");
                }
            }
            return document;
        }
    }

    @Override
    public int delete(Connection connection, Integer id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM documents WHERE id=?"
                )
        ) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        }
    }

    @Override
    public Document findById(Connection connection, Integer id) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, unique_number, description FROM documents WHERE id=?"
                )
        ) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return getDocument(rs);
                }
            }
            return null;
        }
    }

    @Override
    public List<Document> findAll(Connection connection) throws SQLException {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, unique_number, description FROM documents"
                )
        ) {
            List<Document> documentList = new ArrayList<>();
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Document document = getDocument(rs);
                    documentList.add(document);
                }
                return documentList;
            }
        }
    }

    private Document getDocument(ResultSet rs) throws SQLException {
        Document document = new Document();
        document.setId(rs.getInt("id"));
        document.setUniqueNumber(rs.getString("unique_number"));
        document.setDescription(rs.getString("description"));
        return document;
    }

}
