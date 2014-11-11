package com.apress.isf.java.test;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.config.MyDocumentsContext;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDocumentsTest {

    @Test
    public void testFindByType_annotiations() throws Exception {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(MyDocumentsContext.class);
        testFindByType(context);
    }

    @Test
    public void testFindByType_xml() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
        testFindByType(context);
    }

    private void testFindByType(ApplicationContext context) throws Exception {
        SearchEngine engine = context.getBean(SearchEngine.class);
        Type documentType = context.getBean("webType", Type.class);

        final List<Document> documents = engine.findByType(documentType);

        assertThat(documents).isNotNull().hasSize(1);
        assertThat(documents.get(0).getType())
                .isEqualToComparingFieldByField(documentType);
    }

    @Test
    public void testListAll_annotations() throws Exception {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(MyDocumentsContext.class);
        testListAll(context);
    }

    @Test
    public void testListAll_xml() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
        testListAll(context);
    }

    private void testListAll(ApplicationContext context) throws Exception {
        SearchEngine engine = context.getBean(SearchEngine.class);

        final List<Document> documents = engine.listAll();
        assertThat(documents).isNotNull().hasSize(4);
    }
}
