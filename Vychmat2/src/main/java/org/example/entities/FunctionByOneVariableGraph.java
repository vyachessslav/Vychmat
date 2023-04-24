package org.example.entities;

import java.util.LinkedList;
import java.util.List;

public class FunctionByOneVariableGraph extends FunctionGraph {
    public FunctionByOneVariableGraph(Segment segment) {
        this.leftBorder = segment.getLeftBorder();
        this.rightBorder = segment.getRightBorder();
    }

    @Override
    public List<Double> getValuesByVariable(double x, int n) {
        double value = Functions.f(x, n);
        if (value < this.bottomBorder) this.bottomBorder = value;
        if (value > this.topBorder) this.topBorder = value;
        List<Double> valuesList = new LinkedList<>();
        valuesList.add(value);
        return valuesList;
    }
}
