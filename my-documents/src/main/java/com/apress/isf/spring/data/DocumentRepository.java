package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;
import com.apress.isf.spring.jdbc.DocumentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("documentDAO")
public class DocumentRepository implements DocumentDAO {

    @Autowired
    private String query;

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public List<Document> getAll() {
        return jdbcTemplate.query(query, new DocumentRowMapper());
    }
}
