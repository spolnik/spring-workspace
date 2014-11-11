package com.apress.isf.java.service;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;

import java.util.List;

public interface DocumentService {
    void createNewType(Type type);
    void updateType(Type type);
    void removeTypeById(String id);
    List<Type> getAllDefinedTypes();
    Type getTypeById(String id);

    void createNewDocument(Document document);
    void removeDocumentById(String id);
    void updateDocument(Document document);
    void updateLocationFromDocumentId(String documentId, String location);
}
