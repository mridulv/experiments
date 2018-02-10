package com.test.experiments;

import sun.misc.Unsafe;

/**
 * Created by mridul on 16/01/18.
 */
public class OnHeap
{
    private long tradeId;

    public long getField()
    {
        return tradeId;
    }

    public void setField(final long tradeId)
    {
        this.tradeId = tradeId;
    }
}
