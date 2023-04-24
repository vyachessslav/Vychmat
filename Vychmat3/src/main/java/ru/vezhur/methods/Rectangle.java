package main.java.ru.vezhur.methods;

import java.util.function.BiFunction;

public class Rectangle {
    public static Method left() {
        return rectangle((left, right) -> left);
    }

    public static Method center() {
        return rectangle((left, right) -> (left + right) / 2);
    }

    public static Method right() {
        return rectangle((left, right) -> right);
    }
    private static Method rectangle(BiFunction<Double, Double, Double> borderSelector) {
        return (function, left, right, steps) -> {
            double sum = 0;
            double step = (right - left) / steps;
            for (int i = 0; i < steps; ++i) {
                sum += borderSelector.apply(function.apply(left + step * i), function.apply(left + step * (i + 1)));
            }
            sum *= step;
            return sum;
        };
    }
}