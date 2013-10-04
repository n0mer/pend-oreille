
package android.lang.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import junit.framework.TestCase;
import android.annotation.SuppressLint;
import android.reflect.util.test.model.TestClass;
import android.util.Log;

@SuppressLint("DefaultLocale")
public class PrimitiveUtilTest extends TestCase {

    private static final String LOG_TAG = "PRIMITIVEUTILTEST";
    private static final int MIN_VALUE = -10000;
    private static final int MAX_VALUE = 10000;
    public static final int SIZE = 20; // 20000;

    private char[] charset = {
            '\u0021', '\u0022',
            '\u0023', '\u0024',
            '\u0025', '\u0026',
            '\u0028', '\u0029',
            '\u002A', '\u0030',
            '\u0031', '\u0032',
            '\u0033', '\u0034',
            '\u0035', '\u0036',
            '\u0037', '\u0038',
            '\u0039', '\u0040'
    };
    private TestClass testClass;

    public void setUp() {

        testClass = new TestClass();

    }

    public void testBoxByte() {

        byte[] source = testClass.byteArray;

        long start = System.currentTimeMillis();
        Byte[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxByte ms: " + elapsed);

        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testBoxShort() {

        short[] source = testClass.shortArray;
        long start = System.currentTimeMillis();
        Short[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxShort ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testBoxInt() {

        int[] source = testClass.intArray;
        long start = System.currentTimeMillis();
        Integer[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxInt ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testBoxLong() {

        long[] source = testClass.longArray;
        long start = System.currentTimeMillis();
        Long[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxLong ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testBoxFloat() {

        float[] source = testClass.floatArray;
        long start = System.currentTimeMillis();
        Float[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxFloat ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testBoxDouble() {

        double[] source = testClass.doubleArray;
        long start = System.currentTimeMillis();
        Double[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxDouble ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testBoxChar() {

        char[] source = testClass.charArray;
        long start = System.currentTimeMillis();
        Character[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxChar ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testBoxBoolean() {

        boolean[] source = testClass.booleanArray;
        long start = System.currentTimeMillis();
        Boolean[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testBoxBoolean ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxByte() {

        Byte[] source = testClass.boxByteArray;
        long start = System.currentTimeMillis();
        byte[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxByte ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxShort() {

        Short[] source = testClass.boxShortArray;
        long start = System.currentTimeMillis();
        short[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxShort ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxInt() {

        Integer[] source = testClass.boxIntArray;
        long start = System.currentTimeMillis();
        int[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxInt ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxLong() {

        Long[] source = testClass.boxLongArray;
        long start = System.currentTimeMillis();
        long[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxLong ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxFloat() {

        Float[] source = testClass.boxFloatArray;
        long start = System.currentTimeMillis();
        float[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxFloat ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxDouble() {

        Double[] source = testClass.boxDoubleArray;
        long start = System.currentTimeMillis();
        double[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxDouble ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxChar() {

        Character[] source = testClass.boxCharArray;
        long start = System.currentTimeMillis();
        char[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxChar ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testUnboxBoolean() {

        Boolean[] source = testClass.boxBooleanArray;
        long start = System.currentTimeMillis();
        boolean[] result = PrimitiveUtil.unbox(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d(LOG_TAG, "testUnboxBoolean ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

    public void testThrowIfBadArray() {
        boolean success = false;
        try {
            PrimitiveUtil.toCharArray(null);
        } catch (IllegalArgumentException ex) {

            success = true;

        }

        assertTrue("toCharArray accepted null parameter. Expected IllegalArgumentException",
                success);

        success = false;
        try {
            PrimitiveUtil.toCharArray(new byte[0]);
        } catch (IllegalArgumentException ex) {

            success = true;

        }

        assertTrue(
                "toCharArray accepted an even number length bytes as parameter. Expected IllegalArgumentException",
                success);

        success = false;
        try {
            PrimitiveUtil.toCharArray(new byte[1]);
        } catch (IllegalArgumentException ex) {

            success = true;

        }

        assertTrue(
                "toCharArray accepted an even number length bytes as parameter. Expected IllegalArgumentException",
                success);
    }

    private void assertEqualArrays(byte[] control, byte[] underTest) {
        for (int i = 0; i < control.length; i++) {
            assertTrue(String.format("bad byte generation at position %d. Found %d, expecting %d",
                    i, underTest[i], control[i]), underTest[i] == control[i]);

        }

    }

    /**
     * test serialization and deserialization of a type of value.
     */
    public void testToShort() {
        runToShort(Short.MIN_VALUE);
        runToShort(Short.MAX_VALUE);
    }

    private void runToShort(short value) {

        try {

            /*
             * set size
             */
            int size = 2;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            DataOutputStream dos = new DataOutputStream(baos);

            /*
             * write value to stream
             */
            dos.writeShort(value);

            byte[] testBytes = new byte[size];
            PrimitiveUtil.toBytes(value, testBytes, 0);

            assertEqualArrays(baos.toByteArray(), testBytes);

            /*
             * deserizliaze the value
             */
            short deserializedValue = PrimitiveUtil.toShort(testBytes, 0);

            assertTrue(String.format("deserialization failed. Found %d, expecting %d",
                    deserializedValue, value), deserializedValue == value);

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    /**
     * test serialization and deserialization of a type of value.
     */
    public void testToInt() {
        runToInt(Integer.MIN_VALUE);
        runToInt(Integer.MAX_VALUE);
    }

    private void runToInt(int value) {

        try {

            /*
             * set size
             */
            int size = 4;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            DataOutputStream dos = new DataOutputStream(baos);

            /*
             * write value to stream
             */
            dos.writeInt(value);

            byte[] testBytes = new byte[size];
            PrimitiveUtil.toBytes(value, testBytes, 0);

            assertEqualArrays(baos.toByteArray(), testBytes);

            /*
             * deserizliaze the value
             */
            int deserializedValue = PrimitiveUtil.toInt(testBytes, 0);

            assertTrue(String.format("deserialization failed. Found %d, expecting %d",
                    deserializedValue, value), deserializedValue == value);

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    /**
     * test serialization and deserialization of a type of value.
     */
    public void testToLong() {
        runToLong(Long.MIN_VALUE);
        runToLong(Long.MAX_VALUE);
    }

    private void runToLong(long value) {

        try {

            /*
             * set size
             */
            int size = 8;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            DataOutputStream dos = new DataOutputStream(baos);

            /*
             * write value to stream
             */
            dos.writeLong(value);

            byte[] testBytes = new byte[size];
            PrimitiveUtil.toBytes(value, testBytes, 0);

            assertEqualArrays(baos.toByteArray(), testBytes);

            /*
             * deserizliaze the value
             */
            long deserializedValue = PrimitiveUtil.toLong(testBytes, 0);

            assertTrue(String.format("deserialization failed. Found %d, expecting %d",
                    deserializedValue, value), deserializedValue == value);

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    /**
     * test serialization and deserialization of a type of value.
     */
    public void testToFloat() {
        runToFloat(Float.MIN_VALUE);
        runToFloat(Float.MAX_VALUE);
    }

    private void runToFloat(float value) {

        try {

            /*
             * set size
             */
            int size = 4;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            DataOutputStream dos = new DataOutputStream(baos);

            /*
             * write value to stream
             */
            dos.writeFloat(value);

            byte[] testBytes = new byte[size];
            PrimitiveUtil.toBytes(value, testBytes, 0);

            assertEqualArrays(baos.toByteArray(), testBytes);

            /*
             * deserizliaze the value
             */
            float deserializedValue = PrimitiveUtil.toFloat(testBytes, 0);

            assertTrue(String.format("deserialization failed. Found %f, expecting %f",
                    deserializedValue, value), deserializedValue == value);

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    /**
     * test serialization and deserialization of a type of value.
     */
    public void testToDouble() {
        runToDouble(Float.MIN_VALUE);
        runToDouble(Float.MAX_VALUE);
    }

    private void runToDouble(double value) {

        try {

            /*
             * set size
             */
            int size = 8;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            DataOutputStream dos = new DataOutputStream(baos);

            /*
             * write value to stream
             */
            dos.writeDouble(value);

            byte[] testBytes = new byte[size];
            PrimitiveUtil.toBytes(value, testBytes, 0);

            assertEqualArrays(baos.toByteArray(), testBytes);

            /*
             * deserizliaze the value
             */
            double deserializedValue = PrimitiveUtil.toDouble(testBytes, 0);

            assertTrue(String.format("deserialization failed. Found %f, expecting %f",
                    deserializedValue, value), deserializedValue == value);

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    /**
     * test serialization and deserialization of a type of value.
     */
    public void testToBoolean() {
        runToBoolean(Boolean.TRUE);
        runToBoolean(Boolean.FALSE);
    }

    private void runToBoolean(boolean value) {

        try {

            /*
             * set size
             */
            int size = 1;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            DataOutputStream dos = new DataOutputStream(baos);

            /*
             * write value to stream
             */
            dos.writeBoolean(value);

            byte[] testBytes = new byte[size];
            PrimitiveUtil.toBytes(value, testBytes, 0);

            assertEqualArrays(baos.toByteArray(), testBytes);

            /*
             * deserizliaze the value
             */
            boolean deserializedValue = PrimitiveUtil.toBoolean(testBytes, 0);

            assertTrue(String.format("deserialization failed. Found %s, expecting %s",
                    deserializedValue, value), deserializedValue == value);

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    /**
     * test serialization and deserialization of a type of value.
     */
    public void testToChar() {
        runToChar(Character.MIN_VALUE);
        runToChar(Character.MAX_VALUE);
    }

    private void runToChar(char value) {

        try {

            /*
             * set size
             */
            int size = 2;

            ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
            DataOutputStream dos = new DataOutputStream(baos);

            /*
             * write value to stream
             */
            dos.writeChar(value);

            byte[] testBytes = new byte[size];
            PrimitiveUtil.toBytes(value, testBytes, 0);

            assertEqualArrays(baos.toByteArray(), testBytes);

            /*
             * deserizliaze the value
             */
            char deserializedValue = PrimitiveUtil.toChar(testBytes, 0);

            assertTrue(String.format("deserialization failed. Found %s, expecting %s",
                    deserializedValue, value), deserializedValue == value);

        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

    }

    @SuppressLint("DefaultLocale")
    public void testToShortArray() {
        byte[] test = generateBytes(short.class);
        short[] control = generateShortArray();

        long start = System.currentTimeMillis();
        short[] result = PrimitiveUtil.toShortArray(test);
        long duration = System.currentTimeMillis() - start;

        Log.d(LOG_TAG,
                String.format("PrimitiveUtil generated %d bytes for type %s in %d ms.",
                        test.length,
                        short.class.getName(), duration));

        for (int i = 0; i < control.length; i++) {
            assertTrue(String.format("conversion failed on element %d, expected %d but found %d",
                    i, control[i], result[i]), result[i] == control[i]);

        }

    }

    public void testToIntArray() {
        byte[] test = generateBytes(int.class);
        int[] control = generateIntArray();

        long start = System.currentTimeMillis();
        int[] result = PrimitiveUtil.toIntArray(test);
        long duration = System.currentTimeMillis() - start;

        Log.d(LOG_TAG,
                String.format("PrimitiveUtil generated %d bytes for type %s in %d ms.",
                        test.length,
                        int.class.getName(), duration));

        for (int i = 0; i < control.length; i++) {
            assertTrue(String.format("conversion failed on element %d, expected %d but found %d",
                    i, control[i], result[i]), result[i] == control[i]);

        }

    }

    public void testToLongArray() {
        byte[] test = generateBytes(long.class);
        long[] control = generateLongArray();
        byte[] controlBytes = PrimitiveUtil.toBytes(control);

        long start = System.currentTimeMillis();
        long[] result = PrimitiveUtil.toLongArray(controlBytes);
        long duration = System.currentTimeMillis() - start;

        Log.d(LOG_TAG,
                String.format("PrimitiveUtil generated %d bytes for type %s in %d ms.",
                        test.length,
                        long.class.getName(), duration));

        for (int i = 0; i < control.length; i++) {
            assertTrue(String.format("conversion failed on element %d, expected %d but found %d",
                    i, control[i], result[i]), result[i] == control[i]);

        }

    }

    public void testToFloatArray() {
        byte[] test = generateBytes(float.class);
        float[] control = generateFloatArray();

        long start = System.currentTimeMillis();
        float[] result = PrimitiveUtil.toFloatArray(test);
        long duration = System.currentTimeMillis() - start;

        Log.d(LOG_TAG,
                String.format("PrimitiveUtil generated %d bytes for type %s in %d ms.",
                        test.length,
                        long.class.getName(), duration));

        for (int i = 0; i < control.length; i++) {
            assertTrue(String.format("conversion failed on element %d, expected %f but found %f",
                    i, control[i], result[i]), result[i] == control[i]);

        }

    }

    public void testToDoubleArray() {
        byte[] dataOutputStreamGeneratedBytes = generateBytes(double.class);

        double[] control = generateDoubleArray();
        byte[] controlBytes = PrimitiveUtil.toBytes(control);
        long start = System.currentTimeMillis();
        double[] result = PrimitiveUtil.toDoubleArray(controlBytes);
        long duration = System.currentTimeMillis() - start;

        Log.d(LOG_TAG,
                String.format("PrimitiveUtil generated %d bytes for type %s in %d ms.",
                        dataOutputStreamGeneratedBytes.length,
                        double.class.getName(), duration));

        for (int i = 0; i < control.length; i++) {
            assertTrue(String.format("conversion failed on element %d, expected %f but found %f",
                    i, control[i], result[i]), result[i] == control[i]);

        }

    }

    public void testToBooleanArray() {
        byte[] test = generateBytes(boolean.class);
        boolean[] control = generateBooleanArray();

        long start = System.currentTimeMillis();
        boolean[] result = PrimitiveUtil.toBooleanArray(test);
        long duration = System.currentTimeMillis() - start;

        Log.d(LOG_TAG,
                String.format("PrimitiveUtil generated %d bytes for type %s in %d ms.",
                        test.length,
                        boolean.class.getName(), duration));

        for (int i = 0; i < control.length; i++) {
            assertTrue(String.format("conversion failed on element %d, expected %s but found %s",
                    i, control[i], result[i]), result[i] == control[i]);

        }

    }

    public void testToCharArray() {
        byte[] test = generateBytes(char.class);
        char[] control = generateCharArray();

        long start = System.currentTimeMillis();
        char[] result = PrimitiveUtil.toCharArray(test);
        long duration = System.currentTimeMillis() - start;

        Log.d(LOG_TAG,
                String.format("PrimitiveUtil generated %d bytes for type %s in %d ms.",
                        test.length,
                        char.class.getName(), duration));

        for (int i = 0; i < control.length; i++) {
            assertTrue(
                    String.format("conversion failed on element %d, expected '%s' but found '%s'",
                            i, control[i], result[i]), result[i] == control[i]);

        }

    }

    private short[] generateShortArray() {

        short[] result = new short[SIZE];

        for (int i = 0; i < result.length; i++) {

            result[i] = (short) (MIN_VALUE + i);

        }

        return result;

    }

    private int[] generateIntArray() {

        int[] result = new int[SIZE];

        for (int i = 0; i < result.length; i++) {
            result[i] = (int) (MIN_VALUE + i);

        }

        return result;

    }

    private long[] generateLongArray() {

        long[] result = new long[SIZE];

        for (int i = 0; i < result.length; i++) {

            result[i] = (long) (MIN_VALUE + i);

        }

        return result;

    }

    private float[] generateFloatArray() {

        float[] result = new float[SIZE];

        for (int i = 0; i < result.length; i++) {

            result[i] = (float) (MIN_VALUE + i);

        }

        return result;

    }

    private double[] generateDoubleArray() {

        double[] result = new double[SIZE];

        for (int i = 0; i < result.length; i++) {

            result[i] = (double) (MIN_VALUE + i);

        }

        return result;

    }

    private boolean[] generateBooleanArray() {

        boolean[] result = new boolean[SIZE];

        for (int i = 0; i < result.length; i++) {

            result[i] = (MIN_VALUE + i) % 2 == 0;

        }

        return result;

    }

    private char[] generateCharArray() {

        char[] result = new char[SIZE];

        for (int i = 0; i < result.length; i++) {

            result[i] = charset[i % charset.length];

        }

        return result;

    }

    /**
     * Testing operations generate primitive arrays of this size.
     */
    private byte[] generateBytes(Class<?> type) {
        int byteCount = 0;

        ByteArrayOutputStream baos = null;
        long start = 0;
        long duration = 0;
        byte[] result = null;

        try {

            if (type == short.class) {
                short[] testArray = generateShortArray();

                start = System.currentTimeMillis();
                baos = new ByteArrayOutputStream(byteCount);
                DataOutputStream dos = new DataOutputStream(baos);

                for (int i = 0; i < testArray.length; i++) {

                    dos.writeShort(testArray[i]);

                }

                result = baos.toByteArray();
                byteCount = result.length;

                duration = System.currentTimeMillis() - start;

            } else if (type == int.class) {
                int[] testArray = generateIntArray();

                start = System.currentTimeMillis();
                baos = new ByteArrayOutputStream(byteCount);
                DataOutputStream dos = new DataOutputStream(baos);

                for (int i = 0; i < testArray.length; i++) {

                    dos.writeInt(testArray[i]);

                }

                result = baos.toByteArray();
                byteCount = result.length;

                duration = System.currentTimeMillis() - start;

            } else if (type == long.class) {
                long[] testArray = generateLongArray();

                start = System.currentTimeMillis();
                baos = new ByteArrayOutputStream(byteCount);
                DataOutputStream dos = new DataOutputStream(baos);

                for (int i = 0; i < testArray.length; i++) {

                    dos.writeLong(testArray[i]);

                }

                result = baos.toByteArray();
                byteCount = result.length;

                duration = System.currentTimeMillis() - start;

            } else if (type == float.class) {
                float[] testArray = generateFloatArray();

                start = System.currentTimeMillis();
                baos = new ByteArrayOutputStream(byteCount);
                DataOutputStream dos = new DataOutputStream(baos);

                for (int i = 0; i < testArray.length; i++) {

                    dos.writeFloat(testArray[i]);

                }

                result = baos.toByteArray();
                byteCount = result.length;

                duration = System.currentTimeMillis() - start;

            } else if (type == double.class) {
                double[] testArray = generateDoubleArray();

                start = System.currentTimeMillis();
                baos = new ByteArrayOutputStream(byteCount);
                DataOutputStream dos = new DataOutputStream(baos);

                for (int i = 0; i < testArray.length; i++) {

                    dos.writeDouble(testArray[i]);

                }

                result = baos.toByteArray();
                byteCount = result.length;

                duration = System.currentTimeMillis() - start;

            } else if (type == boolean.class) {
                boolean[] array = generateBooleanArray();
                int sz = 2; // for length value
                sz += (array.length / 8);
                if ((array.length % 8) != 0) {
                    sz++;

                }

                result = new byte[sz];

                // write the length
                PrimitiveUtil.toBytes((short) array.length, result, 0);

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

            } else if (type == char.class) {
                char[] testArray = generateCharArray();

                start = System.currentTimeMillis();
                baos = new ByteArrayOutputStream(byteCount);
                DataOutputStream dos = new DataOutputStream(baos);

                for (int i = 0; i < testArray.length; i++) {

                    dos.writeChar(testArray[i]);

                }

                result = baos.toByteArray();
                byteCount = result.length;

                duration = System.currentTimeMillis() - start;

            }

            Log.d(LOG_TAG,
                    String.format("DataOutputStream generated %d bytes for type %s in %d ms.",
                            byteCount,
                            type.getName(), duration));

            if (baos == null) {
                throw new IllegalArgumentException("Cannot generate byte arrays of type "
                        + type.getName());
            }

        } catch (IOException ioe) {
            throw new RuntimeException("unable to create byte array.", ioe);
        }

        return result;

    }
}
