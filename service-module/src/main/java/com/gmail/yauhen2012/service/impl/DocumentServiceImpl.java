package com.gmail.yauhen2012.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.gmail.yauhen2012.repository.ConnectionRepository;
import com.gmail.yauhen2012.repository.DocumentRepository;
import com.gmail.yauhen2012.repository.model.Document;
import com.gmail.yauhen2012.service.DocumentService;
import com.gmail.yauhen2012.service.model.AddDocumentDTO;
import com.gmail.yauhen2012.service.model.DocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private ConnectionRepository connectionRepository;
    private DocumentRepository documentRepository;

    public DocumentServiceImpl(ConnectionRepository connectionRepository, DocumentRepository documentRepository) {
        this.connectionRepository = connectionRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public void addDocument(AddDocumentDTO addDocumentDTO) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Document toDatabaseDocument = convertObjectDTOToDatabaseObject(addDocumentDTO);
                documentRepository.add(connection, toDatabaseDocument);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public int deleteDocumentById(Integer id) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                int affectedRows = documentRepository.delete(connection, id);
                connection.commit();
                return affectedRows;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return 0;
    }

    @Override
    public DocumentDTO findDocumentById(Integer id) {
        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Document document = documentRepository.findById(connection, id);
                DocumentDTO documentDTO = convertDatabaseObjectToDTO(document);
                connection.commit();
                return documentDTO;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<DocumentDTO> findAll() {

        try (Connection connection = connectionRepository.getConnection()) {
            connection.setAutoCommit(false);
            try {
                List<Document> documentList = documentRepository.findAll(connection);
                List<DocumentDTO> documentDTOList = documentList.stream()
                        .map(this::convertDatabaseObjectToDTO)
                        .collect(Collectors.toList());
                connection.commit();
                return documentDTOList;
            } catch (SQLException e) {
                connection.rollback();
                logger.error(e.getMessage(), e);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();

    }

    private DocumentDTO convertDatabaseObjectToDTO(Document document) {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(document.getId());
        documentDTO.setUniqueNumber(document.getUniqueNumber());
        documentDTO.setDescription(document.getDescription());
        return documentDTO;
    }

    private Document convertObjectDTOToDatabaseObject(AddDocumentDTO addDocumentDTO) {
        Document toDatabaseDocument = new Document();
        toDatabaseDocument.setUniqueNumber(UUID.randomUUID().toString());
        toDatabaseDocument.setDescription(addDocumentDTO.getDescription());
        return toDatabaseDocument;
    }

}
