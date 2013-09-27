/*
 * Copyright (C) 2013  Richard Schilling. All rights reserved.
 * contact: coderroadie@gmail.com
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package android.reflect.util.test.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TestClass {

    public static final int SIZE = 10;
    public static final char[] CHAR_TABLE = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'
    };

    public String s;
    public boolean b;
    public float f;
    public int i;
    public double d;
    public char c;
    public byte Byte;
    public short Short;
    public long l;
    public TestClass testtable;

    // array types

    public byte[] byteArray = new byte[SIZE];
    public short[] shortArray = new short[SIZE];
    public int[] intArray = new int[SIZE];
    public long[] longArray = new long[SIZE];
    public float[] floatArray = new float[SIZE];
    public double[] doubleArray = new double[SIZE];
    public boolean[] booleanArray = new boolean[SIZE];
    public char[] charArray = new char[SIZE];

    // boxed array types

    public Byte[] boxByteArray = new Byte[SIZE];
    public Short[] boxShortArray = new Short[SIZE];
    public Integer[] boxIntArray = new Integer[SIZE];
    public Long[] boxLongArray = new Long[SIZE];
    public Float[] boxFloatArray = new Float[SIZE];
    public Double[] boxDoubleArray = new Double[SIZE];
    public Boolean[] boxBooleanArray = new Boolean[SIZE];
    public Character[] boxCharArray = new Character[SIZE];

    public PersistedArrayList<Byte> byteCollection = new PersistedArrayList<Byte>();
    public PersistedArrayList<Short> shortCollection = new PersistedArrayList<Short>();
    public PersistedArrayList<Integer> intCollection = new PersistedArrayList<Integer>();
    public PersistedArrayList<Long> longCollection = new PersistedArrayList<Long>();
    public PersistedArrayList<Float> floatCollection = new PersistedArrayList<Float>();
    public PersistedArrayList<Double> doubleCollection = new PersistedArrayList<Double>();
    public PersistedArrayList<Boolean> booleanCollection = new PersistedArrayList<Boolean>();
    public PersistedArrayList<Character> charCollection = new PersistedArrayList<Character>();
    public PersistedArrayList<CollectionItem> itemCollection = new PersistedArrayList<CollectionItem>();

    public static List<Field> getNonPrimitiveFields() throws SecurityException,
            NoSuchFieldException {

        List<Field> result = new ArrayList<Field>();
        result.add(TestClass.class.getField("byteCollection"));
        result.add(TestClass.class.getField("shortCollection"));
        result.add(TestClass.class.getField("intCollection"));
        result.add(TestClass.class.getField("longCollection"));
        result.add(TestClass.class.getField("floatCollection"));
        result.add(TestClass.class.getField("doubleCollection"));
        result.add(TestClass.class.getField("booleanCollection"));
        result.add(TestClass.class.getField("charCollection"));
        result.add(TestClass.class.getField("itemCollection"));
        result.add(TestClass.class.getField("s"));
        result.add(TestClass.class.getField("testtable"));

        return result;

    }

    public TestClass() {
        for (int i = 0; i < 10; i++) {

            byteArray[i] = (byte) i;
            shortArray[i] = (short) i;
            intArray[i] = (int) i;
            longArray[i] = (long) i;
            floatArray[i] = (float) i;
            doubleArray[i] = (double) i;
            charArray[i] = CHAR_TABLE[i];
            booleanArray[i] = i % 2 == 0;

            boxByteArray[i] = byteArray[i];
            boxShortArray[i] = shortArray[i];
            boxIntArray[i] = intArray[i];
            boxLongArray[i] = longArray[i];
            boxFloatArray[i] = floatArray[i];
            boxDoubleArray[i] = doubleArray[i];
            boxCharArray[i] = charArray[i];
            boxBooleanArray[i] = booleanArray[i];

            byteCollection.add((byte) i);
            shortCollection.add((short) i);
            intCollection.add((int) i);
            longCollection.add((long) i);
            floatCollection.add((float) i);
            doubleCollection.add((double) i);
            booleanCollection.add(booleanArray[i]);
            charCollection.add(CHAR_TABLE[i]);
            itemCollection.add(new CollectionItem());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new IllegalStateException("thread interrupted");
            }

        }
    }
}
