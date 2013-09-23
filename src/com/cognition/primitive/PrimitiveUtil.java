
package com.cognition.primitive;

import com.cognition.reflect.ClassReflectionUtil;

public final class PrimitiveUtil {

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
    public static final short[] toShortArray(byte[] array) {

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
    public static final int[] toIntArray(byte[] array) {
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
    public static final long[] toLongArray(byte[] array) {
        throwIfBadArray(array, 8);
        long[] result = new long[array.length / 8];
        for (int i = 0; i < array.length; i += 8) {
            result[i / 8] = toLong(array, i);
        }

        return result;

    }

    public static final float[] toFloatArray(byte[] array) {
        throwIfBadArray(array, 4);
        float[] result = new float[array.length / 4];
        for (int i = 0; i < array.length; i += 4) {
            result[i / 4] = toFloat(array, i);
        }

        return result;

    }

    public static final double[] toDoubleArray(byte[] array) {
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
    public static final boolean[] toBooleanArray(byte[] array) {

        short resultSz = toShort(array, 0);
        boolean[] result = new boolean[resultSz];

        for (int i = 0; i < resultSz; i++) {
            int bytePos = 2 + (i / 8);
            int bitPos = i & 8;

            int mask = (1 << (7 - bitPos)) & 0xff;

            byte bits = (byte) (mask & array[bytePos]);
            result[i] = bits > 0 ? true : false;

        }

        return result;

    }

    public static final char[] toCharArray(byte[] array) {

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

    public static final short toShort(byte[] array, int start) {

        throwIfBadArraySize(array, start, 2);
        int b1 = array[start] & 0xff;
        int b2 = array[start + 1] & 0xff;
        return (short) ((b1 << 8) + b2);

    }

    /**
     * Converts four bytes of an array starting in position {@code start} into
     * an integer.
     * 
     * @param array the array containing bytes
     * @param start the position in array to start at.
     * @return an integer built out of the next four bytes of the array.
     */
    public static final int toInt(byte[] array, int start) {
        throwIfBadArraySize(array, start, 4);
        int b1 = array[start] & 0xff;
        int b2 = array[start + 1] & 0xff;
        int b3 = array[start + 2] & 0xff;
        int b4 = array[start + 3] & 0xff;
        return ((b1 << 24) + (b2 << 16) + (b3 << 8) + b4);

    }

    public static final long toLong(byte[] array, int start) {
        throwIfBadArraySize(array, start, 8);
        int b1 = array[start] & 0xff;
        int b2 = array[start + 1] & 0xff;
        int b3 = array[start + 2] & 0xff;
        int b4 = array[start + 3] & 0xff;
        int b5 = array[start + 4] & 0xff;
        int b6 = array[start + 5] & 0xff;
        int b7 = array[start + 6] & 0xff;
        int b8 = array[start + 7] & 0xff;

        return ((b1 << 56) + (b2 << 48) + (b3 << 40) + (b4 << 32) + (b5 << 24) + (b6 << 16)
                + (b7 << 8) + b8);

    }

    public static final float toFloat(byte[] array, int start) {

        return Float.intBitsToFloat(toInt(array, start));

    }

    public static final double toDouble(byte[] array, int start) {

        return Double.longBitsToDouble(toLong(array, start));

    }

    public static final char toChar(byte[] array, int start) {

        throwIfBadArraySize(array, start, 2);
        int b1 = array[start] & 0xff;
        int b2 = array[start + 1] & 0xff;
        return (char) ((b1 << 8) + b2);

    }

    public static final byte[] toBytes(Short[] array) {

        byte[] result = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 2);
        }

        return result;
    }

    public static final byte[] toBytes(Integer[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 4);
        }

        return result;
    }

    public static final byte[] toBytes(Long[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 8);
        }

        return result;
    }

