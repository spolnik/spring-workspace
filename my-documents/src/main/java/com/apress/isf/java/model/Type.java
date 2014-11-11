package com.apress.isf.java.model;

public class Type {
    private String typeId;
    private String name;
    private String desc;
    private String extension;

    public Type() {
    }

    public Type(String name, String extension) {
        this.name = name;
        this.extension = extension;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        return !(name != null ? !name.equals(type.name) : type.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId='" + typeId + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
