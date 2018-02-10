package com.test.experiments.operators;

import java.util.ArrayList;

public class SourceOperator extends Operator {
    public static int FLUSH_CODE = -1;
    public static int END_CODE = -2;
    private ArrayList<Integer> nums = null;
    private int flushNumber = 100;
    private Operator underlyingOperator = null;
    boolean enableFlush = false;

    public SourceOperator(Operator underlyingOperator, boolean enableFlush)
    {
        this.underlyingOperator = underlyingOperator;
        this.enableFlush = enableFlush;
    }

    public SourceOperator setArrayList(ArrayList<Integer> nums) {
        this.nums = nums;
        return this;
    }

    public void get(int num) {
        for (int i = 0; i < nums.size(); i++) {
            int p = nums.get(i);
            if (enableFlush && (i % flushNumber == 0)) {
                underlyingOperator.get(FLUSH_CODE);
            }
            underlyingOperator.get(p);
        }
        underlyingOperator.get(END_CODE);
    }

    public String getImplementation() {
        return null;
    }
}
