package main.java.ru.vezhur.methods;

public class Trapezoid {
    public static Method trapezoid() {
        return (function, left, right, steps) -> {
            double sum = 0;
            double step = (right - left) / steps;
            for (int i = 1; i < steps; ++i) {
                sum += function.apply(left + step * i);
            }
            sum += (function.apply(left) + function.apply(right)) / 2;
            sum *= step;
            return sum;
        };
    }
}