package com.apress.isf.java.test;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.amqp.RabbitMQProducer;
import com.apress.isf.spring.jms.JMSProducer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-context.xml")
public class MyDocumentsSpringXmlTest {

    //Based on the META-INF/data/jms.txt - only one record
    private static final int MAX_ALL_DOCS = 5;
    private static final int MAX_WEB_DOCS = 2;

    private static final String DOCUMENT_ID = "df569fa4-a513-4252-9810-818cade184ca";

    @Inject
    private SearchEngine engineProxy;

    @Inject
    private JMSProducer jmsProducer;

    @Inject
    private RabbitMQProducer rabbitmqProducer;

    @Inject
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

    @Test
    @Ignore
    public void testSpringRabbitMQ() throws Exception {

        jmsProducer.send();
        //Waiting a least 3 seconds so the message is consumed.
        Thread.sleep(3000);

        assertThat(rabbitmqProducer).isNotNull();

        Document document = engineProxy.findById(DOCUMENT_ID);
        assertThat(document).isNotNull();
        rabbitmqProducer.send(document);
    }
}
