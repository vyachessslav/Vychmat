package org.example.methods;

import org.example.entities.Functions;
import org.example.entities.OneVariableResult;
import org.example.entities.Segment;

public class NewtonMethod {
    public static OneVariableResult findRoot(Segment segment, double accuracy, int n) {
        double x;
        int iterationsCount = 0;
        if (Functions.f(segment.getLeftBorder(), n) * Functions.fDer2(segment.getLeftBorder(), n) > 0) x = segment.getLeftBorder();
        else x = segment.getRightBorder();
        double prevX = x - 2 * accuracy;
        while (Math.abs(x - prevX) > accuracy) {
            iterationsCount++;
            prevX = x;
            x = prevX - Functions.f(prevX, n) / Functions.fDer(prevX, n);
        }
        return new OneVariableResult(x, iterationsCount);
    }
}
