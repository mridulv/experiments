package com.test.experiments;

import sun.misc.Unsafe;

/**
 * Created by mridul on 16/01/18.
 */
public class OffHeap
{
    private long objectOffset;

    public OffHeap(long address) {
        this.objectOffset = address;
    }

    public long getField(Unsafe unsafe)
    {
        return unsafe.getLong(objectOffset);
    }

    public void putField(Unsafe unsafe, final long tradeId)
    {
        unsafe.putLong(objectOffset, tradeId);
    }
}
