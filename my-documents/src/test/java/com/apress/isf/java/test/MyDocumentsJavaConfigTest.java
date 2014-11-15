package com.apress.isf.java.test;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.config.MyDocumentsConfiguration;
import com.apress.isf.spring.jms.JMSProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyDocumentsConfiguration.class)
public class MyDocumentsJavaConfigTest {

    //Based on the META-INF/data/jms.txt - only one record
    private static final int MAX_ALL_DOCS = 5;
    private static final int MAX_WEB_DOCS = 2;

    @Autowired
    private SearchEngine engineProxy;

    @Autowired
    private JMSProducer jmsProducer;

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

    @Test
    public void testSpringJMS() throws InterruptedException {

        jmsProducer.send();

        assertThat(engineProxy).isNotNull();

        //Waiting a least 3 seconds so the message is consumed.
        Thread.sleep(3000);
        //After the JMS message and insert, must be 5 Documents
        assertThat(MAX_ALL_DOCS).isEqualTo(engineProxy.listAll().size());

        Type documentType = new Type("WEB",".url");
        assertThat(MAX_WEB_DOCS).isEqualTo(engineProxy.findByType(documentType).size());
    }
}
