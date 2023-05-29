package pro.sky;

public class Main {
    public static void main(String[] args) {
        StringList newStrings = new StringListImpl();
        for (int i = 0; i < 10; i++) {
            newStrings.add("Hello world! " + i);
        }
        newStrings.print();
    }
}