    public static final byte[] toBytes(Float[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i] == null ? 0 : array[i], result, i * 4);
        }

        return result;
    }

    public static final byte[] toBytes(Double[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                toBytes(array[i] == null ? 0 : array[i], result, i * 8);
            }
        }

        return result;
    }

    public static final byte[] toBytes(Boolean[] array) {

        if (array == null) {
            return null;
        }

        boolean[] bArray = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            bArray[i] = array[i] == null ? false : array[i];
        }

        return toBytes(bArray);

    }

    public static final byte[] toBytes(Character[] array) {

        if (array == null) {
            return null;
        }

        char[] bArray = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            bArray[i] = array[i] == null ? '\u0000' : array[i];
        }

        return toBytes(bArray);

    }

    public static final byte[] toBytes(short[] array) {

        byte[] result = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 2);
        }

        return result;
    }

    public static final byte[] toBytes(int[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 4);
        }

        return result;

    }

    public static final byte[] toBytes(long[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 8);
        }

        return result;

    }

    public static final byte[] toBytes(float[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 4);
        }

        return result;

    }

    public static final byte[] toBytes(double[] array) {

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
    public static final byte[] toBytes(boolean[] array) {
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

            if (!array[i]) {
                mask = ~mask;
            }

            result[bytePos] = (byte) (result[bytePos] & mask);

        }

        return result;
    }

    public static final byte[] toBytes(char[] array) {

        byte[] result = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 2);
        }

        return result;
    }

    public static final void toBytes(short value, byte[] dest, int start) {

        dest[start] = (byte) (value >>> 8);
        dest[start + 1] = (byte) (value);

    }

    public static final void toBytes(int value, byte[] dest, int start) {

        dest[start] = (byte) (value >>> 24);
        dest[start + 1] = (byte) (value >>> 16);
        dest[start + 2] = (byte) (value >>> 8);
        dest[start + 3] = (byte) value;

    }

    public static final void toBytes(long value, byte[] dest, int start) {

        dest[start] = (byte) (value >>> 56);
        dest[start + 1] = (byte) (value >>> 48);
        dest[start + 2] = (byte) (value >>> 40);
        dest[start + 3] = (byte) (value >>> 32);
        dest[start + 4] = (byte) (value >>> 24);
        dest[start + 5] = (byte) (value >>> 16);
        dest[start + 6] = (byte) (value >>> 8);
        dest[start + 7] = (byte) value;

    }

    public static final void toBytes(float value, byte[] dest, int start) {

        toBytes(Float.floatToIntBits(value), dest, start);

    }

    public static final void toBytes(double value, byte[] dest, int start) {

        toBytes(Double.doubleToLongBits(value), dest, start);

    }

    public static final void toBytes(char value, byte[] dest, int start) {

        dest[start] = (byte) (value >>> 8);
        dest[start + 1] = (byte) (value);

    }

    public static Byte[] box(byte[] array) {
        Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Byte.valueOf(array[i]);
        }
        return result;
    }

    public static final Short[] box(short[] array) {
        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Short.valueOf(array[i]));
        }
        return result;
    }

    public static final Integer[] box(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Integer.valueOf(array[i]));
        }
        return result;
    }

    public static final Long[] box(long[] array) {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Long.valueOf(array[i]));
        }
        return result;
    }

    public static final Float[] box(float[] array) {
        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Float.valueOf(array[i]));
        }
        return result;
    }

    public static final Double[] box(double[] array) {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Double.valueOf(array[i]));
        }
        return result;
    }

    public static final Boolean[] box(boolean[] array) {
        Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Boolean.valueOf(array[i]));
        }
        return result;
    }

    public static final Character[] box(char[] array) {
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = (Character.valueOf(array[i]));
        }
        return result;
    }

    public static byte[] unbox(Byte[] array) {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].byteValue();
        }
        return result;
    }

    public static short[] unbox(Short[] array) {
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].shortValue();
        }

        return result;
    }

    public static int[] unbox(Integer[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].intValue();
        }

        return result;
    }

    public static long[] unbox(Long[] array) {
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0 : array[i].longValue();
        }

        return result;
    }

    public static float[] unbox(Float[] array) {
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0.0f : array[i].floatValue();
        }

        return result;
    }

    public static double[] unbox(Double[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? 0.0d : array[i].doubleValue();
        }

        return result;
    }

    public static boolean[] unbox(Boolean[] array) {
        boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? false : array[i].booleanValue();
        }

        return result;
    }

    public static char[] unbox(Character[] array) {
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] == null ? '\u0000' : array[i].charValue();
        }

        return result;
    }

}
