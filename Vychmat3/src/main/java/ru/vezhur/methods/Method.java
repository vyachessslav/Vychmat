package main.java.ru.vezhur.methods;

import java.util.function.Function;

public interface Method {
    double integrate(Function<Double, Double> function, double left, double right, int steps);
}