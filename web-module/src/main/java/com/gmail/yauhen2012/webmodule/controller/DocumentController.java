package com.gmail.yauhen2012.webmodule.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.gmail.yauhen2012.service.DocumentService;
import com.gmail.yauhen2012.service.model.DocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DocumentController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public String getDocumentList(Model model) {
        List<DocumentDTO> documentDTOList = documentService.findAll();
        model.addAttribute("documentList", documentDTOList);
        logger.debug("Get DocumentList method");
        return "documents";
    }

    @GetMapping("/documents/{id}")
    public String getDocumentById(@PathVariable Integer id, Model model) {
        DocumentDTO document = documentService.findDocumentById(id);
        model.addAttribute("documentById", document);
        logger.debug("Get DocumentById method");
        return "document";
    }

}
