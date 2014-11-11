package com.apress.isf.spring.data;

import com.apress.isf.java.model.Type;

public interface TypeDataDAO {
    Type[] getAll();
    Type findById(String anId);
}
