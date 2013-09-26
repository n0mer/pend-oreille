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

package com.cognition.primitive;

/**
 * Contains functions required by {@link ArrayBackedPrimitive}.
 * 
 * @author Richard Schilling
 * @since 1.0
 */
/* default */final class PrimitiveUtil {

    private PrimitiveUtil() {
        throw new UnsupportedOperationException("instantiating not allowed.");

    }

    /**
     * Throw an exception if the length of the array is not evenly divisible by
     * {@code div}.
     * 
     * @param array the array to check.
     * @param mod the number to use in the modulus calculation
     * @throws IllegalArgumentException if {@code array.length % div != 0}; if
     *             div <= 0; or if array == null.
     */
    private static final void throwIfBadArray(byte[] array, int div) {

        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        if (div <= 0) {
            throw new IllegalArgumentException("div must be >= 0");
        }

        if ((array.length % 2) != 0) {
            throw new IllegalArgumentException("array length is not evenly divisible by " + div);
        }

    }

    /**
     * Converts an array of bytes into an array of shorts.
     * 
     * @param array the array to convert
     * @return an array of short values.
     */
    /* default */static final short[] toShortArray(byte[] array) {

        throwIfBadArray(array, 2);

        short[] result = new short[array.length / 2];
        for (int i = 0; i < array.length; i += 2) {
            result[i / 2] = toShort(array, i);
        }

        return result;

    }

    /**
     * Converts an array of bytes into an array of integers. Assumes that
     * {@link #toBytes(int[])}, or {@link #toBytes(Integer[])} was used to
     * create the array.
     * 
     * @param array the array to convert.
     * @return an array of integers.
     */
    /* default */static final int[] toIntArray(byte[] array) {
        throwIfBadArray(array, 4);
        int[] result = new int[array.length / 4];
        for (int i = 0; i < array.length; i += 4) {
            result[i / 4] = toInt(array, i);
        }

        return result;

    }

    /**
     * Converts an array of bytes into an array of longs. Assumes that
     * toBytes(long[]) was used to create the array.
     * 
     * @param array the array to convert.
     * @return an array of integers.
     */
    /* default */static final long[] toLongArray(byte[] array) {
        throwIfBadArray(array, 8);
        long[] result = new long[array.length / 8];
        for (int i = 0; i < array.length; i += 8) {
            result[i / 8] = toLong(array, i);
        }

        return result;

    }

    /* default */static final float[] toFloatArray(byte[] array) {
        throwIfBadArray(array, 4);
        float[] result = new float[array.length / 4];
        for (int i = 0; i < array.length; i += 4) {
            result[i / 4] = toFloat(array, i);
        }

        return result;

    }

    /* default */static final double[] toDoubleArray(byte[] array) {
        throwIfBadArray(array, 8);
        double[] result = new double[array.length / 8];
        for (int i = 0; i < array.length; i += 8) {
            result[i / 8] = toDouble(array, i);
        }

        return result;

    }

    /**
     * Starting at {@code start}, the first two bytes are read as a short for
     * the length of the returned array.
     * 
     * @param array the array to convert
     * @param start the byte position to start in.
     * @return an array of boolean values extracted from the bit field.
     */
    /* default */static final boolean[] toBooleanArray(byte[] array) {

        short resultSz = toShort(array, 0);
        boolean[] result = new boolean[resultSz];

        for (int i = 0; i < resultSz; i++) {
            int bytePos = 2 + (i / 8);
            int bitPos = i % 8;

            int mask = (1 << (7 - bitPos)) & 0xff;

            byte bits = (byte) (mask & array[bytePos]);
            result[i] = bits != 0 ? true : false;

        }

        return result;

    }

    /* default */static final char[] toCharArray(byte[] array) {

        throwIfBadArray(array, 2);

        char[] result = new char[array.length / 2];
        for (int i = 0; i < array.length; i += 2) {
            result[i / 2] = toChar(array, i);
        }

        return result;

    }

    /**
     * makes sure that there are enough bytes in the array to make a conversion
     * 
     * @param array the array to check
     * @param start the index of the array to start checking at
     * @param bytesRequired the number of bytes, including {@code start} that
     *            must be available.
     * @throws IllegalArgumentException if array is null or there aren't enough
     *             bytes to perform a converstion.
     */
    private static final void throwIfBadArraySize(byte[] array, int start, int bytesRequried) {

        if (array == null) {
            throw new IllegalArgumentException("array cannot be null");
        }

        if (array.length - start < bytesRequried) {
            throw new IllegalArgumentException(bytesRequried + " bytes needed starting at " + start
                    + ". found " + (array.length - bytesRequried));
        }
    }

    /* default */static final short toShort(byte[] array, int start) {

        throwIfBadArraySize(array, start, 2);
        short s1 = (short) (array[start] & 0xff);
        short s2 = (short) (array[start + 1] & 0xff);

        return (short) ((s1 << 8) | s2);

    }

    /**
     * Converts four bytes of an array starting in position {@code start} into
     * an integer.
     * 
     * @param array the array containing bytes
     * @param start the position in array to start at.
     * @return an integer built out of the next four bytes of the array.
     */
    /* default */static final int toInt(byte[] array, int start) {
        throwIfBadArraySize(array, start, 4);

        int i1 = toShort(array, start);
        int i2 = toShort(array, start + 2);

        return (i1 << 16) | i2;

    }

    /* default */static final long toLong(byte[] array, int start) {
        throwIfBadArraySize(array, start, 8);

        long l1 = toInt(array, start);
        long l2 = toInt(array, start + 4);

        return (l1 << 32) | l2;

    }

    /* default */static final float toFloat(byte[] array, int start) {

        return Float.intBitsToFloat(toInt(array, start));

    }

    /* default */static final double toDouble(byte[] array, int start) {

        return Double.longBitsToDouble(toLong(array, start));

    }

    /* default */static final char toChar(byte[] array, int start) {

        throwIfBadArraySize(array, start, 2);
        int b1 = (array[start] & 0xff) << 8;
        int b2 = array[start + 1] & 0xff;
        return (char) (b1 | b2);

    }

    /* default */static final byte[] toBytes(Short[] array) {

        byte[] result = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 2);
        }

        return result;
    }

    /* default */static final byte[] toBytes(Integer[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 4);
        }

        return result;
    }

    /* default */static final byte[] toBytes(Long[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 8);
        }

        return result;
    }

    /* default */static final byte[] toBytes(Float[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 4);
        }

        return result;
    }

    /* default */static final byte[] toBytes(Double[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                toBytes(array[i] == null ? 0 : array[i], result, i * 8);
            }
        }

        return result;
    }

    /* default */static final byte[] toBytes(Boolean[] array) {

        if (array == null) {
            return null;
        }

        boolean[] bArray = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            bArray[i] = array[i] == null ? false : array[i];
        }

        return toBytes(bArray);

    }

    /* default */static final byte[] toBytes(Character[] array) {

        if (array == null) {
            return null;
        }

        char[] bArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            bArray[i] = array[i] == null ? '\u0000' : array[i];
        }

        return toBytes(bArray);

    }

    /* default */static final byte[] toBytes(short[] array) {

        byte[] result = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 2);
        }

        return result;
    }

    /* default */static final byte[] toBytes(int[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 4);
        }

        return result;

    }

    /* default */static final byte[] toBytes(long[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 8);
        }

        return result;

    }

    /* default */static final byte[] toBytes(float[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 4);
        }

        return result;

    }

    /* default */static final byte[] toBytes(double[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {

            toBytes(array[i], result, i * 8);

        }

        return result;
    }

    /**
     * Stores an array of bytes in the following format:
     * <ol>
     * <li>The first two bytes contain an short value created by toBytes(short,
     * byte[]) which contain the number of bits that are set.</li>
     * <li>The remainder of the array contains bytes - buits of 1 indicate true
     * and bits of 0 indicate false
     * </ol>
     * The bits in the array are stored in the same order as the booleans in the
     * array.
     * 
     * @param array the array to convert
     * @return a byte array
     */
    /* default */static final byte[] toBytes(boolean[] array) {
        if (array == null) {
            return null;
        }

        if (array.length > Short.MAX_VALUE) {
            throw new IllegalArgumentException("boolean arrays must have a length of "
                    + Short.MAX_VALUE + " or less");
        }

        int sz = 2; // for length value
        sz += (array.length / 8);
        if ((array.length % 8) != 0) {
            sz++;

        }

        byte[] result = new byte[sz];

        // write the length
        toBytes((short) array.length, result, 0);

        for (int i = 0; i < array.length; i++) {

            // calculate byte position
            int bytePos = 2 + i / 8;
            int bitPos = i % 8;
            int mask = (1 << (7 - bitPos)) & 0xff;

            if (array[i]) {
                result[bytePos] = (byte) (result[bytePos] | mask);
            } else {
                mask = ~mask;
                result[bytePos] = (byte) (result[bytePos] & mask);
            }

        }

        return result;
    }

    /* default */static final byte[] toBytes(char[] array) {

        byte[] result = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 2);
        }

        return result;
    }

    /* default */static final void toBytes(short value, byte[] dest, int start) {

        dest[start] = (byte) (value >>> 8);
        dest[start + 1] = (byte) (value);

    }

    /* default */static final void toBytes(int value, byte[] dest, int start) {

        short s1 = (short) (value >>> 16);
        short s2 = (short) value;

        toBytes(s1, dest, start);
        toBytes(s2, dest, start + 2);

    }

    /* default */static final void toBytes(long value, byte[] dest, int start) {
        int i1 = (int) (value >>> 32);
        int i2 = (int) value;

        toBytes(i1, dest, start);
        toBytes(i2, dest, start + 4);

    }

    /* default */static final void toBytes(float value, byte[] dest, int start) {

        toBytes(Float.floatToIntBits(value), dest, start);

    }

    /* default */static final void toBytes(double value, byte[] dest, int start) {

        toBytes(Double.doubleToLongBits(value), dest, start);

    }

    /* default */static final void toBytes(boolean value, byte[] dest, int start) {
        dest[start] = value ? (byte) 1 : (byte) 0;
    }

    /* default */static final void toBytes(char value, byte[] dest, int start) {

        dest[start] = (byte) (value >>> 8);
        dest[start + 1] = (byte) (value);

    }

    /* default */static Byte[] box(byte[] array) {
        Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Byte.valueOf(array[i]);
        }
        return result;
    }

    /* default */static final Short[] box(short[] array) {
        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Short.valueOf(array[i]));
        }
        return result;
    }

    /* default */static final Integer[] box(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Integer.valueOf(array[i]));
        }
        return result;
    }

    /* default */static final Long[] box(long[] array) {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Long.valueOf(array[i]));
        }
        return result;
    }

    /* default */static final Float[] box(float[] array) {
        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Float.valueOf(array[i]));
        }
        return result;
    }

    /* default */static final Double[] box(double[] array) {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Double.valueOf(array[i]));
        }
        return result;
    }

    /* default */static final Boolean[] box(boolean[] array) {
        Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Boolean.valueOf(array[i]));
        }
        return result;
    }

    /* default */static final Character[] box(char[] array) {
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Character.valueOf(array[i]));
        }
        return result;
    }

    /* default */static byte[] unbox(Byte[] array) {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].byteValue();
        }
        return result;
    }

    /* default */static short[] unbox(Short[] array) {
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].shortValue();
        }

        return result;
    }

    /* default */static int[] unbox(Integer[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].intValue();
        }

        return result;
    }

    /* default */static long[] unbox(Long[] array) {
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].longValue();
        }

        return result;
    }

    /* default */static float[] unbox(Float[] array) {
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0.0f : array[i].floatValue();
        }

        return result;
    }

    /* default */static double[] unbox(Double[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0.0d : array[i].doubleValue();
        }

        return result;
    }

    /* default */static boolean[] unbox(Boolean[] array) {
        boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? false : array[i].booleanValue();
        }

        return result;
    }

    /* default */static char[] unbox(Character[] array) {
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? '\u0000' : array[i].charValue();
        }

        return result;
    }

}
