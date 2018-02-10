/*
 * Copyright (c) 2005, 2014, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.test.experiments;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@State(Scope.Thread)
public class MyBenchmark {

    public static Unsafe unsafe;
    public static long initialAddress;

    static {
        try {
            unsafe = getUnsafe();
            initialAddress = unsafe.allocateMemory(100000);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("restriction")
    private static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
        try {

            Field singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
            singleoneInstanceField.setAccessible(true);
            return (Unsafe) singleoneInstanceField.get(null);

        } catch (IllegalArgumentException e) {
            throw e;
        } catch (SecurityException e) {
            throw e;
        } catch (NoSuchFieldException e) {
            throw e;
        } catch (IllegalAccessException e) {
            throw e;
        }
    }

//    @Benchmark
//    public void testMethod(Blackhole blackhole) {
//        OnHeap onHeap = new OnHeap();
//        onHeap.setField(100L);
//        blackhole.consume(onHeap.getField());
//    }
//
//    @Benchmark
//    public void testMethod2(Blackhole blackhole) {
//        OffHeap offHeap = new OffHeap(initialAddress);
//        offHeap.putField(unsafe, 100);
//        blackhole.consume(offHeap.getField(unsafe));
//    }
}
