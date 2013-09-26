
package android.reflect.util.test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import android.app.Activity;
import android.content.Intent;
import android.reflect.util.ClassReflectionUtil;

public class ClassReflectionUtilTest extends TestCase {

    /**
     * The list of primitive classes and their box types.
     * 
     * @since 1.0
     */
    private static Class<?>[] PRIMITIVE_CLASSES = {
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Boolean.class,
            Character.class,
            byte.class,
            short.class,
            int.class,
            long.class,
            float.class,
            double.class,
            boolean.class,
            char.class

    };

    /**
     * The list of primitive array classes.
     * 
     * @since 1.0
     */
    public static Class<?>[] PRIMITIVE_ARRAY_CLASSES = {
            byte[].class, Byte[].class,
            short[].class, Short[].class,
            int[].class, Integer[].class,
            long[].class, Long[].class,
            float[].class, Float[].class,
            double[].class, Double[].class,
            boolean[].class, Boolean[].class,
            char[].class, Character[].class
    };

    /**
     * Some arbitrary classes to test cases with.
     * 
     * @since 1.0
     */
    public static Class<?>[] NONPRIMITIVE_CLASSES = {
            String.class,
            BigInteger.class,
            IllegalArgumentException.class,
            Activity.class,
            Intent.class
    };

    /**
     * Ensures that ClassReflectionUtil.isPrimitiverOrArray and isPrimitiveArray
     * is accepting and rejecting the right set of classes.
     */
    public void testisPrimitive() {

        for (Class<?> c : PRIMITIVE_CLASSES) {
            assertTrue("ClassReflectionUtil does not think " + c.getName()
                    + " is a primitive, box, or array of either",
                    ClassReflectionUtil.isPrimitiveOrArray(c));
        }

        for (Class<?> c : PRIMITIVE_ARRAY_CLASSES) {
            assertTrue("ClassReflectionUtil does not think " + c.getName()
                    + " is a primitive array", ClassReflectionUtil.isPrimitiveArray(c));
        }

        for (Class<?> c : PRIMITIVE_CLASSES) {
            assertFalse("ClassReflectionUtil did not reject " + c.getName()
                    + " even though it's not a primitive array.",
                    ClassReflectionUtil.isPrimitiveArray(c));
        }

    }

    /**
     * Ensures that the function ClassReflectionUtil.removePrimitives(Set) is
     * working properly.
     */
    public void testRemovePrimitives() {

        /*
         * create a mixed set of classes
         */
        Set<Class<?>> testSet = new HashSet<Class<?>>(5);

        for (int i = 0; i < 2; i++) {
            testSet.add(NONPRIMITIVE_CLASSES[i]);
        }

        for (Class<?> c : PRIMITIVE_ARRAY_CLASSES) {
            testSet.add(c);

        }
        testSet.add(NONPRIMITIVE_CLASSES[2]);

        for (Class<?> c : PRIMITIVE_CLASSES) {
            testSet.add(c);
        }

        for (int i = 3; i < NONPRIMITIVE_CLASSES.length; i++) {
            testSet.add(NONPRIMITIVE_CLASSES[i]);
        }

        Set<Class<?>> inclusive = ClassReflectionUtil.keepPrimitives(testSet);
        Set<Class<?>> exclusive = ClassReflectionUtil.removePrimitives(testSet);

        /*
         * check inclusive
         */
        for (Class<?> c : PRIMITIVE_ARRAY_CLASSES) {
            assertTrue(
                    "ClassReflectionUtil.keepPrimitives did not retain primitive " + c.getName(),
                    inclusive.contains(c));

        }

        for (Class<?> c : PRIMITIVE_CLASSES) {
            assertTrue(
                    "ClassReflectionUtil.keepPrimitives did not retain primitive " + c.getName(),
                    inclusive.contains(c));

        }

        for (Class<?> c : NONPRIMITIVE_CLASSES) {
            assertFalse("ClassReflectionUtil.keepPrimitives did not remove " + c.getName(),
                    inclusive.contains(c));

        }

        /*
         * check exclusive
         */
        for (Class<?> c : PRIMITIVE_ARRAY_CLASSES) {
            assertFalse(
                    "ClassReflectionUtil.keepPrimitives did not remove primitive " + c.getName(),
                    exclusive.contains(c));

        }

        for (Class<?> c : PRIMITIVE_CLASSES) {
            assertFalse(
                    "ClassReflectionUtil.keepPrimitives did not remove primitive " + c.getName(),
                    exclusive.contains(c));

        }

        for (Class<?> c : NONPRIMITIVE_CLASSES) {
            assertTrue("ClassReflectionUtil.keepPrimitives did not retain " + c.getName(),
                    exclusive.contains(c));

        }

    }
}
