
package com.cognition.reflect;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Reflection utilities for class operations.
 * 
 * @author Richard Schilling
 */
public final class ClassUtil {

    private ClassUtil() {
        throw new UnsupportedOperationException("this class is not meant to be instantiated");
    }

    /**
     * Returns the superset of classes that are needed to define a class. The
     * list includes :
     * <ol>
     * <li>the classes of all the fields defined in the class
     * <li>the classes of all fields defined in super classes.
     * <ol>
     * 
     * @param c the class to extract field classes from
     * @return the list of classes that are used to define fields
     */
    public static Set<Class<?>> getFieldClasses(
            Class<?> c) {

        if (c == null)
            throw new IllegalArgumentException("c cannot be null");

        Set<Class<?>> result = new HashSet<Class<?>>();

        while (c != null) {
            for (Field f : c.getDeclaredFields()) {
                result.add(f.getType());
            }
            c = c.getSuperclass();
        }

        return result;

    }
}
