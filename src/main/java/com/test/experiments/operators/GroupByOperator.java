package com.test.experiments.operators;

import java.util.HashMap;

public class GroupByOperator extends Operator {
    private Operator underlyingOperator = null;
    private HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    private int keys = 1000000;
    private long t = 0L;

    public GroupByOperator(Operator operator) {
        underlyingOperator = operator;
    }

    private void printMap() {
        for (Integer key : hashMap.values()) {
            underlyingOperator.get(key);
        }
    }

    public void get(int elem) {
        if (elem == SourceOperator.FLUSH_CODE) {
            underlyingOperator.get(elem);
            printMap();
        } else {
            int key = elem % keys;

            Integer p1 = hashMap.get(key);
            if (p1 == null) {
                hashMap.put(key, elem);
            } else {
                int p2 = hashMap.get(key);
                hashMap.put(key, p2 + elem);
            }
        }
    }

    public String getImplementation() {
        return null;
    }
}
