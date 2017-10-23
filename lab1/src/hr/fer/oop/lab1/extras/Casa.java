package hr.fer.oop.lab1.extras;

import java.util.Map;

import hr.fer.oop.Input;

public class Casa {
    private final Double waterInIce = 50.0;
    private Integer capacity;
    private Integer ice;
    private Integer water;
    public Casa(Integer glassCapacity) {
        this.capacity = glassCapacity;
        this.ice = 0;
        this.water = 0;
    }
    private Integer overflowRate() {
        return Filled() - capacity;
    }
    private Integer Filled() {
        return (ice * waterInIce.intValue() + water);
    }
    public Integer NotFilled() {
        return capacity - Filled();
    }
    public Integer AddableIce() {
        return NotFilled() / waterInIce;
    }
    private void overflow() {
        if (overflowRate() < 0)
            return;
        if (water > 0) {
            Integer waterOverflow = Math.min(water, overflowRate());
            water -= waterOverflow;
            System.out.printf("Izlijeva se %dml vode%n", waterOverflow);
        }
        if (overflowRate() > 0) {
            Double iceOverflowDouble =  Math.ceil(overflowRate() / waterInIce);
            Integer iceOverflow = Math.min(ice, iceOverflowDouble.intValue());
            ice -= iceOverflow;
            System.out.printf("Izlila su se %d leda%n", iceOverflow);
        }
        return;
    }

    public void dodajVodu(Integer waterCapacity) {
        System.out.printf("Dodajem %dml vode u casu u kojoj je vec %dml vode i %d kockica leda, nakon dodavanja u casi je ukupno %dml vode!%n", 
            waterCapacity,
            this.water,
            this.ice,
            this.water + waterCapacity);
        this.water += waterCapacity;
        overflow();
        return;
    }

    public void dodajKockeLeda(Integer iceCubes) {
        System.out.printf("Dodajem %d kocki leda u casu u kojoj je vec %dml vode i %d kockica leda, nakon dodavanja u casi je ukupno %d kocki leda!%n", 
            iceCubes,
            this.water,
            this.ice,
            this.ice + iceCubes);
        this.ice += iceCubes;
        overflow();
        return;
    }
    private static String glassCapacityStr = "Kapacitet case";

    private static String testCases = "Broj umetanja";
    private static void HandleTests(Casa casa, Map<String, CasaTest> tests)
    {
        for (Map.Entry<String, CasaTest> testEntery : tests.entrySet()) {
            CasaTest test = testEntery.getValue();
            if (test.IsIce())
                casa.dodajKockeLeda(test.amount);
            else if (test.IsWatter())
                casa.dodajVodu(test.amount);
            else
                System.out.printf("Bad input %s %d%n", test.waterOrIce, test.amount);
        }
    }
    public static void main(String[] args) {
        Input<Integer> input = new Input<Integer>("Integer");
        String[] inputParams = new String[] {glassCapacityStr, testCases};
        Boolean ok = input.handleInput(inputParams, args, (d) -> Integer.toString(d), (d) -> Integer.parseInt(d), (d) -> d > 0);
        if (!ok)
            return;
        Integer capacity = input.input.get(glassCapacityStr);
        Casa casa = new Casa(capacity);
        Integer testsNum = input.input.get(testCases);
        Input<CasaTest> tests = new Input<CasaTest>("Case");
        String[] args2 = new String[testsNum];
        String[] paramName = new String[testsNum];
        Integer j = 0;
        for(Integer i=2;i<args.length;i+=2) {
            args2[j] = args[i] + ' ' + args[i + 1];
            paramName[j] = "Test " + i.toString();
            // System.out.printf("(%d, %d) %s is %s%n", i, j, paramName[j], args2[j]);
            j++;
        }
        
        ok =tests.handleInput(paramName, args2, (test) -> {
            return test.toString();
        }, (str) -> {
            return CasaTest.fromString(str);
        }, (nes) -> {return true;});
        if (!ok)
            return;
        HandleTests(casa, tests.input);
    }
}