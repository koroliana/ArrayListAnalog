package pro.sky;

public class Main {
    public static void main(String[] args) {
        StringList newStrings = new StringListImpl();
        for (int i = 0; i < 10; i++) {
            newStrings.add("Hello world! " + i);
        }
        newStrings.print();
        System.out.println();
        IntegerList numbers = new IntegerListImpl();
        for (int i = 800; i > 700; i= i-17) {
            numbers.add(i+1);
        }
        numbers.print();
    }
}