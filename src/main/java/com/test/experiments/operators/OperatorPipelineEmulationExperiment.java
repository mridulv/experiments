package com.test.experiments.operators;

import java.util.ArrayList;
import java.util.HashMap;

public class OperatorPipelineEmulationExperiment {

    public static ArrayList<Integer> experimentVirtual(ArrayList<Integer> arrayList) {
        BufferedOperator bufferedOperator = new BufferedOperator();
        AddOperator addOperator = new AddOperator(bufferedOperator, 10);
        SourceOperator sourceOperator = new SourceOperator(addOperator, true);
        sourceOperator.setArrayList(arrayList);
        sourceOperator.get(1);
        return bufferedOperator.arrayList;
    }
}