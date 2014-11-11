package com.apress.isf.java.test;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class MyDocumentsTestBase {

    @Autowired
    private SearchEngine engineProxy;

    @Autowired
    private Type webType;

    @Test
    public void testFindByType() throws Exception {
        final List<Document> documents = engineProxy.findByType(webType);

        assertThat(documents).isNotNull().hasSize(1);
        assertThat(documents.get(0).getType().getName()).isEqualTo(webType.getName());
        assertThat(documents.get(0).getType().getDesc()).isEqualTo(webType.getDesc());
        assertThat(documents.get(0).getType().getExtension()).isEqualTo(webType.getExtension());
    }

    @Test
    public void testListAll() throws Exception {
        final List<Document> documents = engineProxy.listAll();
        assertThat(documents).isNotNull().hasSize(4);
    }

    @Test
    public void testFindByLocation() throws Exception {
        try {
            engineProxy.findByLocation("/some/path");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
