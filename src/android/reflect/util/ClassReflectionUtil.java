/*
 * Copyright (C) 2013  Richard Schilling. All rights reserved.
 * contact: coderroadie@gmail.com
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package android.reflect.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * {@link Class} reflection utility functions.
 * 
 * @author Richard Schilling
 * @since 1.0
 */
public final class ClassReflectionUtil {
    /**
     * A list of classes for primitive box types.
     * 
     * @since 1.0
     */
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
     * A list of classes for primitive arrays.
     * 
     * @since 1.0
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
     * Returns true if calling {@link Class#isPrimitive()} on {@code c} returns
     * true or if {@code c} is present in {@link #PRIMITIVE_ARRAYS}.
     * 
     * @param c the class to check
     * @return true if the class is a primitive type or a primitive box type.
     * @since 1.0
     */
    public static boolean isPrimitiveOrArray(Class<?> c) {

        if (c == null) {
            throw new IllegalArgumentException("c cannot be null");
        }

        if (c.isPrimitive()) {
            return true;
        }

        for (Class<?> boxType : PRIMITIVE_BOX_TYPES) {
            if (c == boxType) {
                return true;
            }
        }

        return isPrimitiveArray(c);

    }

    /**
     * Returns true if {@code c} is present in {@link #PRIMITIVE_ARRAYS}.
     * 
     * @param c the class to check for.
     * @return true if class is a primitive array
     * @since 1.0
     */
    public static boolean isPrimitiveArray(Class<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("c cannot be null");
        }

        for (Class<?> pClass : PRIMITIVE_ARRAYS) {
            if (pClass == c) {
                return true;
            }
        }

        return false;

    }

    /**
     * Creates a new set that contains only classes that return false when
     * calling {@link #isPrimitiveOrArray(Field)}.
     * 
     * @param classSet the set of classes to process
     * @return a new set containing non-primitive types
     * @since 1.0
     */
    public static Set<Class<?>> removePrimitives(Set<Class<?>> classSet) {
        Set<Class<?>> result = new HashSet<Class<?>>();

        for (Iterator<Class<?>> i = classSet.iterator(); i.hasNext();) {
            Class<?> c = i.next();
            if (!isPrimitiveOrArray(c))
                result.add(c);
        }

        return result;

    }

    /**
     * Creates a new set that contains only classes that return true when
     * calling {@link #isPrimitiveOrArray(Field)}.
     * 
     * @param classSet the set of classes to process
     * @return a new set containing non-primitive types
     * @since 1.0
     */
    public static Set<Class<?>> keepPrimitives(Set<Class<?>> classSet) {
        Set<Class<?>> result = new HashSet<Class<?>>();

        for (Iterator<Class<?>> i = classSet.iterator(); i.hasNext();) {
            Class<?> c = i.next();
            if (isPrimitiveOrArray(c))
                result.add(c);
        }

        return result;

    }
}
