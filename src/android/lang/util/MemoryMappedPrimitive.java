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

package android.lang.util;

/**
 * {@code ArrayBackedPrimitive} allows primitive and arrays of primitives to be
 * converted easily to other primitive types. This class can be used for
 * marshalling operations to serialize (or deserialize) primitives and arrays of
 * primitives into (or out of) byte arrays.
 * <p>
 * Whenever a constructor or a {@code setXXX} operation is called, this class
 * serializes the data into a byte array.
 * <p>
 * <h3>Boolean Values and Boolean Arrays</h3>
 * <p>
 * {@code boolean} arrays are packed into bit fields using a specific data
 * format. This allows boolean arrays (e.g. {@code boolean[]} or
 * {@code Boolean[]}) to be stored in as compact of a format as possible. As a
 * result, there are some specific semantics around boolean values to consider
 * when using this class.
 * <p>
 * Calling {@link #set(boolean[])} will store the boolean array in the following
 * format: the first two bytes the underlying array will contain the length of
 * the boolean array (as a short value), and each <b>bit</b> in the remainder of
 * the byte array will be either a 1 ({@code true} or 0 ({@code true}).
 * <p>
 * Calling {@link #set(boolean)} will cause the first bye of the underlying
 * array to be set to either 1 ( {@code true}) or 0 {@code false}.
 * <p>
 * calling {@link #set(boolean)} will affect the underlying byte array to
 * contain different values than #set(boolean[]}. Specifically:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * {@code ((boolean[])asType(boolean[].class))[0] != ((boolean)asType(boolean.class))}
 * </pre>
 * 
 * </blockquote>
 * <p>
 * <p>
 * <h3>Examples</h3>
 * <p>
 * To convert an array of floats to a byte array:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * float[] floatArray = ...;
 * byte[] serializedFloats = new ArrayBackedPrimitive(floatArray).asType(byte[].class);
 * </pre>
 * 
 * </blockquote>
 * <p>
 * To convert an array of bytes to other types of data structures:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * byte[] byteArray = ...;
 * long[] longValues = new ArrayBackedPrimitive(byteArray).asType(long[].class);
 * </pre>
 * 
 * </blockquote>
 * <p>
 * To convert an an array of one primitive into a primitive array of another
 * type:
 * <p>
 * <blockquote>
 * 
 * <pre>
 * double[] doubleArray = 0d, 1d, 2d, 3d, 4d }; // convert an array of
 * doubles into an array of 20 shorts short[] shortValues = new
 * ArrayBackedPrimitive(doubleArray).asType(short[].class);
 * </pre>
 * 
 * </blockquote>
 * 
 * @author Richard Schilling
 * @since 1.0
 * @param <T> the type of primitive this object was created for
 */
public final class MemoryMappedPrimitive {

