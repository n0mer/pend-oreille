
package android.lang.util;

import android.annotation.SuppressLint;
import junit.framework.TestCase;

public class MemoryMappedPrimitiveTest extends TestCase {

    /*
     * Test the constructors of MemoryMappedPrimitive to be sure the underlying
     * byte array has an expected length. This also exercises this check for
     * 'set' functions.
     */
    public void testConstructorByte() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive((byte) 0);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 1);

    }

    public void testConstructorShort() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive((short) 0);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 2);

    }

    public void testConstructorInteger() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive((int) 0);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 4);

    }

    public void testConstructorLong() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive((long) 0);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 8);

    }

    public void testConstructorFloat() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive((float) 0);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 4);

    }

    public void testConstructorDouble() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive((double) 0);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 8);

    }

    public void testConstructorBoolean() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(true);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 1);

    }

    public void testConstructorChar() {

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive((char) 0);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue("byte array length incorrect.  Expected 1, but found " + bytesUnderTest.length,
                bytesUnderTest.length == 2);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorByteArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE;

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new byte[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorShortArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE * 2;

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new short[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorIntArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE * 4;

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new int[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorLongArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE * 8;

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new long[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorFloatArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE * 4;

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new float[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorDoubleArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE * 8;

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new double[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorBooleanArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE / 8 + 2;
        if (PrimitiveUtilTest.SIZE % 8 != 0) {
            expectedByteCount++;
        }

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new boolean[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    @SuppressLint("DefaultLocale")
    public void testConstructorCharArray() {

        int expectedByteCount = PrimitiveUtilTest.SIZE * 2;

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(
                new char[PrimitiveUtilTest.SIZE]);
        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

    }

    private long[] getLongArray() {
        long[] result = new long[PrimitiveUtilTest.SIZE];
        for (int i = 0; i < PrimitiveUtilTest.SIZE; i++) {
            result[i] = 0xFFFFFFFFFFFFFFFFL;
        }

        return result;
    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeByte() {
        int expectedByteCount = PrimitiveUtilTest.SIZE * 8;
        long[] input = getLongArray();

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found 0x%02X, but expected 0xFF",
                            i, bytesUnderTest[i]), bytesUnderTest[i] == (byte) 0xFF);
        }

    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeShort() {
        int expectedByteCount = PrimitiveUtilTest.SIZE * 4;
        long[] input = getLongArray();

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        short[] bytesUnderTest = (short[]) objectUnderTest.asType(short[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found 0x%02X, but expected 0xFF",
                            i, bytesUnderTest[i]), bytesUnderTest[i] == (short) 0xFFFF);
        }

    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeInt() {
        int expectedByteCount = PrimitiveUtilTest.SIZE * 2;
        long[] input = getLongArray();

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        int[] bytesUnderTest = (int[]) objectUnderTest.asType(int[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found 0x%02X, but expected 0xFF",
                            i, bytesUnderTest[i]), bytesUnderTest[i] == 0xFFFFFFFF);
        }

    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeLong() {
        int expectedByteCount = PrimitiveUtilTest.SIZE;
        long[] input = getLongArray();

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        long[] bytesUnderTest = (long[]) objectUnderTest.asType(long[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found 0x%02X, but expected 0xFF",
                            i, bytesUnderTest[i]), bytesUnderTest[i] == 0xFFFFFFFFFFFFFFFFL);
        }

    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeFloat() {

        float expected = Float.intBitsToFloat(0x0FFFFFFF);
        int expectedByteCount = PrimitiveUtilTest.SIZE * 2;
        long[] input = new long[PrimitiveUtilTest.SIZE];
        for (int i = 0; i < PrimitiveUtilTest.SIZE; i++) {
            input[i] = 0x0FFFFFFF0FFFFFFFL;
        }

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        float[] bytesUnderTest = (float[]) objectUnderTest.asType(float[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found 0x%s, but expected 0xFF",
                            i, Integer.toHexString(Float.floatToIntBits(bytesUnderTest[i]))),
                    bytesUnderTest[i] == expected);
        }

    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeDouble() {
        int expectedByteCount = PrimitiveUtilTest.SIZE;
        long[] input = getLongArray();

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        double[] bytesUnderTest = (double[]) objectUnderTest.asType(double[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found %s, but expected 0xFF",
                            i, Long.toHexString(Double.doubleToRawLongBits(bytesUnderTest[i]))),
                    Double.doubleToRawLongBits(bytesUnderTest[i]) == 0xFFFFFFFFFFFFFFFFL);
        }

    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeBoolean() {
        int expectedByteCount = PrimitiveUtilTest.SIZE * 8;
        long[] input = getLongArray();

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        byte[] bytesUnderTest = (byte[]) objectUnderTest.asType(byte[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found 0x%02X, but expected 0xFF",
                            i, bytesUnderTest[i]), bytesUnderTest[i] == (byte) 0xFF);
        }

    }

    @SuppressLint("DefaultLocale")
    public void testAsTypeChar() {
        int expectedByteCount = PrimitiveUtilTest.SIZE * 4;
        long[] input = getLongArray();

        MemoryMappedPrimitive objectUnderTest = new MemoryMappedPrimitive(input);

        short[] bytesUnderTest = (short[]) objectUnderTest.asType(short[].class);

        assertTrue(String.format("byte array length incorrect.  Expected %d, but found %d",
                expectedByteCount, bytesUnderTest.length),
                bytesUnderTest.length == expectedByteCount);

        for (int i = 0; i < expectedByteCount; i++) {
            assertTrue(
                    String.format(
                            "unexpected value at position %d. Found 0x%02X, but expected 0xFF",
                            i, bytesUnderTest[i]), bytesUnderTest[i] == (short) 0xFFFF);
        }

    }

}
