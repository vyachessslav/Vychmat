package org.example.entities;


import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Functions {
    public static double f1(double x) {
        return x * x * x - 4.5 * x * x - 9.21 * x - 0.383;
    }
    public static double f1Der(double x) { return 3 * x * x - 2 * 4.5 * x - 9.21; }
    public static double f1Der2(double x) { return 6 * x - 2 * 4.5; }
    public static String f1Str() {return "x^3 - 4.5 * x^2 - 9.21 * x - 0.383";}

    public static double f2(double x) {
        return sin(x) + x;
    }
    public static double f2Der(double x) {return cos(x) + 1;}
    public static double f2Der2(double x) { return - sin(x); }
    public static String f2Str() {
        return "sin(x) + x";
    }

    public static double f3(double x) { return x * x - 2 * x; }
    public static double f3Der(double x) { return 2 * x - 2; }
    public static double f3Der2(double x) { return 2; }
    public static String f3Str() {
        return "x^2 - 2 * x";
    }
    public static double p11(double x, double y) { return x * x * x + y * y + 5; }
    public static double p11DerX(double x, double y) { return 3 * x * x; }
    public static double p11DerY(double x, double y) { return 2 * y; }

    public static String p11Str() {
        return "x^3 + y^2 + 5";
    }

    public static double p12(double x, double y) { return sin(x) * y + 1; }

    public static double p12DerX(double x, double y) { return y * cos(x); }

    public static double p12DerY(double x, double y) { return sin(x); }

    public static String p12Str() {
        return "sin(x) * y + 1";
    }

    public static double p21(double x, double y) { return x * y + x + y; }

    public static double p21DerX(double x, double y) { return y + 1; }

    public static double p21DerY(double x, double y) { return x + 1; }

    public static String p21Str() {
        return "x * y + x + y";
    }

    public static double p22(double x, double y) { return x; }

    public static double p22DerX(double x, double y) { return 1; }

    public static double p22DerY(double x, double y) { return 0; }

    public static String p22Str() {
        return "x";
    }

    public static double f(double x, int n) {
        return switch (n) {
            case (1) -> f1(x);
            case (2) -> f2(x);
            case (3) -> f3(x);
            default -> throw new IllegalArgumentException("Выбрана несуществующая опция");
        };
    }

    public static double fDer(double x, int n) {
        return switch (n) {
            case (1) -> f1Der(x);
            case (2) -> f2Der(x);
            case (3) -> f3Der(x);
            default -> throw new IllegalArgumentException("Выбрана несуществующая опция");
        };
    }

    public static double fDer2(double x, int n) {
        return switch (n) {
            case (1) -> f1Der2(x);
            case (2) -> f2Der2(x);
            case (3) -> f3Der2(x);
            default -> throw new IllegalArgumentException("Выбрана несуществующая опция");
        };
    }

    public static double p(double x, double y, int n, int k) {
        if (n == 1 && k == 1) return p11(x, y);
        if (n == 1 && k == 2) return p12(x, y);
        if (n == 2 && k == 1) return p21(x, y);
        if (n == 2 && k == 2) return p22(x, y);
        throw new IllegalArgumentException("Выбрана несуществующая опция");
    }

    public static double pDerX(double x, double y, int n, int k) {
        if (n == 1 && k == 1) return p11DerX(x, y);
        if (n == 1 && k == 2) return p12DerX(x, y);
        if (n == 2 && k == 1) return p21DerX(x, y);
        if (n == 2 && k == 2) return p22DerX(x, y);
        throw new IllegalArgumentException("Выбрана несуществующая опция");
    }

    public static double pDerY(double x, double y, int n, int k) {
        if (n == 1 && k == 1) return p11DerY(x, y);
        if (n == 1 && k == 2) return p12DerY(x, y);
        if (n == 2 && k == 1) return p21DerY(x, y);
        if (n == 2 && k == 2) return p22DerY(x, y);
        throw new IllegalArgumentException("Выбрана несуществующая опция");
    }

}