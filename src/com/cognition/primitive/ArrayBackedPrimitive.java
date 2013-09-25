
package com.cognition.primitive;

import com.cognition.reflect.ClassReflectionUtil;

/**
 * Allows a byte array to used to store primitive values. The byte array passed
 * to the constructor are read and updated with each function.
 * 
 * @author Richard Schilling
 */
public final class ArrayBackedPrimitive {

    /**
     * The array that contains this primitive's data.
     */
    public final byte[] array;

    public ArrayBackedPrimitive(byte[] backedBy) {
        if (backedBy == null || backedBy.length == 0) {
            throw new IllegalArgumentException("parameter cannot be null or an array of length 0");
        }
        array = backedBy;

    }

    public Object asType(Class<?> type) {

        if (!ClassReflectionUtil.isPrimitive(type)) {
            throw new IllegalArgumentException(type.getName()
                    + " is not a primitive or primitive array");
        }

        if (type.isPrimitive()) {
            return asPrimitiveType(type);

        }

        return asPrimitiveArray(type);

    }

    public Object asPrimitiveType(Class<?> type) {
        if (!type.isPrimitive()) {
            throw new IllegalArgumentException(type.getName()
                    + " is not a primitive ");
        }
        if (type.isAssignableFrom(byte.class)) {
            return asByte();
        }

        if (type.isAssignableFrom(Byte.TYPE)) {
            return asBoxedByte();
        }

        if (type.isAssignableFrom(short.class)) {
            return asShort();
        }

        if (type.isAssignableFrom(Short.class)) {
            return asBoxedShort();
        }

        if (type.isAssignableFrom(int.class)) {
            return asInt();
        }

        if (type.isAssignableFrom(Integer.class)) {
            return asBoxedInt();

        }

        if (type.isAssignableFrom(long.class)) {
            return asLong();

        }

        if (type.isAssignableFrom(Long.class)) {
            return asBoxedLong();
        }

        if (type.isAssignableFrom(float.class)) {
            return asFloat();
        }

        if (type.isAssignableFrom(Float.class)) {
            return asBoxedFloat();
        }

        if (type.isAssignableFrom(double.class)) {
            return asDouble();
        }

        if (type.isAssignableFrom(Double.class)) {
            return asBoxedDouble();
        }

        if (type.isAssignableFrom(boolean.class)) {
            return asBoolean();
        }

        if (type.isAssignableFrom(Boolean.class)) {
            return asBoxedBoolean();
        }

        if (type.isAssignableFrom(char.class)) {
            return asChar();
        }

        if (type.isAssignableFrom(Character.class)) {
            return asBoxedChar();
        }

        throw new IllegalArgumentException("unable to convert this.array to type " + type.getName());
    }

    public Object asPrimitiveArray(Class<?> type) {

        if (!type.isArray() && !type.getComponentType().isPrimitive()) {
            throw new IllegalArgumentException(type.getName()
                    + " is not a primitive array ");
        }
        if (type == byte[].class) {
            throw new UnsupportedOperationException("use ArrayBackedPrimitive.array instead.");
        }

        if (type == Byte[].class) {
            return PrimitiveUtil.box(array);
        }

        if (type == short[].class) {
            return PrimitiveUtil.toShortArray(array);
        }

        if (type == Short[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toShortArray(array));
        }

        if (type == int[].class) {
            return PrimitiveUtil.toIntArray(array);
        }

        if (type == Integer[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toIntArray(array));
        }

        if (type == long[].class) {
            return PrimitiveUtil.toLongArray(array);

        }

        if (type == Long[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toLongArray(array));
        }

        if (type == float[].class) {
            return PrimitiveUtil.toFloatArray(array);
        }

        if (type == Float[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toFloatArray(array));
        }

        if (type == double[].class) {
            return PrimitiveUtil.toDoubleArray(array);
        }

        if (type == Double[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toDoubleArray(array));
        }

        if (type == boolean[].class) {
            return PrimitiveUtil.toBooleanArray(array);
        }

        if (type == Boolean[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toBooleanArray(array));
        }

        if (type == char[].class) {
            return PrimitiveUtil.toCharArray(array);
        }

        if (type == Character[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toCharArray(array));
        }

        throw new UnsupportedOperationException("Unable to convert this.array to primitive array "
                + type.getName());
    }

    public Object asByte() {
        return array[0];
    }

    public short asShort() {
        return PrimitiveUtil.toShort(array, 0);
    }

    public Object asInt() {
        return PrimitiveUtil.toInt(array, 0);
    }

    public Object asLong() {
        return PrimitiveUtil.toLong(array, 0);
    }

    public Object asFloat() {
        return PrimitiveUtil.toFloat(array, 0);
    }

    public Object asDouble() {
        return PrimitiveUtil.toDouble(array, 0);
    }

    /**
     * Examines {@code true} or {@code false} based on the first first byte of
     * {@link #array}
     * 
     * @return true if array[0] is not zero (or {@code false} if it is.
     */
    public Object asBoolean() {
        boolean result = true;
        if (array[0] == 0) {
            result = false;
        }
        return result;
    }

    public Object asChar() {
        return PrimitiveUtil.toChar(array, 0);
    }

    public Object asBoxedByte() {

        return (Byte) asByte();
    }

    public Short asBoxedShort() {
        return (Short) asShort();
    }

    public Object asBoxedInt() {
        return (Integer) asInt();
    }

    public Object asBoxedLong() {
        return (Long) asLong();
    }

    public Object asBoxedFloat() {
        return (Float) asFloat();
    }

    public Object asBoxedDouble() {
        return (Double) asDouble();
    }

    public Object asBoxedBoolean() {
        return (Boolean) asBoolean();
    }

    public Object asBoxedChar() {
        return (Character) asChar();
    }

}
