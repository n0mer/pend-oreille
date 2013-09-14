
package com.cognition.reflect;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class FieldReflectionUtil {

    private FieldReflectionUtil() {
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

        return ClassReflectionUtil.isPrimitive(f.getType());
    }

    /**
     * Removes all the keys from a map that have a primitive type. The map
     * passed into the function is untouched.
     * 
     * @param fieldMap
     * @return
     */
    public static Map<Field, Object> removePrimitives(Map<Field, Object> fieldMap) {

        Map<Field, Object> result = new Hashtable<Field, Object>();

        for (Entry<Field, Object> e : fieldMap.entrySet()) {
            Field key = e.getKey();
            if (!isPrimitive(key))
                result.put(e.getKey(), e.getValue());

        }

        return result;

    }

    /**
     * Removes all the Field objects from a map that are part of a list. A new
     * instance of Map<Field, Object> is returned. fieldMap is untouched.
     * 
     * @param fieldMap
     * @param types
     * @return
     */
    public static Map<Field, Object> removeFieldTypes(Map<Field, Object> fieldMap,
            List<Class<?>> types) {
        Map<Field, Object> result = new Hashtable<Field, Object>();

        for (Entry<Field, Object> e : fieldMap.entrySet()) {
            Field key = e.getKey();
            if (!types.contains(key.getType()))
                result.put(e.getKey(), e.getValue());

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

    /**
     * Returns the values of all fields in a class and its superclasses.
     * 
     * @param c the class to get fields for
     * @param object the instance of class that field values should be fetched
     *            from.
     * @return a map of field names and values.
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static <T> Map<Field, Object> getFieldValues(Class<?> c, T object)
            throws IllegalArgumentException, IllegalAccessException {

        if (c == null)
            throw new IllegalArgumentException("c cannot be null");

        if (object == null)
            throw new IllegalArgumentException("object cannot be null");

        Hashtable<Field, Object> result = new Hashtable<Field, Object>();
        /*
         * derive field names and field values
         */
        while (c != null) {
            Field[] fields = c.getDeclaredFields();
            if (fields != null) {
                for (Field f : fields) {
                    if (!f.isAccessible())
                        f.setAccessible(true);

                    result.put(f, f.get(object));

                }

            }
            c = c.getSuperclass();
        }
        return result;
    }

    /**
     * Returns a list of all fields declared in a class as well as its
     * superclasses.
     * 
     * @param c
     * @return
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
