package pro.sky;

import pro.sky.exception.IntegerIsNullException;
import pro.sky.exception.IntegerNotFoundException;

import java.util.Objects;

public class IntegerListImpl implements IntegerList {
    private static final int DEFAULT_SIZE = 10;
    private Integer[] numbers;
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

    private void checkItem(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Integer не может быть null!");
        }
    }

    private void grow() {
        Integer[] extendedNumbers = new Integer[numbers.length + numbers.length/2];
        int extendedFreeCells = extendedNumbers.length;
        for (int i = 0; i < numbers.length; i++) {
            extendedNumbers[i] = numbers[i];
            extendedFreeCells--;
        }
        this.numbers = extendedNumbers;
        this.freeCells = extendedFreeCells;

    }

    @Override
    public Integer add(Integer item) {
        checkItem(item);
        if (freeCells == 0) {
            grow();
        }
        numbers[numbers.length - freeCells] = item;
        freeCells--;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        if (index < 0 || index > numbers.length - 1) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (freeCells == 0) {
            grow();
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
        checkItem(item);
        if (index < 0 || index > numbers.length - 1) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        numbers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
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
        checkItem(item);
        // insertionSort();
        Integer[] copy = toArray();
        quickSort(copy);
            int low = 0;
            int high = copy.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                Integer midValue = copy[mid];

                if (midValue.equals(item)) {
                    return true;
                } else if (midValue < item) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null && numbers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
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
    public boolean equals(IntegerList otherList) {
        if (this == otherList) {
            return true;
        }
        if (otherList == null || size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(get(i), otherList.get(i))) {
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
        System.arraycopy(numbers, 0, array, 0, array.length);
        return array;
    }

    public void print() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Элемент " + i + ": " + numbers[i]);
        }
    }

    //Сортировка вставкой
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

    // Быстрая сортировка

    private void quickSort(Integer[] sortableNumbers) {
        quickSort(sortableNumbers, 0, sortableNumbers.length - 1);
    }
    private void quickSort(Integer[] sortableNumbers, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(sortableNumbers, begin, end);

            quickSort(sortableNumbers, begin, partitionIndex - 1);
            quickSort(sortableNumbers, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] sortableNumbers, int begin, int end) {
        int pivot = sortableNumbers[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (sortableNumbers[j] <= pivot) {
                i++;
                swapElements(sortableNumbers, i, j);
            }
        }

        swapElements(sortableNumbers, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }


}
