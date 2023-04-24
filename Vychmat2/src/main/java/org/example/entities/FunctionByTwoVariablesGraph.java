package org.example.entities;

import java.util.LinkedList;
import java.util.List;

public class FunctionByTwoVariablesGraph extends FunctionGraph {
    private double stepSize;
    private double accuracy;

    private int k;
    public FunctionByTwoVariablesGraph(Segment[] segments, double stepSize, double accuracy, int k) {
        this.leftBorder = segments[0].getLeftBorder();
        this.rightBorder = segments[0].getRightBorder();
        this.bottomBorder = segments[1].getLeftBorder();
        this.topBorder = segments[1].getRightBorder();
        this.stepSize = stepSize;
        this.accuracy = accuracy;
        this.k = k;
    }

    public FunctionByTwoVariablesGraph(Segment[] segments, double accuracy, int k) {
        this.leftBorder = segments[0].getLeftBorder();
        this.rightBorder = segments[0].getRightBorder();
        this.bottomBorder = segments[1].getLeftBorder();
        this.topBorder = segments[1].getRightBorder();
        this.stepSize = 0.001;
        this.accuracy = accuracy;
        this.k = k;
    }

    @Override
    public List<Double> getValuesByVariable(double x0, int n) {
        List<Double> valuesList = new LinkedList<>();
        double x = x0;
        double y = this.bottomBorder;
        while (y <= this.topBorder) {
            if (Math.abs(Functions.p(x, y, n, k)) < this.accuracy) valuesList.add(y);
            y += this.stepSize;
        }
        return valuesList;
    }
}
