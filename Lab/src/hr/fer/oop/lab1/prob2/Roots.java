package hr.fer.oop.lab1.prob2;

import java.io.Console;
import java.util.Dictionary;
import java.util.Map;

import hr.fer.oop.Input;

import java.util.HashMap;
/**
 * Roots
 */
public class Roots {    
    public static void calculate(Map<String, Double> roots)
    {
        Complex complex = new Complex(roots.get("Real part"), roots.get("Imaginary part"));
        Integer root = ((Double)roots.get("Root power")).intValue();
        Complex[] SquerRoots = complex.GetRoots(root);
        System.out.printf("%d roots of %s are:%n", root, complex.toString());
        for (Complex SquerRoot : SquerRoots){
            System.out.println(SquerRoot.toString());
        }
        return;
    }
    public static void main(String[] args) {
        Input<Double> input = new Input<Double>("Double");
        String[] params = new String[]{"Real part", "Imaginary part", "Root power"};
        Boolean IsOk = input.handleInput(params, args, (d) -> Double.toString(d), (d) -> Double.parseDouble(d), (d) -> d > 0);
        if (!IsOk)
            return;
        System.out.println();
        calculate(input.input);
        return; 
    }
}
