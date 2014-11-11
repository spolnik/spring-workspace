package com.apress.isf.spring.service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchEngineService implements SearchEngine {

    private static final Logger log =
            LoggerFactory.getLogger(SearchEngineService.class);

    private DocumentDAO documentDAO;

    public SearchEngineService() {
        if (log.isDebugEnabled())
            log.debug("ServiceSearchEngine created: " + this);
    }

    public void setDocumentDAO(DocumentDAO documentDAO) {
        if (log.isDebugEnabled())
            log.debug("Document DAO set: " + documentDAO);

        this.documentDAO = documentDAO;
    }

    @Override
    public List<Document> findByType(Type documentType) {
        return listAll().stream()
                .filter(document -> document.getType().equals(documentType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Document> listAll() {
        return Arrays.asList(documentDAO.getAll());
    }
}
