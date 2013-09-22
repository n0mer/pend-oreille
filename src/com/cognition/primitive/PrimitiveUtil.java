
package com.cognition.primitive;

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
     * toBytes(int[]) was used to create the array.
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
        throwIfBadArray(array, 4);
        int b1 = array[start] & 0xff;
        int b2 = array[start + 1] & 0xff;
        int b3 = array[start + 2] & 0xff;
        int b4 = array[start + 3] & 0xff;
        return ((b1 << 24) + (b2 << 16) + (b3 << 8) + b4);

    }

    public static final long toLong(byte[] array, int start) {

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

    public static final byte[] toBytes(Short[] array) {

        byte[] result = new byte[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 2);
        }

        return result;
    }

    public static final byte[] toBytes(Integer[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 4);
        }

        return result;
    }

    public static final byte[] toBytes(Long[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 8);
        }

        return result;
    }

    public static final byte[] toBytes(Float[] array) {

        byte[] result = new byte[array.length * 4];
        for (int i = 0; i < array.length; i++) {
            toBytes(array[i], result, i * 4);
        }

        return result;
    }

    public static final byte[] toBytes(Double[] array) {

        byte[] result = new byte[array.length * 8];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                toBytes(array[i], result, i * 8);
            }
        }

        return result;
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

}
