package com.cyecize.skatefixers.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMerger {

    public ModelMerger() {

    }

    public <D> D merge(Object source, D destination) {
        List<Field> destinationFields = this.getAllFields(destination.getClass());
        try {
            for (Field field : source.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Field destinationField = this.getField(field.getName(), destinationFields);
                if (destinationField == null)
                    continue;
                destinationField.setAccessible(true);
                destinationField.set(destination, field.get(source));
            }
            return destination;
        } catch (Exception e) {
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
