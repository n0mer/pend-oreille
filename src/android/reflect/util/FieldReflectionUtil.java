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
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * {@link Field} reflection utility functions.
 * 
 * @author Richard Schilling
 * @since 1.0
 */
public final class FieldReflectionUtil {

    private FieldReflectionUtil() {
        throw new UnsupportedOperationException("this class should not be instantiated");
    }

    /**
     * Creates a new map that excludes all the entries where {@link Field} is
     * not a primitive type or primitive array. {@code fieldMap} is untouched.
     * 
     * @param fieldMap the fieldMap to read
     * @return a new map
     * @since 1.0
     */
    public static Map<Field, Object> removePrimitives(Map<Field, Object> fieldMap) {

        if (fieldMap == null) {
            return null;
        }

        Map<Field, Object> result = new Hashtable<Field, Object>();

        for (Entry<Field, Object> e : fieldMap.entrySet()) {
            Field key = e.getKey();
            if (!ClassReflectionUtil.isPrimitiveOrArray(key.getType())) {
                result.put(e.getKey(), e.getValue());
            }

        }

        return result;

    }

    /**
     * Creates a new map that excludes all {@link Field} entries with classes
     * found in {@code fieldMap}. {@code fieldMap} and {@code types} are
     * untouched.
     * 
     * @param fieldMap the map to examine.
     * @param types insert into the new map all {@link Field} entries that are
     *            not in this list.
     * @return a new map.
     * @since 1.0
     */
    public static Map<Field, Object> removeFieldTypes(Map<Field, Object> fieldMap,
            List<Class<?>> types) {
        Map<Field, Object> result = new Hashtable<Field, Object>();

        for (Entry<Field, Object> e : fieldMap.entrySet()) {
            Field key = e.getKey();
            if (!types.contains(key.getType())) {
                result.put(e.getKey(), e.getValue());
            }

        }

        return result;
    }

    /**
     * Converts the value of a field into an enumeration constant.
     * 
     * @return The matching enum value or null if blank enum name.
     * @throws IllegalArgumentException If the enum name is not known.
     * @since 1.0
     */
    public static Enum<?> toEnum(Field field, String enumName) {
        if (enumName == null || enumName.trim().length() == 0) {
            throw new IllegalArgumentException("enumName cannot be null or empty");
        }

        if (!field.getType().isEnum()) {
            throw new IllegalArgumentException("field is not an enumeration type");
        }

        for (Enum<?> enumVal : (Enum<?>[]) field.getType().getEnumConstants()) {
            if (enumVal.name().equals(enumName)) {
                return enumVal;
            }
        }
        throw new IllegalArgumentException(enumName + " is not an enum of "
                + field.getType().getSimpleName());
    }

    /**
     * Returns the values of all fields in a class and its superclasses.
     * 
     * @param c the class to get fields for
     * @param object the instance of class that field values should be fetched
     *            from.
     * @return a map of field names and values.
     * @throws IllegalAccessException if a field value could not be read.
     * @throws IllegalArgumentException if either {@code c} or {@code object}
     *             are null.
     * @since 1.0
     */
    public static <T> Map<Field, Object> getFieldValues(Class<?> c, T object)
            throws IllegalArgumentException, IllegalAccessException {

        if (c == null) {
            throw new IllegalArgumentException("c cannot be null");
        }

        if (object == null) {
            throw new IllegalArgumentException("object cannot be null");
        }

        Hashtable<Field, Object> result = new Hashtable<Field, Object>();
        /*
         * derive field names and field values
         */
        while (c != null) {
            Field[] fields = c.getDeclaredFields();
            if (fields != null) {
                for (Field f : fields) {
                    if (!f.isAccessible()) {
                        f.setAccessible(true);
                    }

                    result.put(f, f.get(object));

                }

            }
            c = c.getSuperclass();
        }
        return result;
    }

    /**
     * Returns a list of all fields declared in a class as well as its super
     * classes.
     * 
     * @param c the class to examine.
     * @return all the fields defined in a class and it's super classes.
     * @since 1.0
     */
    public static List<Field> getAllFields(Class<?> c) {

        List<Field> result = new LinkedList<Field>();
        for (Class<?> classWalk = c; classWalk != null; classWalk = classWalk
                .getSuperclass()) {
            for (Field field : classWalk.getDeclaredFields()) {
                result.add(field);
            }
        }

        return result;

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
     * @since 1.0
     */
    public static Set<Class<?>> getFieldClasses(
            Class<?> c) {

        if (c == null) {
            throw new IllegalArgumentException("c cannot be null");
        }

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
