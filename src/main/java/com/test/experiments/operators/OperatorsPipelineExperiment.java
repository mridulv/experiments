package com.test.experiments.operators;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by mridul on 05/12/17.
 */
public class OperatorsPipelineExperiment {
    static int loop1 = 20000000;
    static int loop2 = 100;

    public static void main(String args[]) {
        loop1 = Integer.valueOf(args[0]);
        loop2 = Integer.valueOf(args[1]);
        ArrayList<Long> arrayList1 = new ArrayList<Long>();
        ArrayList<Long> arrayList2 = new ArrayList<Long>();
        for (int i = 0; i < loop2; i++) {
            long t1 = experimentInlined();
            System.out.println("Printing results of inlining is " +  t1);
            long t2 = experimentVirtual();
            System.out.println("Printing results of virtual is " +  t2);
            arrayList1.add(t1);
            arrayList2.add(t2);
        }
        Collections.sort(arrayList1);
        Collections.sort(arrayList2);
        System.out.println("Printing results of inlining");
        for (int j = 1; j <= 10; j++) {
            int p1 = (10 * j) * arrayList1.size() / 100 - 1;
            if (p1 < 0) {
                p1 = 0;
            }
            System.out.println(arrayList1.get(p1));
        }
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Printing results of virtual");
        for (int j = 1; j <= 10; j++) {
            int p1 = (10 * j) * arrayList2.size() / 100 - 1;
            if (p1 < 0) {
                p1 = 0;
            }
            System.out.println(arrayList2.get(p1));
        }
    }

    private static long experimentVirtual() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < loop1; i++) {
            arrayList.add(i);
        }
        long t1 = System.currentTimeMillis();
        Operator operator5 = new BufferedOperator();
        Operator operator4 = new DivideOperator(operator5, 10);
        Operator operator3 = new MultiplyOperator(operator4, 10);
        Operator operator2 = new SubstractOperator(operator3, 10);
        Operator operator1 = new AddOperator(operator2, 10);
        SourceOperator operator = new SourceOperator(operator1, false);
        operator.setArrayList(arrayList);
        operator.get(1);
        return System.currentTimeMillis() - t1;
    }

    private static long experimentInlined() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int i = 0; i < loop1; i++) {
            arrayList.add(i);
        }
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < loop1; i++) {
            int a = arrayList.get(i);
            int a1 = a + 10;
            int a2 = a1 - 10;
            int a3 = a2 * 10;
            int a4 = a3 / 10;
            results.add(a4);
        }
        return System.currentTimeMillis() - t1;
    }
}
