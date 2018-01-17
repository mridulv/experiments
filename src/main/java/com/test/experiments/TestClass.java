package com.test.experiments;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by mridul on 14/01/18.
 */

public class TestClass {
    public static Unsafe unsafe;

    static {
        try {
            unsafe = getUnsafe();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        long[] l = new long[1000000];
        long address = unsafe.allocateMemory(100L);
        for (int i = 0; i < 1000000 ; i++) {
            l[i] = unsafe.getLong(address);
        }
        Thread.sleep(1000000);
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

}
