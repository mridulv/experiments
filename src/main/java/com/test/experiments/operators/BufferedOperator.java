package com.test.experiments.operators;

import java.util.ArrayList;

/**
 * Created by mridul on 06/12/17.
 */
public class BufferedOperator extends Operator {
    ArrayList<Integer> arrayList = null;

    public BufferedOperator() {
        arrayList = new ArrayList<Integer>();
    }

    public void get(int elem) {
        if (elem == SourceOperator.FLUSH_CODE) {
            arrayList = new ArrayList<Integer>();
        } else if (elem == SourceOperator.END_CODE) {
            return;
        } else {
            arrayList.add(elem);
        }
    }

    public String getImplementation() {
        return null;
    }
}
