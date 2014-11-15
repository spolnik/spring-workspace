package com.apress.isf.java.utils;

import com.thoughtworks.xstream.XStream;

public class XmlUtils {

    public static <T> String toXML(T object) {
        XStream xStream = new XStream();
        xStream.alias(object.getClass().getSimpleName().toLowerCase(),
                object.getClass());
        return xStream.toXML(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromXML(String xml, Class<T> aClass) {
        final XStream xStream = new XStream();
        xStream.alias(aClass.getSimpleName().toLowerCase(), aClass);
        return (T)xStream.fromXML(xml);
    }
}
