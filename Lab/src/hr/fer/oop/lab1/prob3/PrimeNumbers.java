package hr.fer.oop.lab1.prob3;

import java.io.Console;
import java.util.Map;

import hr.fer.oop.*;

import java.util.HashMap;
/**
 * PrimeNumbers
 */
public class PrimeNumbers {
    private static String toNumberString = "How many primes";

    public static void calculate(HashMap<String, Integer> input)
    {
        Integer n = input.get(toNumberString);
        int[] primes = CalculatePrimes.sieveOfEratosthenesWithNPrimeNumbers(input.get(toNumberString));
        System.out.printf("First %d primes are:%n", n);
        for (int i = 0;i<n;i++){
            System.out.printf("%d. %d%n", i + 1, primes[i]);
        }
    }
    public static void main(String[] args) {
        Input<Integer> input = new Input<Integer>("Integer");
        String[] params = new String[]{toNumberString};
        Boolean IsOk = input.handleInput(params, args, (d) -> Integer.toString(d), (d) -> Integer.parseInt(d), (d) -> d > 0);
        if (IsOk)
            calculate(input.input);
        return; 
    }
}
