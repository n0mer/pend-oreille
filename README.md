# Pend Oreille Developer Guide #

This guide describes what the Pend Oreille library is and provides the basic information a developer needs to take advantage of it.

## Introduction ##

Pend Oreille is a utility library built for Android that provides these basic features:

* serialize Java primitives and primitive arrays to byte arrays.

* deserialize java primitives and primitive arrays from byte arrays.

* box and unbox primitive arrays

* serialize an array of boolean values into a packed bit field.

* extract class model objects from a Java class using reflection.

* convert an array of one primitive type (e.g. `long[]`) into another (e.g. `byte[]`)
 	
There are also a few goals that Pende Oreille aims to achieve:

1. be small and lightweight.  The current library is about 14kb in size.

1. provide a more performant means of serializing and de-serializing primitives (and arrays of primitives) to/from byte arrays than using DataOutputStream and ByteArrayOutputStream.

1. allow boolean arrays to be stored in a packed bit array as opposed

1. work on arrays of primitives of arbitrary size.

1. support boxing and unboxing of entire primitive arrays.


## Basic Usage ##

This section will introduce you to the basic concepts of the library and how to take advantage of its features.

### Primitive Serialization ###

The purpose of the class `android.lang.util.MemoryMappedPrimitive` is to map primitives to byte arrays.  It is used to convert primitives and arrays of primitives from one type to another.

Let's say you have an array of long values and want to convert it to an array of long values.  You simply have to create a new `MemoryMappedPrimitive` object and then extract the bytes using the `asType(Class<?>)` function:

    long[] longArray = new long[100];
    MemoryMappedPrimitive mmp = new MemoryMappedPrimitive(longArray);
    byte[] longBytes = (byte[])mmp.asType(byte[].class);

You can do the same thing with any primitive type or any array of primitive types.

### Primitive Deserialization ###

`MemoryMappedPrimitive` also works in reverse.  It can take an arbitrary byte array and produce an array of another type:

    // convert a byte array to an array of two long values
    byte[] byteArray = new byte[16];
    MemoryMappedPrimitive mmp = new MemoryMappedPrimitive(byteArray);
    long[] longValues = (long[])mmp.asType(long[].class);
    
    // longValues.length = 2

## Booleans ##

Booleans deserve special mention.  Traditionally an array of booleans is stored as one byte each using this kind of scheme:

    boolean[] booleanArray = …
    byte[] byteArray = new byte[booleanArray.length];
    for (int i = 0; i < booleanArray.length; i++){
        byteArray[i] = booleanArray[i] ? 1 : 0;
    }

The problem with that approach is that it uses much more memory than necessary.  It would be more memory-consciencous to store the array of booleans into a byte array that treats each bit as a true/false value.  This not only reduces the amount of memory required to store the boolean values by a factor of eight, it also is more intuitive.  If a bit is 0 then it means false, and 1 means true.  

Pend Oreille solves this problem by serializing arrays of booleans in the following format:

1. bytes 0 and 1 store a short value which indicates boolean values in the array.
2. the remaining bytes in the array contain a bit field with each bit representing a boolean value.

So, this code will result in a byte array that is three bytes long:

    boolean[] booleanArray = new boolean[8]
    MemoryMappedPrimitive mmp = new MemoryMappedPrimitive(booleanArray);
    byte[] bitfield = (byte[])mmp.asType(byte[].class);

    // if you create a short out of the first two bytes you'll get the value 8
    // passing short.class to asType will convert the first two bytes of the array
    // into a short value.
    short numberOfBits = (short)mmp.asType(short.class);
    
    // the third byte contains the values of each boolean.
    // this will output a hex value that shows which bits are true and which are 
    // false 
    System.out.println(String.format("boolean bits: 0x%02X", bitfield[2]));


## Performance ##

For small amounts of data, say arrays of 1,000 primitives or less, Pend Oreille is not much faster than just using `DataOutputStream` to serialize/deseralize primitive arrays:

    // serialize 1,000 long values 
    ByteArrayOutputStream boas = new ByteArrayOutputStream(8000);
    DataOutputStream dos = new DataOutputStream(baos);
    for (int i = 0; i < 1000; i++){
        dos.writeLong(i);
    }
    byte[] serialized = baos.toByteArray();

But, with larger array sizes, say up in the 20,000 range Pend Oreille generally performs twice as fast:


    long[] longArray = new long[20000];
    byte[] controlBytes = PrimitiveUtil.toBytes(longArray);

Here is some output from the unit tests found in the directory `EclipseProjects/PendOreilleUnitTests`:

For doubles ...
                                                          
    10-03 21:11:28.920: D/PRIMITIVEUTILTEST(6473): DataOutputStream generated 160000 bytes for type double in 359 ms.
    10-03 21:11:29.211: D/PRIMITIVEUTILTEST(6473): PrimitiveUtil generated 160000 bytes for type double in 142 ms.

For integers …

    10-03 21:22:18.831: D/PRIMITIVEUTILTEST(6473): DataOutputStream generated 80000 bytes for type int in 150 ms.
    10-03 21:22:18.910: D/PRIMITIVEUTILTEST(6473): PrimitiveUtil generated 80000 bytes for type int in 73 ms.

For longs ...

    10-03 21:24:23.550: D/PRIMITIVEUTILTEST(6473): DataOutputStream generated 160000 bytes for type long in 255 ms.
    10-03 21:24:23.790: D/PRIMITIVEUTILTEST(6473): PrimitiveUtil generated 160000 bytes for type long in 114 ms.

For shorts ...

    10-03 21:26:27.140: D/PRIMITIVEUTILTEST(6473): DataOutputStream generated 40000 bytes for type short in 63 ms.
    10-03 21:26:27.220: D/PRIMITIVEUTILTEST(6473): PrimitiveUtil generated 40000 bytes for type short in 30 ms.

For floats …

    10-03 21:16:53.710: D/PRIMITIVEUTILTEST(6473): DataOutputStream generated 80000 bytes for type float in 174 ms.
    10-03 21:16:53.820: D/PRIMITIVEUTILTEST(6473): PrimitiveUtil generated 80000 bytes for type float in 95 ms.


This difference is significance when you consider the notion that many mobile applications are working with much larger amounts of data.  160,000 bytes for example is considered by many to be a "small" amount of data.  So, if that's the case for you then you should be able to serialize/de-serialize your data in about half the time as it takes using `DataOutputStream`.


## Using Pend Oreille On Other Platforms ##

Pend Oreille relies upon the standard Java platform classes, so there is a good chance it will run as-is on any Java 1.5 platform or above.

## License ##

This product is licensed under the GPL v 2.0 license.

Copyright (C) 2013 Richard Schilling

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

