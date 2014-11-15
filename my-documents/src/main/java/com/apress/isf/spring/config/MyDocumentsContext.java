package com.apress.isf.spring.config;

import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;
import com.apress.isf.spring.data.DocumentRepository;
import com.apress.isf.spring.service.SearchEngineService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
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
    public DocumentDAO documentDAO(final DataSource dataSource) {
        final DocumentRepository documentRepository = new DocumentRepository();
        documentRepository.setDataSource(dataSource);
        return documentRepository;
    }

    @Bean
    public DataSource dataSource(){
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
}
