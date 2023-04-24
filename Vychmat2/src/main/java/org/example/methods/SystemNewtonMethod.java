package org.example.methods;

import org.example.entities.Functions;
import org.example.entities.OneVariableResult;
import org.example.entities.Segment;
import org.example.entities.TwoVariablesResult;

public class SystemNewtonMethod {
    public static TwoVariablesResult findRoot(double x, double y, double accuracy, int n) {
        int iterationsCount = 0;
        double prevX = x;
        double prevY = y;

        double deltaX = (-Functions.p(x, y, n, 2) + Functions.p(x, y, n, 1) * Functions.pDerY(x, y, n, 2) /
                Functions.pDerY(x, y, n, 1)) / (Functions.pDerX(x, y, n, 2) - Functions.pDerX(x, y, n, 1) *
                Functions.pDerY(x, y, n, 2) / Functions.pDerY(x, y, n, 1));
        double deltaY = (-Functions.p(x, y, n, 1) - Functions.pDerX(x, y, n, 1) * deltaX) / Functions.pDerY(x, y, n, 1);

        x += deltaX;
        y += deltaY;
        iterationsCount++;
        while (Math.abs(x - prevX) > accuracy || Math.abs(y - prevY) > accuracy) {
            prevX = x;
            prevY = y;

            deltaX = (-Functions.p(x, y, n, 2) + Functions.p(x, y, n, 1) * Functions.pDerY(x, y, n, 2) /
                    Functions.pDerY(x, y, n, 1)) / (Functions.pDerX(x, y, n, 2) - Functions.pDerX(x, y, n, 1) *
                    Functions.pDerY(x, y, n, 2) / Functions.pDerY(x, y, n, 1));
            deltaY = (-Functions.p(x, y, n, 1) - Functions.pDerX(x, y, n, 1) * deltaX) / Functions.pDerY(x, y, n, 1);

            x += deltaX;
            y += deltaY;
            iterationsCount++;
        }
        return new TwoVariablesResult(new double[] {x, y}, iterationsCount);
    }
}
