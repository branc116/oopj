package hr.fer.oop.lab1.prob1;

import java.io.Console;
import java.util.Map;

import hr.fer.oop.Input;

import java.util.HashMap;
/**
 * Rectangle
 */
public class Rectangle {
    public static void calculate(HashMap<String, Double> sides)
    {
        Double area = 1.0;
        Double perimeter = 0.0;

        for(Map.Entry<String, Double> side : sides.entrySet()){
            System.out.printf("%s is %f!%n", side.getKey(), side.getValue());
            area *= side.getValue();
            perimeter += (2*side.getValue());
        }

        System.out.println();
        System.out.printf("Area is %f%n", area);
        System.out.printf("Perimeter is %f%n", perimeter);
    }
    public static void main(String[] args) {
        Input<Double> input = new Input<Double>("Double");
        String[] params = new String[]{"Height", "Width"};
        Boolean IsOk = input.handleInput(params, args, (d) -> Double.toString(d), (d) -> Double.parseDouble(d), (d) -> d > 0);
        if (IsOk)
            calculate(input.input);
        return; 
    }
}
