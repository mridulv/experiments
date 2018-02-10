package com.test.experiments.operators;

public class DivideOperator extends Operator {
    private Operator underlyingOperator = null;
    private int num = 0;

    public DivideOperator(Operator underlyingOperator, int p) {
        this.underlyingOperator = underlyingOperator;
        this.num = p;
    }


    public void get(int elem) {
        if (elem == SourceOperator.FLUSH_CODE) {
            underlyingOperator.get(elem);
        } else if (elem == SourceOperator.END_CODE) {
            underlyingOperator.get(elem);
        } else {
            int p = elem / num;
            underlyingOperator.get(p);
        }
    }

    public String getImplementation() {
        return null;
    }
}