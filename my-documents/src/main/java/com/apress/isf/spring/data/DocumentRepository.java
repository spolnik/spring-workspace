package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository implements DocumentDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Document> getAll() {
        List<Document> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM documents");

            while (resultSet.next()) {
                result.add(getDocument(resultSet));
            }
        } catch (SQLException sqlEx) {
            throw new RuntimeException(sqlEx);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // nothing more to do
                }
            }
        }

        return result;
    }

    private Document getDocument(ResultSet resultSet) throws SQLException {
        Document document = new Document();

        document.setDocumentId(resultSet.getString("documentId"));
        document.setName(resultSet.getString("name"));
        document.setLocation(resultSet.getString("location"));
        document.setCreated(resultSet.getDate("created"));
        document.setModified(resultSet.getDate("modified"));
        document.setDescription("doc_desc");

        return document;
    }
}
