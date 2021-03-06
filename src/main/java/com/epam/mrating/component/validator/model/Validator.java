package com.epam.mrating.component.validator.model;

import com.epam.mrating.component.validator.annotation.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * The enum Validator.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 public final class Validator {

    private Validator() {
    }

    /**
     * Validate boolean.
     *
     * @param o          the o
     * @param fieldName  the field name
     * @param fieldValue the field value
     * @return the boolean
     * @throws NoSuchFieldException   the no such field exception
     * @throws IllegalAccessException the illegal access exception
     */
    public static boolean validate(Object o, String fieldName, String fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Field violationsField = o.getClass().getSuperclass().getDeclaredField("violations");
        violationsField.setAccessible(true);
        Violations violations = (Violations)violationsField.get(o);
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            if (field.getName().equals(fieldName)){
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation: annotations) {
                    if (annotation instanceof Validate) {
                        Validate validator = (Validate) annotation;
                        if(!validator.type().isValid(fieldValue)){
                            violations.addViolation(field.getName(),validator.message());
                            violationsField.set(o,violations);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
