package hr.fer.oop;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.Map;

public class Input<T> {
    public LinkedHashMap<String, T> input;
    private String genericType;
    public Input(String geType) {
        this.genericType = geType;
    }

    private void handleInput(Function<T, String> typeToString, Function<String, T> stringToType, Function<T, Boolean> valueOk) {
        Boolean isOk = false;
        String inputTemp;
        for (Map.Entry<String, T> entry : this.input.entrySet()){
            isOk = false;
            do {
                System.out.printf("Please enter %s.%n", entry.getKey());
                inputTemp = System.console().readLine();
                    try
                    {
                        T valueConverted = stringToType.apply(inputTemp);
                        if (!valueOk.apply(valueConverted))
                            throw new Exception();
                        isOk = true;
                        input.replace(entry.getKey(), valueConverted);
                        System.out.printf("%s is %s!%n", entry.getKey(), typeToString.apply(valueConverted));
                    }
                    catch(Exception ex){
                        System.out.printf("Error: %s is not a valid value!%n", inputTemp);
                    }
            } while(!isOk);
        }
    }

    private void handleInput(String[] args, Function<T, String> typeToString, Function<String, T> stringToType, Function<T, Boolean> valueOk){
        Integer i = 0;
        for (Map.Entry<String, T> entry: this.input.entrySet()){
            T convertedValue = stringToType.apply(args[i]);
            if (!valueOk.apply(convertedValue)){
                throw new RuntimeException();
            }
            entry.setValue(convertedValue);
            System.out.printf("%s is %s!%n", entry.getKey(), typeToString.apply(convertedValue));
            i++;
        }
    }
    public Boolean handleInput(String[] paramNames, String[] args, Function<T, String> typeToString, Function<String, T> stringToType, Function<T, Boolean> valueOk){
        input = new LinkedHashMap<String, T>();
        for (String var : paramNames) {
            this.input.put(var, null);
        }
        Boolean isOk = false;
        if (args.length == 0)
        {
            try{
                this.handleInput(typeToString, stringToType, valueOk);
                isOk = true;
            }catch(Exception ex){
                System.out.printf("Error with parsing input with 0 arguments%n");
            }
        }else if (args.length >= paramNames.length)
        {
            try{
                handleInput(args, typeToString, stringToType, valueOk);
                isOk = true;
            }catch(Exception ex){
                ex.printStackTrace();
                System.out.printf("Error with parsing input with %d arguments%n", args.length);

            }
        }
        if (!isOk){
            System.out.printf("Bad input%nGood inputs are:%nNo args%n%d %ss%n", paramNames.length, genericType);
            return false;
        }
        return true;
    }
}