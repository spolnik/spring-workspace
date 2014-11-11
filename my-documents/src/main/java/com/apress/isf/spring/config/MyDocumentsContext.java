package com.apress.isf.spring.config;

import com.apress.isf.java.model.Type;
import com.apress.isf.java.service.SearchEngine;
import com.apress.isf.spring.data.DocumentDAO;
import com.apress.isf.spring.data.DocumentRepository;
import com.apress.isf.spring.service.SearchEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyDocumentsContext {
    private static final Logger log =
            LoggerFactory.getLogger(MyDocumentsContext.class);

    @Bean
    public Type webType() {
        final Type webType = new Type("WEB", ".url");
        webType.setDesc("Web Link");

        return webType;
    }

    @Bean
    @Scope("prototype")
    public SearchEngine engine(){
        SearchEngineService engine = new SearchEngineService();
        engine.setDocumentDAO(documentDAO());

        if(log.isDebugEnabled())
            log.debug("SearchEngine created: " + engine);

        return engine;
    }

    private DocumentDAO documentDAO(){

        return new DocumentRepository();
    }


}
