package pro.sky;

import pro.sky.exception.IntegerIsNullException;
import pro.sky.exception.IntegerNotFoundException;
import pro.sky.exception.StringListSizeExceededException;

public class IntegerList implements StringList {
    private static final int DEFAULT_SIZE = 10;
    private final Integer[] numbers;
    private int freeCells;

    public IntegerList() {
        this(DEFAULT_SIZE);
    }

    public IntegerList(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Illegal capacity: " + size);
        }
        this.numbers = new Integer[size];
        this.freeCells = size;
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (freeCells == 0) {
            throw new StringListSizeExceededException();
        }
        numbers[numbers.length - freeCells] = Integer.parseInt(item);
        freeCells--;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        if (index < 0 || index > numbers.length - 1) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (freeCells == 0) {
            throw new StringListSizeExceededException();
        }
        for (int i = numbers.length - 1; i > index; i--) {
            numbers[i] = numbers[i - 1];
        }
        numbers[index] = Integer.parseInt(item);
        freeCells--;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        if (index < 0 || index > numbers.length - 1) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        numbers[index] = Integer.parseInt(item);
        return item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        int foundIndex;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null && numbers[i].equals(Integer.parseInt(item))) {
                foundIndex = i;
                return remove(foundIndex);
            }
        }
        throw new IntegerNotFoundException(Integer.parseInt(item));
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (index >= numbers.length - freeCells) {
            throw new IntegerIsNullException();
        }
        String removedItem = String.valueOf(numbers[index]);
        for (int i = index; i < numbers.length - freeCells - 1; i++) {
            numbers[i] = numbers[i + 1];
        }
        numbers[numbers.length - freeCells - 1] = null;
        freeCells++;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        Integer number = Integer.parseInt(item);
        int index = binarySearch(number);
        return index >= 0;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        Integer number = Integer.parseInt(item);
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null && numbers[i].equals(number)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        Integer number = Integer.parseInt(item);
        for (int i = numbers.length - freeCells - 1; i >= 0; i--) {
            if (numbers[i] != null && numbers[i].equals(number)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (numbers[index] == null) {
            throw new IntegerIsNullException();
        }
        return String.valueOf(numbers[index]);
    }

    @Override
    public boolean equals(StringList otherList) throws IntegerIsNullException {
        if (this == otherList) {
            return true;
        }
        if (otherList == null || size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            String thisString = get(i);
            String otherString = otherList.get(i);
            if ((thisString == null && otherString != null) || !thisString.equals(otherString)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return numbers.length - freeCells;
    }

    @Override
    public boolean isEmpty() {
        return numbers.length == freeCells;
    }

    @Override
    public void clear() {
        for (int i = 0; i < numbers.length - freeCells; i++) {
            numbers[i] = null;
        }
        freeCells = numbers.length;
    }

    @Override
    public String[] toArray() {
        String[] array = new String[numbers.length - freeCells];
        for (int i = 0; i < array.length; i++) {
            array[i] = String.valueOf(numbers[i]);
        }
        return array;
    }

    public void print() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Элемент " + i + ": " + numbers[i]);
        }
    }

    private void insertionSort() {
        for (int i = 1; i < numbers.length - freeCells; i++) {
            Integer key = numbers[i];
            int j = i - 1;
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = key;
        }
    }

    private int binarySearch(Integer key) {
        insertionSort();

        int low = 0;
        int high = numbers.length - freeCells - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Integer midValue = numbers[mid];

            if (midValue.equals(key)) {
                return mid;
            } else if (midValue < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
