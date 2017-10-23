package hr.fer.oop.lab1.prob4;

import java.io.Console;
import java.util.Map;

import hr.fer.oop.*;

import java.util.HashMap;
/**
 * PrimeNumbers
 */
public class PrimeFactiorization {
    private static String toNumberString = "Number to factorize";
    public static void calculate(HashMap<String, Integer> input)
    {
        int i = 0;
        Integer n = input.get(toNumberString);
        Double maxPime = Math.sqrt(input.get(toNumberString));
        int[] primes = CalculatePrimes.sieveOfEratosthenesWithPrimesToN(maxPime.intValue() + 1);
        System.out.printf("Prime factors of number %d are:%n", n);
        for (int prime : primes) {
            if (prime == 0)
                continue;
            while ((n % prime) == 0){
                n/=prime;
                System.out.printf("%d. factor: %d%n",i+1,prime);
                i++;
            }
        }
    }
    public static void main(String[] args) {
        Input<Integer> input = new Input<Integer>("Integer");
        String[] params = new String[]{toNumberString};
        Boolean IsOk = input.handleInput(params, args, (d) -> Integer.toString(d), (d) -> Integer.parseInt(d), (d) -> d > 1);
        if (IsOk)
            calculate(input.input);
        return; 
    }
}