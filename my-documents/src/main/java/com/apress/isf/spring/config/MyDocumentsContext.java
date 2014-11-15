package com.apress.isf.spring.config;

import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;
import com.apress.isf.spring.data.DocumentRepository;
import com.apress.isf.spring.service.SearchEngineService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan("com.apress.isf.spring")
public class MyDocumentsContext {

    @Bean
    public Type webType() {
        final Type webType = new Type("WEB", ".url");
        webType.setDesc("Web Link");

        return webType;
    }

    @Bean
    public SearchEngine engine() {
        return new SearchEngineService();
    }

    @Bean
    public DocumentDAO documentDAO() {
        return new DocumentRepository();
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        return embeddedDatabaseBuilder
                .addScript("classpath:META-INF/data/schema.sql")
                .addScript("classpath:META-INF/data/data.sql")
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
    }

    @Bean
    public String query() {
        return "select d.documentId, d.name, d.location, d.description as doc_desc, d.typeId, d.created, d.modified, t.name as type_name, t.description as type_desc, t.extension from documents d\n" +
                "join types t on d.typeId = t.typeId";
    }

    @Bean
    public String update() {
        return "update documents\n" +
                "set name = ?,\n" +
                "location = ?,\n" +
                "description = ?,\n" +
                "typeId = ?,\n" +
                "modified = ?\n" +
                "where documentId = ?";
    }

    @Bean
    public String insert() {
        return "insert into documents (documentId,name,location,description, typeId, created, modified)\n" +
                "values (?,?,?,?,?,?,?)";
    }

    @Bean
    public String find() {
        return "select d.documentId, d.name, d.location, d.description as doc_desc,\n" +
                "d.typeId, d.created, d.modified,\n" +
                "t.name as type_name, t.description as type_desc, t.extension from\n" +
                "documents d\n" +
                "join types t\n" +
                "on d.typeId = t.typeId\n" +
                "where d.documentId = ?";
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        final JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName("mydocumentsQueue");
        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }
}
