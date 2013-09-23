
package com.cognition.reflect;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Reflection utilities for class operations.
 * 
 * @author Richard Schilling
 */
public final class ClassReflectionUtil {
    public static final Class<?>[] PRIMITIVE_BOX_TYPES = {
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Boolean.class,
            Character.class

    };

    /**
     * These classes are persisted as binary objects
     */
    public static final Class<?>[] PRIMITIVE_ARRAYS = {
            byte[].class, Byte[].class,
            short[].class, Short[].class,
            int[].class, Integer[].class,
            long[].class, Long[].class,
            float[].class, Float[].class,
            double[].class, Double[].class,
            boolean[].class, Boolean[].class,
            char[].class, Character[].class
    };

    private ClassReflectionUtil() {
        throw new UnsupportedOperationException("this class is not meant to be instantiated");
    }

    /**
     * Returns true if calling {@link Class#isPrimitive()} on {@code c} return
     * true or if {@code c} is present in {@link #PRIMITIVE_ARRAYS}.
     * 
     * @param c the class to check
     * @return true if the class is a primitive type or a primitive box type.
     */
    public static boolean isPrimitive(Class<?> c) {

        if (c == null) {
            throw new IllegalArgumentException("c cannot be null");
        }

        if (c.isPrimitive()) {
            return true;
        }

        return isPrimitiveArray(c);

    }

    public static boolean isPrimitiveArray(Class<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("c cannot be null");
        }

        for (Class<?> pClass : PRIMITIVE_BOX_TYPES) {
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
}