    /**
     * The array that contains this primitive's data.
     * 
     * @since 1.0
     */
    private byte[] mArray;

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(byte value) {
        mArray = new byte[1];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(short value) {
        mArray = new byte[2];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(int value) {
        mArray = new byte[4];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(long value) {
        mArray = new byte[8];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(float value) {
        mArray = new byte[4];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(double value) {
        mArray = new byte[8];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(boolean value) {
        mArray = new byte[1];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(char value) {
        mArray = new byte[2];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(byte[] value) {
        mArray = value;
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(short[] value) {
        mArray = new byte[2];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(int[] value) {
        mArray = new byte[4];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(long[] value) {
        mArray = new byte[8];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(float[] value) {
        mArray = new byte[4];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(double[] value) {
        mArray = new byte[8];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(boolean[] value) {
        mArray = new byte[1];
        set(value);
    }

    /**
     * Create a new object with the initial value. The value will be overwritten
     * by any call to a {@code setXXX} method.
     * 
     * @param value the object's initial value.
     * @since 1.0
     */
    public MemoryMappedPrimitive(char[] value) {
        mArray = new byte[2];
        set(value);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(byte value) {
        mArray[0] = value;
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(short value) {
        PrimitiveUtil.toBytes(value, mArray, 0);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(int value) {
        PrimitiveUtil.toBytes(value, mArray, 0);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(long value) {
        PrimitiveUtil.toBytes(value, mArray, 0);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(float value) {
        PrimitiveUtil.toBytes(value, mArray, 0);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(double value) {
        PrimitiveUtil.toBytes(value, mArray, 0);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(boolean value) {
        PrimitiveUtil.toBytes(value, mArray, 0);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(char value) {
        PrimitiveUtil.toBytes(value, mArray, 0);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(byte[] value) {
        mArray = value;
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(short[] value) {
        mArray = PrimitiveUtil.toBytes(value);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(int[] value) {
        mArray = PrimitiveUtil.toBytes(value);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(long[] value) {
        mArray = PrimitiveUtil.toBytes(value);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(float[] value) {
        mArray = PrimitiveUtil.toBytes(value);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(double[] value) {
        mArray = PrimitiveUtil.toBytes(value);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(boolean[] value) {
        mArray = PrimitiveUtil.toBytes(value);
    }

    /**
     * Sets the value of the primitive.
     * 
     * @param value the value to set this object to.
     * @since 1.0
     */
    public void set(char[] value) {
        mArray = PrimitiveUtil.toBytes(value);
    }

    /**
     * Converts whatever value this object contains into a specified type. If
     * there are not enough bytes to return a proper value an exception is
     * thrown:
     * <p>
     * <blockquote>
     * 
     * <pre>
     * // throws an exception because an int needs at least four bytes.
     * int value = new ArrayBackedPrimitive((byte) 0).asInt();
     * </pre>
     * 
     * </blockquote>
     * <p>
     * Calling {@code asType(byte[].class)} will return the underlying byte
     * array that stores the data.
     * 
     * @param value type of value to return
     * @since 1.0
     * @throws RuntimeException if the underlying byte array cannot be converted
     *             to the specified {@code type}.
     */
    public Object asType(Class<?> type) {
        return type.isPrimitive() ? asPrimitiveType(type) : asPrimitiveArray(type);
    }

    private Object asPrimitiveType(Class<?> type) {
        if (!type.isPrimitive()) {
            throw new IllegalArgumentException(type.getName()
                    + " is not a primitive ");
        }
        if (type == byte.class) {
            return asByte();
        }

        if (type == Byte.class) {
            return (Byte) asByte();
        }

        if (type == short.class) {
            return asShort();
        }

        if (type == Short.class) {
            return (Short) asShort();
        }

        if (type == int.class) {
            return asInt();
        }

        if (type == Integer.class) {
            return (Integer) asInt();
        }

        if (type == long.class) {
            return asLong();
        }

        if (type == Long.class) {
            return (Long) asLong();
        }

        if (type == float.class) {
            return asFloat();
        }

        if (type == Float.class) {
            return (Float) asFloat();
        }

        if (type == double.class) {
            return asDouble();
        }

        if (type == Double.class) {
            return (Double) asDouble();
        }

        if (type == boolean.class) {
            return asBoolean();
        }

        if (type == Boolean.class) {
            return (Boolean) asBoolean();
        }

        if (type == char.class) {
            return asChar();
        }

        if (type == Character.class) {
            return (Character) asChar();
        }

        throw new IllegalArgumentException("unable to convert this.array to type " + type.getName());
    }

    private Object asPrimitiveArray(Class<?> type) {

        if (!type.isArray() && !type.getComponentType().isPrimitive()) {
            throw new IllegalArgumentException(type.getName()
                    + " is not a primitive array ");
        }
        if (type == byte[].class) {
            return mArray;
        }

        if (type == Byte[].class) {
            return PrimitiveUtil.box(mArray);
        }

        if (type == short[].class) {
            return PrimitiveUtil.toShortArray(mArray);
        }

        if (type == Short[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toShortArray(mArray));
        }

        if (type == int[].class) {
            return PrimitiveUtil.toIntArray(mArray);
        }

        if (type == Integer[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toIntArray(mArray));
        }

        if (type == long[].class) {
            return PrimitiveUtil.toLongArray(mArray);

        }

        if (type == Long[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toLongArray(mArray));
        }

        if (type == float[].class) {
            return PrimitiveUtil.toFloatArray(mArray);
        }

        if (type == Float[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toFloatArray(mArray));
        }

        if (type == double[].class) {
            return PrimitiveUtil.toDoubleArray(mArray);
        }

        if (type == Double[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toDoubleArray(mArray));
        }

        if (type == boolean[].class) {
            return PrimitiveUtil.toBooleanArray(mArray);
        }

        if (type == Boolean[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toBooleanArray(mArray));
        }

        if (type == char[].class) {
            return PrimitiveUtil.toCharArray(mArray);
        }

        if (type == Character[].class) {
            return PrimitiveUtil.box(PrimitiveUtil.toCharArray(mArray));
        }

        throw new UnsupportedOperationException("Unable to convert this.array to primitive array "
                + type.getName());
    }

    private byte asByte() {
        return mArray[0];
    }

    private short asShort() {
        if (mArray.length < 2) {
            throw new UnsupportedOperationException("not enough bytes to read a short.");
        }
        return PrimitiveUtil.toShort(mArray, 0);
    }

    private int asInt() {
        return PrimitiveUtil.toInt(mArray, 0);
    }

    private long asLong() {
        return PrimitiveUtil.toLong(mArray, 0);
    }

    private float asFloat() {
        return PrimitiveUtil.toFloat(mArray, 0);
    }

    private double asDouble() {
        return PrimitiveUtil.toDouble(mArray, 0);
    }

    private boolean asBoolean() {
        boolean result = true;
        if (mArray[0] == 0) {
            result = false;
        }
        return result;
    }

    private char asChar() {
        return PrimitiveUtil.toChar(mArray, 0);
    }

}
