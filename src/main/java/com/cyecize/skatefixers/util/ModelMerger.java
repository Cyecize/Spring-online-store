package com.cyecize.skatefixers.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMerger {

    private static final String TYPE_MISMATCH ="Field type mismatch";

    public ModelMerger() {

    }

    public <D> D merge(Object source, D destination) {
        List<Field> destinationFields = this.getAllFields(destination.getClass());
        try {
            for (Field field : this.getAllFields(source.getClass())) {
                field.setAccessible(true);
                Field destinationField = this.getField(field.getName(), destinationFields);
                if (destinationField == null)
                    continue;
                destinationField.setAccessible(true);
                if(!destinationField.getClass().getTypeName().equals(field.getClass().getTypeName())) {
                    throw new RuntimeException(TYPE_MISMATCH);
                }
                destinationField.set(destination, field.get(source));
            }
            return destination;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return destination;
    }

    private Field getField(String fieldName, List<Field> fields) {
        for (Field field : fields) {
            if (field.getName().equals(fieldName))
                return field;
        }
        return null;
    }

    private List<Field> getAllFields(Class<?> type) {
        List<Field> fields = Arrays.stream(type.getDeclaredFields()).collect(Collectors.toList());
        if (type.getSuperclass() != null) {
            fields.addAll(getAllFields(type.getSuperclass()));
        }
        return fields;
    }
}
