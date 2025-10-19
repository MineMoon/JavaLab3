package ru.mastiko.math;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class Calculator {

    public static double power(String xStr, String yStr) {
        int x;
        int y;
        try {
            x = parseInt(xStr);
            y = parseInt(yStr);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Unreadable argument format");
        }
        return pow(x, y);
    }

}
