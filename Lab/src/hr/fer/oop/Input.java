package hr.fer.oop;

import java.util.HashMap;
import java.util.function.Function;
import java.util.Map;

public class Input<T> {
    public HashMap<String, T> input;
    private String genericType;
    public Input(String geType) {
        input = new HashMap<>();
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
        input = new HashMap<String, T>();
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
        }else if (args.length == paramNames.length)
        {
            try{
                handleInput(args, typeToString, stringToType, valueOk);
                isOk = true;
            }catch(Exception ex){
                System.out.printf("Error with parsing input with %d arguments%n", args.length);
            }
        }
        if (!isOk){
            // The short answer is, that there is no way to find out the runtime type of generic type parameters in Java. 
            // I suggest reading the chapter about type erasure in the Java Tutorial for more details.
            // A popular solution to this is to pass the Class of the type parameter into the constructor of the generic type - I don't wanna do this...
            System.out.printf("Bad input%nGood inputs are:%nNo args%n%d %ss%n", paramNames.length, genericType);
            return false;
        }
        return true;
    }
}