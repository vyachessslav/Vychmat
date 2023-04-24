package org.example.methods;

import org.example.entities.Functions;
import org.example.entities.OneVariableResult;
import org.example.entities.Segment;

public class HalfDivisionMethod {
    public static OneVariableResult findRoot(Segment segment, double accuracy, int iterationsCount, int n) {
        double right = segment.getRightBorder();
        double left = segment.getLeftBorder();
        double center = (right + left) / 2;
        iterationsCount++;
        if (Math.abs(Functions.f(center, n)) < accuracy) return new OneVariableResult(center, iterationsCount);
        else if (Functions.f(left, n) * Functions.f(center, n) < 0) return findRoot(new Segment(left, center), accuracy, iterationsCount, n);
        else return findRoot(new Segment(center, right), accuracy, iterationsCount, n);
    }
}
