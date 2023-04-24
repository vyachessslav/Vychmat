package org.example.util;

import org.example.entities.Functions;
import org.example.entities.Segment;

import java.util.ArrayList;
import java.util.List;

public class Verificator {
    public static List<Segment> getRootsSegments(Segment segment, double stepSize, int n) {
        List<Segment> segments = new ArrayList<>();
        double x = segment.getLeftBorder();

        while (x <= segment.getRightBorder()) {
            if (Functions.f(x, n) * Functions.f(x + stepSize, n) < 0) segments.add(new Segment(x, x + stepSize));
            x += stepSize;
        }

        return segments;
    }
}