package com.apress.isf.spring.data;

import com.apress.isf.java.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class TypeDataRepository implements TypeDataDAO {

    private static final Logger log
            = LoggerFactory.getLogger(TypeDataRepository.class);

    private Map<String,Type> types = null;

    public void setTypes(Map<String, Type> types) {
        this.types = types;
    }

    @Override
    public Type[] getAll() {
        return types.values().stream().toArray(Type[]::new);
    }

    @Override
    public Type findById(String id) {
        if (log.isDebugEnabled())
            log.debug("Start <findById> Params: " + id);

        final Type type = types.get(id);

        if (log.isDebugEnabled())
            log.debug("End <findById> Params: " + type);

        return type;
    }
}
