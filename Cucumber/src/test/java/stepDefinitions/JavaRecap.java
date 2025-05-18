package stepDefinitions;

public class JavaRecap {

    public static void main(String[] args) {
        int a = 10;
        int b = 15;

        System.out.println(sum(a, b));
        System.out.println(print("Hello world!"));
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static String print(String text) {
        return "Print " + text;
    }

}
