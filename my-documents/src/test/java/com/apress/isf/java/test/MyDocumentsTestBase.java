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
    private SearchEngine engine;
    @Autowired
    private Type webType;

    @Test
    public void testFindByType() throws Exception {
        final List<Document> documents = engine.findByType(webType);

        assertThat(documents).isNotNull().hasSize(1);
        assertThat(documents.get(0).getType())
                .isEqualToComparingFieldByField(webType);
    }

    @Test
    public void testListAll() throws Exception {
        final List<Document> documents = engine.listAll();
        assertThat(documents).isNotNull().hasSize(4);
    }
}
