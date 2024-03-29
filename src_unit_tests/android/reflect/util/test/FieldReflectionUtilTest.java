
package android.reflect.util.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import android.reflect.util.ClassReflectionUtil;
import android.reflect.util.FieldReflectionUtil;
import android.reflect.util.test.model.EnumTestClass;
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

    /**
     * Test {@link FieldReflectionUtil#removeFieldTypes(Map, List)}.
     */
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

    /**
     * Test {@link FieldReflectionUtil#toEnum(Field, String)}.
     */
    public void testToEnum() {
        boolean success = false;
        try {
            FieldReflectionUtil.toEnum(null, "name");
        } catch (IllegalArgumentException ex) {
            success = true;
        }
        assertTrue(
                "toEnum accepted a null parameter for field, expected an IllegalArgumentException",
                success);

        success = false;
        try {

            FieldReflectionUtil.toEnum(TestClass.class.getField("s"), null);

        } catch (NoSuchFieldException ex) {

            ex.printStackTrace();
            fail("could not get field 's' from TestClass.");

        } catch (IllegalArgumentException ex) {

            success = true;
        }

        assertTrue(
                "toEnum accepted a null parameter for enumName, expected an IllegalArgumentException",
                success);

        success = false;
        try {

            FieldReflectionUtil.toEnum(TestClass.class.getField("s"), "");

        } catch (NoSuchFieldException ex) {

            ex.printStackTrace();
            fail("could not get field 's' from TestClass.");

        } catch (IllegalArgumentException ex) {

            success = true;
        }

        assertTrue(
                "toEnum accepted an empty string for enumName, expected an IllegalArgumentException",
                success);

        success = false;
        try {

            FieldReflectionUtil.toEnum(TestClass.class.getField("s"), "name");

        } catch (NoSuchFieldException ex) {

            ex.printStackTrace();
            fail("could not get field 's' from TestClass.");

        } catch (IllegalArgumentException ex) {

            success = true;
        }

        assertTrue(
                "toEnum a field that was not an Enum object, expected an IllegalArgumentException",
                success);

        success = false;
        try {

            FieldReflectionUtil.toEnum(TestClass.class.getField("mEnum"), "FOUR");

        } catch (NoSuchFieldException ex) {

            ex.printStackTrace();
            fail("could not get field 'mEnum' from TestClass.");

        } catch (IllegalArgumentException ex) {

            success = true;
        }

        assertTrue(
                "toEnum accepted a bad value for enumName, expected an IllegalArgumentException",
                success);

        try {

            Enum<?> result = FieldReflectionUtil.toEnum(TestClass.class.getField("mEnum"), "ONE");
            assertTrue("toEnum returned class " + result.getClass().getName() + ", expected "
                    + EnumTestClass.class.getName(), result.getClass() == EnumTestClass.class);

        } catch (NoSuchFieldException ex) {

            ex.printStackTrace();
            fail("could not get field 's' from TestClass.");

        }

    }

    /**
     * Test {@link FieldReflectionUtil#getFieldValues(Class, Object)}.
     */
    public void testGetFieldValues() {

        boolean success = false;
        try {

            FieldReflectionUtil.getFieldValues(null, new Object());

        } catch (IllegalArgumentException ex) {
            success = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(
                "getFieldValues accepted a null value for class. Expected IllegalArgumentException",
                success);

        success = false;
        try {

            FieldReflectionUtil.getFieldValues(TestClass.class, null);

        } catch (IllegalArgumentException ex) {
            success = true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            fail();
        }

        assertTrue(
                "getFieldValues accepted a null value for object. Expected IllegalArgumentException",
                success);

        try {
            Map<Field, Object> fieldValues = FieldReflectionUtil.getFieldValues(TestClass.class,
                    new TestClass());

            /*
             * fieldValues should contain a value for every Field object in the
             * member fields plus two additional. The two additional fields are
             * static final fields.
             */
            assertTrue("not all field values were returned. Found " + fieldValues.size()
                    + ", expected " + fields.size() + 2, fieldValues.size() == fields.size() + 2);
        } catch (IllegalArgumentException e) {

            fail(e.getMessage());

        } catch (IllegalAccessException e) {

            fail(e.getMessage());

        }

    }

    /**
     * Test {@link FieldReflectionUtil#getAllFields(Class, int)}.
     */
    public void testGetAllFields() {

        /*
         * There are 39 fields - 38 are public and one of them is private.
         * Filter all the public fields.
         */
        List<Field> result = FieldReflectionUtil.getAllFields(TestClass.class, Modifier.PUBLIC);

        assertTrue(
                "Found " + result.size() + " fields. Expected 1.",
                result.size() == 1);

    }
}
