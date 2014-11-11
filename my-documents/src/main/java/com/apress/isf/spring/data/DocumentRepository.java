package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class DocumentRepository implements DocumentDAO {

    private static final Logger log
            = LoggerFactory.getLogger(DocumentRepository.class);

    private List<Document> documents = null;

        public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public Document[] getAll() {
        if (log.isDebugEnabled())
            log.debug("Start <getAll> Params: ");

        final Document[] result = documents.stream().toArray(Document[]::new);

        if (log.isDebugEnabled())
            log.debug("End <getAll> Result:" + Arrays.toString(result));

        return result;
    }
}
