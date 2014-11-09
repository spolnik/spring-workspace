package com.apress.isf.java.builder;

import com.apress.isf.java.model.Type;

public final class TypeBuilder {
    private String name = "default-name";
    private String description = "default-description";
    private String extension = ".default-extension";

    public TypeBuilder withName(final String aName) {
        name = aName;
        return this;
    }

    public TypeBuilder withDescription(final String aDescription) {
        description = aDescription;
        return this;
    }

    public TypeBuilder withExtension(final String anExtension) {
        extension = anExtension;
        return this;
    }

    public Type build() {
        Type type = new Type();

        type.setName(name);
        type.setDesc(description);
        type.setExtension(extension);

        return type;
    }
}
