package com.apress.isf.spring.annotated.service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("engine")
public class AnnotatedSearchEngine implements SearchEngine {

    @Autowired
    private DocumentDAO documentDAO;

    @Override
    public List<Document> findByType(Type documentType) {
        return listAll().stream()
                .filter(doc -> doc.getType().equals(documentType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Document> listAll() {
        return Arrays.asList(documentDAO.getAll());
    }
}