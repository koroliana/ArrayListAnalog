package pro.sky.exception;

public class StringNotFoundException extends RuntimeException{
    private final String item;

    public StringNotFoundException(String item) {
        this.item = item;
    }

    public String getMessage() {
        return "Cтрока \"" + item + "\" не найдена";
    }
}
