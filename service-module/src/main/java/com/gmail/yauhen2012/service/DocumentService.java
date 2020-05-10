package com.gmail.yauhen2012.service;

import java.util.List;

import com.gmail.yauhen2012.service.model.AddDocumentDTO;
import com.gmail.yauhen2012.service.model.DocumentDTO;

public interface DocumentService {

    void addDocument(AddDocumentDTO addDocumentDTO);

    int deleteDocumentById(Integer id);

    DocumentDTO findDocumentById(Integer id);

    List<DocumentDTO> findAll();

}
