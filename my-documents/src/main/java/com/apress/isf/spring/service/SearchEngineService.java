package com.apress.isf.spring.service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchEngineService implements SearchEngine {

    private DocumentDAO documentDAO;

    public void setDocumentDAO(DocumentDAO documentDAO) {
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

    @Override
    public List<Document> findByLocation(String locaiton) {
        throw new UnsupportedOperationException("findByLocation not yet implemented.");
    }
}
