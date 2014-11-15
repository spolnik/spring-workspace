package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;
import com.apress.isf.spring.jdbc.DocumentRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

@Repository("documentDAO")
public class DocumentRepository implements DocumentDAO {

    @Inject
    private DataSource dataSource;

    @Inject
    private String query;
    @Inject
    private String insert;
    @Inject
    private String find;
    @Inject
    private String update;

    @Override
    public List<Document> getAll() {
        return new JdbcTemplate(dataSource).query(query, new DocumentRowMapper());
    }

    @Override
    public Document findById(String id) {
        Document updateDocument = null;

        final JdbcTemplate template = new JdbcTemplate(dataSource);

        try {
            updateDocument = template.queryForObject(find,
                    new Object[] {id},
                    new DocumentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return updateDocument;
    }

    @Override
    public void save(Document document) {
        try {
            final JdbcTemplate template = new JdbcTemplate(dataSource);

            if (null == findById(document.getDocumentId())) {
                template.update(
                        insert,
                        document.getDocumentId(),
                        document.getName(),
                        document.getLocation(),
                        document.getDescription(),
                        document.getType().getTypeId(),
                        document.getCreated(),
                        document.getModified());
            } else {
                template.update(
                        update,
                        document.getName(),
                        document.getLocation(),
                        document.getDescription(),
                        document.getType().getTypeId(),
                        new Date(),
                        document.getDocumentId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
