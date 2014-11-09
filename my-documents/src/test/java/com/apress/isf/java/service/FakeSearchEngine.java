package com.apress.isf.java.service;

import com.apress.isf.java.builder.DocumentBuilder;
import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.builder.TypeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeSearchEngine implements SearchEngine {

    @Override
    public List<Document> findByType(Type documentType) {
        return storage().stream()
                .filter(document -> document.getType().equals(documentType))
                .collect(Collectors.toList());
    }

    @Override
    public List<Document> listAll() {
        return storage();
    }

    private List<Document> storage() {
        final List<Document> result = new ArrayList<>();

        Type type = new TypeBuilder()
                .withName("PDF")
                .withDescription("Portable Document Format")
                .withExtension(".pdf")
                .build();

        Document document = new DocumentBuilder()
                .withName("Book template")
                .withType(type)
                .withLocation("/Documents/Book Template.pdf")
                .build();

        result.add(document);

        document = new DocumentBuilder()
                .withName("Sample Contract")
                .withType(type)
                .withLocation("/Users/felipeg/Documents/Contracts/Sample Contract.pdf")
                .build();

        result.add(document);

        type = new TypeBuilder()
                .withName("NOTE")
                .withDescription("Text Notes")
                .withExtension(".txt")
                .build();

        document = new DocumentBuilder()
                .withName("Clustering with RabbitMQ")
                .withType(type)
                .withLocation("/Users/felipeg/Documents/Random/Clustering with RabbitMQ.txt")
                .build();

        result.add(document);

        type = new TypeBuilder()
                .withName("WEB")
                .withDescription("Web Link")
                .withExtension(".url")
                .build();

        document = new DocumentBuilder()
                .withName("Pro Spring Security Book")
                .withType(type)
                .withLocation("http://www.apress.com/9781430248187")
                .build();

        result.add(document);

        return result;
    }
}
