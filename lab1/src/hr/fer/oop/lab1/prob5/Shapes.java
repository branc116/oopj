package hr.fer.oop.lab1.prob5;

public class Shapes {
    public static String line1 = "+--------+";
    public static String line2 = "\\        /";
    public static String line3 = " \\      / ";
    public static String line4 = "   -----   ";
    public static String line5 = " /      \\ ";
    public static String line6 = "/        \\";
    public static void hourGlass(){
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line4);
        System.out.println(line5);
        System.out.println(line6);
        System.out.println(line1);
    }
    public static void hourGlass2(){
        System.out.println(line4);
        System.out.println(line5);
        System.out.println(line6);
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
    }
    public static void hourGlass3(){
        System.out.println(line2);
        System.out.println(line3);
        System.out.println(line4);
        System.out.println(line1);
    }
    public static void hourGlass4(){
        System.out.println(line4);
        System.out.println(line5);
        System.out.println(line6);
        System.out.println(line1);
    }
    public static void main(String[] args) {
        hourGlass();
        hourGlass2();
        hourGlass3();
        hourGlass4();
    }
}