package pro.sky.exception;

public class IntegerNotFoundException extends RuntimeException{
    private final Integer item;

    public IntegerNotFoundException(Integer item) {
        this.item = item;
    }

    public String getMessage() {
        return "Число \"" + item + "\" не найдено";
    }
}
