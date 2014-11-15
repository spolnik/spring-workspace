package com.apress.isf.java.test;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;
import com.apress.isf.java.utils.XmlUtils;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlUtilsTest {

    @Test
    public void testXmlUtils(){

        Type type = new Type();
        type.setTypeId("4980d2e4-a424-4ff4-a0b2-476039682f43");
        type.setName("WEB");
        type.setDesc("Web Link");
        type.setExtension(".url");


        Document document = new Document();
        document.setDocumentId(UUID.randomUUID().toString());
        document.setName("Apress Books");
        document.setLocation("http://www.apress.com");
        document.setDescription("Apress Books");
        document.setType(type);
        document.setCreated(new Date());
        document.setModified(new Date());

        String string = XmlUtils.toXML(document);

        Document other = XmlUtils.fromXML(string,Document.class);
        assertThat(other).isNotNull().isEqualToComparingFieldByField(document);
    }
}
