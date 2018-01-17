package com.test.experiments;

import sun.misc.Unsafe;

/**
 * Created by mridul on 16/01/18.
 */
public class OffHeap
{
    private static final long fieldOffset = 0;
    private long objectOffset;

    public OffHeap(long address) {
        this.objectOffset = address;
    }

    public long getField(Unsafe unsafe)
    {
        return unsafe.getLong(objectOffset + fieldOffset);
    }

    public void getField(Unsafe unsafe, final long tradeId)
    {
        unsafe.putLong(objectOffset + fieldOffset, tradeId);
    }
}
