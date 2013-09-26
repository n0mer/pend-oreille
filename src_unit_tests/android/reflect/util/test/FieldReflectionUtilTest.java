
package android.reflect.util.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import android.reflect.util.FieldReflectionUtil;

/**
 * Test {@link FieldReflectionUtil}.
 * 
 * @author Richard Schilling
 * @since 1.0
 */
public class FieldReflectionUtilTest extends TestCase {

    private List<Field> fields;
    private List<Field> nonPrimitives;
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
    }

    /**
     * Make sure that all primitives (and only primitives) are removed by
     * {@link FieldReflectionUtil#removePrimitives(Map)}.
     * 
     * @since 1.0
     */
    public void testRemovePrimitives() {

        Map<Field, Object> pretendValues = new HashMap<Field, Object>();
        for (Field f : fields) {
            pretendValues.put(f, new Object());

        }

        Map<Field, Object> result = FieldReflectionUtil.removePrimitivesAndArrays(pretendValues);
        assertTrue("result does not have the correct number of fields in it. found "
                + result.size() + ", expected " + nonPrimitives.size(),
                result.size() == nonPrimitives.size());

        for (Field f : result.keySet()) {
            assertTrue("non-primitive field " + f.getName() + " was removed",
                    nonPrimitivesNames.contains(f.getName()));

        }

    }
}
