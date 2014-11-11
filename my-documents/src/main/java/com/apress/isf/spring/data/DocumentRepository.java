package com.apress.isf.spring.data;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DocumentRepository implements DocumentDAO {

    private String queryAll;
    private DataSource dataSource;
    private Resource schema;
    private Resource data;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setQueryAll(String queryAll) {
        this.queryAll = queryAll;
    }

    public void setSchema(Resource schema) {
        this.schema = schema;
    }

    public void setData(Resource data) {
        this.data = data;
    }

    public void initialize() {
        try {
            String schemaSql = getSql(schema);
            Connection connection = null;
            Statement statement;

            try {
                connection = dataSource.getConnection();
                statement = connection.createStatement();
                statement.execute(schemaSql);
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            } finally {
                if (null != connection) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        // No work to do
                    }
                }
            }

            String dataSql = getSql(data);

            connection = null;
            try {
                connection = dataSource.getConnection();
                statement = connection.createStatement();
                statement.executeUpdate(dataSql);
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            } finally {
                if (null != connection) {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        // No work to do
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getSql(Resource resource) throws IOException {
        final StringBuilder sql = new StringBuilder();

        try (InputStream stream = resource.getInputStream()) {
            try (Scanner scanner = new Scanner(stream)) {
                while (scanner.hasNext()) {
                    sql.append(scanner.nextLine());
                    sql.append("\n");
                }
                scanner.close();
            }
            stream.close();
        }

        return sql.toString();
    }

    @Override
    public List<Document> getAll() {
        List<Document> result = new ArrayList<>();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(queryAll);
            while (resultSet.next()) {
                result.add(getDocument(resultSet));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // No work to do
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

        Type type = new Type();
        type.setTypeId(resultSet.getString("typeId"));
        type.setName(resultSet.getString("type_name"));
        type.setDesc(resultSet.getString("type_desc"));
        type.setExtension(resultSet.getString("extension"));

        document.setType(type);

        return document;
    }
}
