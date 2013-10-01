
package android.lang.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import junit.framework.TestCase;
import android.reflect.util.test.model.TestClass;
import android.util.Log;

public class PrimitiveUtilTest extends TestCase {

    private static final String LOG_TAG = "PRIMITIVEUTILTEST";
    private static final int MIN_VALUE = -10000;
    private static final int MAX_VALUE = 10000;
    private static final int SIZE = 20000;

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

    public void testToLongArray() {
        byte[] test = generateBytes(long.class);
        long[] control = generateLongArray();

        long start = System.currentTimeMillis();
        long[] result = PrimitiveUtil.toLongArray(test);
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

    private short[] generateShortArray() {

        short[] result = new short[SIZE];

        for (int i = 0; i < result.length; i++) {

            result[i] = (short) (MIN_VALUE + i);

        }

        return result;

    }

    private long[] generateLongArray() {

        long[] result = new long[SIZE];

        for (int i = 0; i < result.length; i++) {
            try {
                result[i] = (long) (MIN_VALUE + i);
            } catch (ArrayIndexOutOfBoundsException ex) {
                Log.e(LOG_TAG, i + " is out of bounds");
                throw ex;
            }

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
