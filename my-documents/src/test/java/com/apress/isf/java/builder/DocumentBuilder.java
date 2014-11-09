package com.apress.isf.java.builder;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.model.Type;

public final class DocumentBuilder {
    private String name;
    private Type type;
    private String location;

    public DocumentBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public DocumentBuilder withType(final Type aType) {
        type = aType;
        return this;
    }

    public DocumentBuilder withLocation(final String aLocation) {
        location = aLocation;
        return this;
    }

    public Document build() {
        Document document = new Document();

        document.setName(name);
        document.setType(type);
        document.setLocation(location);

        return document;
    }
}
