
package com.cognition.reflect;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class FieldUtil {

    public static final Class<?>[] PRIMITIVE_CLASSES = {
            byte.class, Byte.class,
            short.class, Short.class,
            int.class, Integer.class,
            long.class, Long.class,
            float.class, Float.class,
            double.class, Double.class,
            boolean.class, Boolean.class,
            char.class, Character.class

    };

    private FieldUtil() {
        throw new UnsupportedOperationException("this class should not be instantiated");
    }

    /**
     * Returns true if the specified field is a java primitive type.
     * 
     * @return true if the field is a primitive type or false otherwise.
     */
    public static boolean isPrimitive(Field f) {
        if (f == null)
            throw new IllegalArgumentException("f cannot be null");

        return isPrimitive(f.getType());
    }

    public static boolean isPrimitive(Class<?> c) {

        if (c == null)
            throw new IllegalArgumentException("c cannot be null");

        for (Class<?> pClass : PRIMITIVE_CLASSES) {
            if (pClass == c)
                return true;
        }

        return false;

    }

    /**
     * Creates a new set that contains only classes that return false when
     * calling {@link #isPrimitive(Field)}.
     * 
     * @param classSet the set of classes to process
     * @return a new set containing
     */
    public static Set<Class<?>> removePrimitives(Set<Class<?>> classSet) {
        Set<Class<?>> result = new HashSet<Class<?>>();

        for (Iterator<Class<?>> i = classSet.iterator(); i.hasNext();) {
            Class<?> c = i.next();
            if (!isPrimitive(c))
                result.add(c);
        }

        return result;

    }

    /**
     * Creates a new set that contains only classes that return true when
     * calling {@link #isPrimitive(Field)}.
     * 
     * @param classSet the set of classes to process
     * @return a new set containing
     */
    public static Set<Class<?>> keepPrimitives(Set<Class<?>> classSet) {
        Set<Class<?>> result = new HashSet<Class<?>>();

        for (Iterator<Class<?>> i = classSet.iterator(); i.hasNext();) {
            Class<?> c = i.next();
            if (isPrimitive(c))
                result.add(c);
        }

        return result;

    }

    /**
     * Converts the value of a field into an enumeration constant.
     * 
     * @return The matching enum value or null if blank enum name.
     * @throws IllegalArgumentException If the enum name is not known.
     */
    public static Enum<?> toEnum(Field field, String enumName) {
        if (enumName == null || enumName.trim().length() == 0)
            throw new IllegalArgumentException("enumName cannot be null or empty");

        if (!field.getType().isEnum())
            throw new IllegalArgumentException("field is not an enumeration type");

        for (Enum<?> enumVal : (Enum<?>[]) field.getType().getEnumConstants()) {
            if (enumVal.name().equals(enumName)) {
                return enumVal;
            }
        }
        throw new IllegalArgumentException(enumName + " is not an enum of "
                + field.getType().getSimpleName());
    }

}
