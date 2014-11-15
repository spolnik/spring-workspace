package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;

import java.util.List;

public interface DocumentDAO {
    List<Document> getAll();
    void save(Document document);
    Document findById(String id);
}
