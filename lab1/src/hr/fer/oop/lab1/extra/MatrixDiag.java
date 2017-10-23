package hr.fer.oop.lab1.extra;

import java.util.Map;

import hr.fer.oop.Input;

public class MatrixDiag {
    private static String modStr = "Velicina kvadrata";
    public static void racunaj(Map<String, Integer> input) {
        Integer mod = input.get(modStr);
        Integer sum = 0;
        for (Integer i=0;i<mod;i++){
            sum += input.get(i.toString() + '.' + i.toString());
        }
        System.out.printf("Suma djegonale je: %d%n", sum);
        return;
    }

    public static void main(String[] args) {
        Input<Integer> input = new Input<Integer>("Integer");
        String[] params = new String[args.length];
        params[0] = modStr;
        Integer mod = Integer.parseInt(args[0]);
        for(Integer i=1;i<args.length;i++){
            Integer curRow = (i - 1) % mod;
            Integer curColumn = (i - 1) / mod;
            params[i] = curRow.toString() + '.' + curColumn.toString();
        }
        Boolean IsOk = input.handleInput(params, args, (d) -> Integer.toString(d), (d) -> Integer.parseInt(d), (d) -> true);
        if (IsOk) {
            racunaj(input.input);
        }
        return;
    }
}