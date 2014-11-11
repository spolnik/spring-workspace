package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;
import com.apress.isf.spring.jdbc.DocumentRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DocumentRepository implements DocumentDAO {

    private String queryAll;
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setQueryAll(String queryAll) {
        this.queryAll = queryAll;
    }

    @Override
    public List<Document> getAll() {
        return jdbcTemplate.query(queryAll, new DocumentRowMapper());
    }
}
