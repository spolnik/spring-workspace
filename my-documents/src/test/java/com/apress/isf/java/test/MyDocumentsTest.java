package com.apress.isf.java.test;

import com.apress.isf.java.builder.TypeBuilder;
import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.FakeSearchEngine;
import com.apress.isf.java.service.SearchEngine;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDocumentsTest {

    private SearchEngine engine = new FakeSearchEngine();

    @Test
    public void testFindByType() throws Exception {
        final Type documentType = new TypeBuilder()
                .withName("WEB")
                .withDescription("Web Link")
                .withExtension(".url")
                .build();

        final List<Document> documents = engine.findByType(documentType);

        assertThat(documents).isNotNull().hasSize(1);
        assertThat(documents.get(0).getType())
                .isEqualToComparingFieldByField(documentType);
    }

    @Test
    public void testListAll() throws Exception {
        final List<Document> documents = engine.listAll();
        assertThat(documents).isNotNull().hasSize(4);
    }
}
