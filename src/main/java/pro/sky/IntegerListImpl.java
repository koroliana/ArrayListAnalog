package pro.sky;

import pro.sky.exception.IntegerIsNullException;
import pro.sky.exception.IntegerNotFoundException;
import pro.sky.exception.StringListSizeExceededException;

public class IntegerListImpl implements IntegerList {
    private static final int DEFAULT_SIZE = 10;
    private final Integer[] numbers;
    private int freeCells;

    public IntegerListImpl() {
        this(DEFAULT_SIZE);
    }

    public IntegerListImpl(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Illegal capacity: " + size);
        }
        this.numbers = new Integer[size];
        this.freeCells = size;
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (freeCells == 0) {
            throw new StringListSizeExceededException();
        }
        numbers[numbers.length - freeCells] = item;
        freeCells--;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
        numbers[index] = item;
        freeCells--;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        if (index < 0 || index > numbers.length - 1) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        numbers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        int foundIndex;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null && numbers[i].equals(item)) {
                foundIndex = i;
                return remove(foundIndex);
            }
        }
        throw new IntegerNotFoundException(item);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0 || index >= numbers.length) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (index >= numbers.length - freeCells) {
            throw new IntegerIsNullException();
        }
        Integer removedItem = numbers[index];
        for (int i = index; i < numbers.length - freeCells - 1; i++) {
            numbers[i] = numbers[i + 1];
        }
        numbers[numbers.length - freeCells - 1] = null;
        freeCells++;
        return removedItem;
    }

    @Override
    public boolean contains(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        insertionSort();
        int index = binarySearch(item);
        return index >= 0;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null && numbers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        for (int i = numbers.length - freeCells - 1; i >= 0; i--) {
            if (numbers[i] != null && numbers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (numbers[index] == null) {
            throw new IntegerIsNullException();
        }
        return numbers[index];
    }

    @Override
    public boolean equals(IntegerList otherList) throws IntegerIsNullException {
        if (this == otherList) {
            return true;
        }
        if (otherList == null || size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            Integer thisNumber = get(i);
            Integer otherNumber = otherList.get(i);
            if ((thisNumber == null && otherNumber != null) || !thisNumber.equals(otherNumber)) {
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
    public Integer[] toArray() {
        Integer[] array = new Integer[numbers.length - freeCells];
        for (int i = 0; i < array.length; i++) {
            array[i] = numbers[i];
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
