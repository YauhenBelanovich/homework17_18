package com.gmail.yauhen2012.webmodule.controller;

import java.lang.invoke.MethodHandles;

import com.gmail.yauhen2012.service.DocumentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DocumentActionController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private DocumentService documentService;

    public DocumentActionController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/{id}/delete")
    public String deleteDocumentById(@PathVariable Integer id, Model model) {
        model.addAttribute("documentById", documentService.deleteDocumentById(id));
        logger.debug("Get deleteDocumentById method");
        return "redirect:/documents";
    }

}
