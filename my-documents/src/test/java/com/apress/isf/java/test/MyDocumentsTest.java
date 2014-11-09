package com.apress.isf.java.test;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.config.MyDocumentsContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDocumentsTest {

    private SearchEngine engine;
    private Type documentType;

    @Before
    public void setUp() throws Exception {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(MyDocumentsContext.class);

        engine = context.getBean(SearchEngine.class);
        documentType = context.getBean(Type.class);
    }

    @Test
    public void testFindByType() throws Exception {
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
