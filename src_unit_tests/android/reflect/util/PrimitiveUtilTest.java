
package android.reflect.util;

import junit.framework.TestCase;
import android.lang.util.PrimitiveUtil;
import android.reflect.util.test.model.TestClass;
import android.util.Log;

public class PrimitiveUtilTest extends TestCase {

    private TestClass testClass;

    public void setUp() {

        testClass = new TestClass();

    }

    public void testBoxByte() {

        byte[] source = testClass.byteArray;

        long start = System.currentTimeMillis();
        Byte[] result = PrimitiveUtil.box(source);
        long elapsed = System.currentTimeMillis() - start;
        Log.d("PRIMITIVEUTILTEST", "testBoxByte ms: " + elapsed);

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
        Log.d("PRIMITIVEUTILTEST", "testBoxShort ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testBoxInt ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testBoxLong ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testBoxFloat ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testBoxDouble ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testBoxChar ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testBoxBoolean ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxByte ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxShort ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxInt ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxLong ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxFloat ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxDouble ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxChar ms: " + elapsed);
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
        Log.d("PRIMITIVEUTILTEST", "testUnboxBoolean ms: " + elapsed);
        for (int i = 0; i < result.length; i++) {

            assertTrue("index " + i + " of result does not match.  Expected "
                    + source[i] + ", found " + result[i],
                    source[i] == result[i]);

        }

    }

}
