package com.gmail.yauhen2012.webmodule.controller;

import java.lang.invoke.MethodHandles;

import javax.validation.Valid;

import com.gmail.yauhen2012.service.DocumentService;
import com.gmail.yauhen2012.service.model.AddDocumentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddDocumentController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private DocumentService documentService;

    public AddDocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents/add")
    public String addDocumentPage(Model model) {
        model.addAttribute("document", new AddDocumentDTO());
        logger.debug("Get addDocumentPage method");
        return "document_add";
    }

    @PostMapping("/documents/add")
    public String addDocument(@Valid @ModelAttribute(name = "document") AddDocumentDTO document, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("document", document);
            return "document_add";
        } else {
            documentService.addDocument(document);
            logger.debug("Post addDocument method");
            return "redirect:/documents";
        }
    }

}
