package main.java.ru.vezhur;

import main.java.ru.vezhur.methods.Method;
import main.java.ru.vezhur.methods.Rectangle;
import main.java.ru.vezhur.methods.Simpson;
import main.java.ru.vezhur.methods.Trapezoid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

import static java.lang.Math.sin;

public class Main {

    public static void main(String[] args) throws IOException {
        Function<Double, Double> function;
        double left;
        double right;
        double precision;
        int steps = 4;
        Method method;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("""
                Выберите функцию для интегрирования:
                1. x^3 + 5x^2 - 7x - 10
                2. 2x^3 - 5x^2 - 3x + 21
                3. sin(x) * x + x - 3
                """);
        switch (reader.readLine()) {
            case ("1") -> {
                function = (x) -> Math.pow(x, 3) + 5 * Math.pow(x, 2) - 7 * x - 10;
            }
            case ("2") -> {
                function = (x) -> 2 * Math.pow(x, 3) - 5 * Math.pow(x, 2) - 3 * x + 21;
            }
            case ("3") -> {
                function = (x) -> sin(x) * x + x - 3;
            }
            default -> {
                incorrect();
                return;
            }
        }

        System.out.println("Введите пределы интегрирования (сначала левый, а потом правый):");
        left = Double.parseDouble(reader.readLine());
        right = Double.parseDouble(reader.readLine());

        System.out.println("Введите точность:");
        precision = Double.parseDouble(reader.readLine());

        System.out.println("""
                Выберите метод нахождения интеграла:
                1. Метод прямоугольника - левый
                2. Метод прямоугольника - центр
                3. Метод прямоугольника - правый
                4. Метод трапеций
                5. Метод Симпсона
                """);
        switch (reader.readLine()) {
            case ("1") -> {
                method = Rectangle.left();
            }
            case ("2") -> {
                method = Rectangle.center();
            }
            case ("3") -> {
                method = Rectangle.right();
            }
            case ("4") -> {
                method = Trapezoid.trapezoid();
            }
            case ("5") -> {
                method = Simpson.simpson();
            }
            default -> {
                incorrect();
                return;
            }
        }

        while (Math.abs(method.integrate(function, left, right, steps) - method.integrate(function, left, right, steps * 2)) >= precision) {
            steps *= 2;
        }
        steps *= 2;
        System.out.println("Ответ: " + method.integrate(function, left, right, steps));
        System.out.println("Количество шагов: " + steps);
    }

    public static void incorrect() {
        System.out.println("Введено некорректное значение");
    }
}