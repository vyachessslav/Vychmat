package org.example.methods;


import org.example.entities.Functions;
import org.example.entities.OneVariableResult;
import org.example.entities.Segment;

public class SimpleIterationMethod {
    private static double findLambda(Segment segment, int n) {
        return SimpleIterationMethod.findLambda(segment, 0.001, n);
    }

    private static double findLambda(Segment segment, double stepSize, int n) throws IllegalArgumentException {
        double x = segment.getLeftBorder();
        double lambdaSign = Functions.fDer(x, n) / Math.abs(Functions.fDer(x, n));
        double maxDerivative = 0;
        while (x <= segment.getRightBorder()) {
            double derivative = Functions.fDer(x, n);
            if (derivative * lambdaSign < 0) throw new IllegalArgumentException("Функция не удовлетворяет достаточным условиям сходимости метода простой итерации на этом интервале :(");
            if (Math.abs(derivative) > maxDerivative) maxDerivative = Math.abs(derivative);
            x += stepSize;
        }
        return - lambdaSign / maxDerivative;
    }

    static double phi(double x, double lambda, int n) throws IllegalArgumentException {
        return x + lambda * Functions.f(x, n);
    }

    public static OneVariableResult findRoot(Segment segment, double accuracy, int n) throws IllegalArgumentException {
        double lambda = SimpleIterationMethod.findLambda(segment, n);

        double x = segment.getLeftBorder();
        double prevX = x - 2 * accuracy;

        int iterationsCount = 0;
        while (Math.abs(x - prevX) > accuracy) {
            iterationsCount++;
            prevX = x;
            x = phi(prevX, lambda, n);
        }

        return new OneVariableResult(x, iterationsCount);
    }
}
