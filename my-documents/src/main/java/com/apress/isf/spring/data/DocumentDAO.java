package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;

import java.util.List;

public interface DocumentDAO {
    List<Document> getAll();
    Document save(String id, Document document);
    Document findById(String id);
    Document removeById(String id);
}
