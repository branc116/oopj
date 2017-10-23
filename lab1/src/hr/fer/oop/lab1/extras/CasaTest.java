package hr.fer.oop.lab1.extras;

public class CasaTest {
    public Character waterOrIce;
    public Integer amount;
    public CasaTest(Character kind, Integer n) {
        this.waterOrIce = kind;
        this.amount = n;
    }
    public Boolean IsIce() {
        return waterOrIce == 'L';
    }
    public Boolean IsWatter() {
        return waterOrIce == 'V';
    }
    @Override
    public String toString() {
        if (waterOrIce != null && amount != null)
            return waterOrIce + " " + amount.toString();
        return "Undefined";
    }
    public static CasaTest fromString(String str){
        
        String num = str.substring(2);
        Character kind = str.charAt(0);
        // System.out.printf("From string: %s | %s, %s %n", str, num, kind);
        return new CasaTest(kind, Integer.parseInt( num));
    }
}