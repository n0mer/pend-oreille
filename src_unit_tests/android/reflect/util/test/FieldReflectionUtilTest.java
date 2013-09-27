
package android.reflect.util.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import android.reflect.util.FieldReflectionUtil;
import android.reflect.util.test.model.TestClass;

/**
 * Test {@link FieldReflectionUtil}.
 * 
 * @author Richard Schilling
 * @since 1.0
 */
public class FieldReflectionUtilTest extends TestCase {

    private List<Field> fields;
    private List<Field> nonPrimitives;

    private Map<Field, Object> pretendValues;
    private List<String> nonPrimitivesNames;

    public void setUp() {

        fields = FieldReflectionUtil.getAllFields(TestClass.class);
        try {
            nonPrimitives = TestClass.getNonPrimitiveFields();
        } catch (SecurityException e) {
            fail();
        } catch (NoSuchFieldException e) {
            fail();
        }

        nonPrimitivesNames = new ArrayList<String>(nonPrimitives.size());

        for (Field f : nonPrimitives) {
            nonPrimitivesNames.add(f.getName());
        }

        pretendValues = new HashMap<Field, Object>();
        for (Field f : fields) {
            pretendValues.put(f, new Object());

        }

    }

    /**
     * Make sure that all primitives and primitive arrays (and only primitives)
     * are removed by {@link FieldReflectionUtil#removePrimitivesAndArrays(Map)}
     * .
     * 
     * @since 1.0
     */
    public void testRemovePrimitivesAndArrays() {

        /*
         * check null return value
         */
        boolean success = false;
        try {
            FieldReflectionUtil.removePrimitivesAndArrays(null);
        } catch (IllegalArgumentException ex) {
            success = true;
        }
        assertTrue(
                "removePrimitivesAndArrays throw an IllegalArgumentException if the parameter is null",
                success);

        /*
         * Check the empty set
         */
        Map<Field, Object> emptyMap = FieldReflectionUtil
                .removePrimitivesAndArrays(new HashMap<Field, Object>());

        assertTrue(
                "expected a map of size 0 from removePrimitivesAndArrays, received a map of size "
                        + emptyMap.size(),
                emptyMap.size() == 0);

        Map<Field, Object> result = FieldReflectionUtil.removePrimitivesAndArrays(pretendValues);
        assertTrue("result does not have the correct number of fields in it. found "
                + result.size() + ", expected " + nonPrimitives.size(),
                result.size() == nonPrimitives.size());

        for (Field f : result.keySet()) {
            assertTrue("non-primitive field " + f.getName() + " was removed",
                    nonPrimitivesNames.contains(f.getName()));

        }

    }

    public void testRemoveFieldTypes() {
        
        boolean success = false;
        try {
            FieldReflectionUtil.removeFieldTypes(null, new ArrayList<Class<?>>());
        } catch (IllegalArgumentException ex) {
            success = true;
        }
        assertTrue(
                "removeFieldTypes accepted a null parameter for fieldMap, expected an IllegalArgumentException",
                success);

        success = false;
        try {
            FieldReflectionUtil.removeFieldTypes(new HashMap<Field, Object>(), null);
        } catch (IllegalArgumentException ex) {
            success = true;
        }
        assertTrue(
                "removeFieldTypes accepted a null parameter for types, expected an IllegalArgumentException",
                success);

        /*
         * remove primitives and primitive arrays from a map
         */
        Map<Field, Object> map1 = FieldReflectionUtil.removeFieldTypes(pretendValues,
                ClassReflectionUtilTest.primitiveClassList);

        Map<Field, Object> newMap = FieldReflectionUtil.removeFieldTypes(map1,
                ClassReflectionUtilTest.primitiveArrayClassList);

        /*
         * The only thing left should be non-primitives: only foreign references
         * that are not primitive arrays or arrays of boxed primitives.
         */

        /*
         * So, if newMap and nonPrimitives have the same size ...
         */
        assertTrue("expected " + nonPrimitives.size() + " items in map, found " + newMap.size(),
                nonPrimitives.size() == newMap.size());

        /*
         * ... and each item in nonPrimitives is found in the map, then it means
         * that all of nonPrimitive's values are in mapField. We know this
         * because mapField keys are unique.
         */
        for (Field npField : nonPrimitives) {

            boolean found = false;

            for (Field mapField : newMap.keySet()) {
                if (mapField.getType() == npField.getType()) {
                    found = true;
                    break;
                }
            }

            assertTrue("removeFieldTypes removed " + npField.getType().getName()
                    + " and it should not have.",
                    found);

        }

    }
}
