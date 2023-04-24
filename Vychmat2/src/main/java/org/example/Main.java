package org.example;

import org.example.entities.*;
import org.example.methods.HalfDivisionMethod;
import org.example.methods.NewtonMethod;
import org.example.methods.SimpleIterationMethod;
import org.example.methods.SystemNewtonMethod;
import org.example.util.GraphPanel;
import org.example.util.Verificator;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            JFrame jFrame = new JFrame();
            GraphPanel panel = new GraphPanel();

            Scanner scanner = null;
            if (args.length != 0) {
                FileInputStream fileInputStream = new FileInputStream(args[0]);
                scanner = new Scanner(fileInputStream);
            }
            else scanner = new Scanner(System.in);
            System.out.println("Введите 1, если хотите решать нелинейное уравнение, 2 - если систему");
            int answer = scanner.nextInt();
            switch (answer) {
                case (1):
                    Main.equationSolving(scanner, panel);
                    break;
                case (2):
                    Main.systemOfEquationsSolving(scanner, panel);
                    break;
                default:
                    throw new IllegalArgumentException("Введите 1 или 2!");
            }

            jFrame.add(panel);
            jFrame.setSize(800, 800);
            jFrame.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void equationSolving(Scanner scanner, GraphPanel panel) throws IllegalAccessException, InstantiationException {
        System.out.println("Выберите уравнение \n1 -- " + Functions.f1Str() + ";\n2 -- "
                + Functions.f2Str() + ";\n3 -- " + Functions.f3Str());
        int equation = scanner.nextInt();
        if (equation != 1 && equation != 2 && equation != 3) {
                throw new IllegalArgumentException("Выбрана несуществующая опция :(");
        }
        panel.setN(equation);

        System.out.println("Введите левую границу интервала");
        double leftBorder = scanner.nextDouble();
        System.out.println("Введите правую границу интервала");
        double rightBorder = scanner.nextDouble();
        Segment usersSegment = new Segment(leftBorder, rightBorder);
        List<Segment> segments = Verificator.getRootsSegments(usersSegment, 0.1, equation);
        if (segments.size() == 0) throw new IllegalArgumentException("На интервале не найдено корней");
        else System.out.println("Количество корней на интервале: " + segments.size());

        System.out.println("Интервалы, на которых будем искать корни:");
        for (Segment segment: segments) {
            System.out.println(segment.getLeftBorder() + ", " + segment.getRightBorder());
        }

        System.out.println("Введите точность");
        double accuracy = scanner.nextDouble();
        System.out.println("Выберите метод \n1 -- метод половинного деления;\n2 -- метод Ньютона;\n3 -- метод простой итерации");
        int answer = scanner.nextInt();

        List<OneVariableResult> roots = new LinkedList<>();
        switch (answer) {
            case (1):
                for (Segment segment: segments) roots.add(HalfDivisionMethod.findRoot(segment, accuracy, 0, equation));
                break;
            case (2):
                for (Segment segment: segments) roots.add(NewtonMethod.findRoot(segment, accuracy, equation));
                break;
            case (3):
                for (Segment segment: segments) roots.add(SimpleIterationMethod.findRoot(segment, accuracy, equation));
                break;
            default:
                throw new IllegalArgumentException("Выбрана несуществующая опция");
        }
        for (int i=0; i<roots.size(); i++) {
            System.out.println("Корень_" + (i+1) + " " + roots.get(i).toString() + ";");
            System.out.println("Значение функции" + (i+1) + " " + Functions.f(roots.get(i).getRoot(), equation) + ";");
        }
        FunctionByOneVariableGraph graph = new FunctionByOneVariableGraph(usersSegment);
        panel.addFunctionGraph(graph);
    }


    public static void systemOfEquationsSolving(Scanner scanner, GraphPanel panel) throws IllegalAccessException, InstantiationException {

        System.out.println("Выберите систему уравнений \n1 -- " + Functions.p11Str() + " и " + Functions.p12Str() + ";\n2 -- "
                + Functions.p21Str() + " и " + Functions.p22Str());
        int system = scanner.nextInt();
        if (system != 1 && system != 2) {
            throw new IllegalArgumentException("Выбрана несуществующая опция");
        }
        panel.setN(system);

        System.out.println("Введите начальное приближение x");
        double x = scanner.nextDouble();
        System.out.println("Введите начальное приближение y");
        double y = scanner.nextDouble();

        System.out.println("Введите точность");
        double accuracy = scanner.nextDouble();

        TwoVariablesResult root = SystemNewtonMethod.findRoot(x, y, accuracy, system);

        System.out.println("Корень " + root + ";");

        System.out.println("Значения системы " + Functions.p(root.getRoot()[0], root.getRoot()[1], system, 1) +
                ", " + Functions.p(root.getRoot()[0], root.getRoot()[1], system, 2) + ";");

        FunctionByTwoVariablesGraph graph1 = new FunctionByTwoVariablesGraph(new Segment[]{new Segment(-10, 10),
                new Segment(-10, 10)},0.005, 1);
        FunctionByTwoVariablesGraph graph2 = new FunctionByTwoVariablesGraph(new Segment[]{new Segment(-10, 10),
                new Segment(-10, 10)},0.005, 2);
        panel.addFunctionGraph(graph1);
        panel.addFunctionGraph(graph2);
    }
}