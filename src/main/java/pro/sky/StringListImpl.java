package pro.sky;

import pro.sky.exception.StringIsNullException;
import pro.sky.exception.StringListSizeExceededException;
import pro.sky.exception.StringNotFoundException;

public class StringListImpl implements StringList{
    private static final int DEFAULT_SIZE = 10;
    private final String[] strings;
    private int freeCells;

    public StringListImpl() {
        this(DEFAULT_SIZE);
    }

    public StringListImpl(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Illegal capacity: " + size);
        }
        this.strings = new String[size];
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
        strings[strings.length-freeCells] = item;
        freeCells--;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        if (index < 0 || index > strings.length-1) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (freeCells == 0) {
            throw new StringListSizeExceededException();
        }
        for (int i = strings.length-1 ; i > index; i--) {
            strings[i] = strings[i - 1];
        }
        strings[index] = item;
        freeCells--;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        if (index < 0 || index > strings.length-1) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        strings[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        int foundIndex;
        for (int i = 0; i < strings.length; i++) {
            if(strings[i].equals(item)) {
                foundIndex = i;
                return remove(foundIndex);
              }
        }
        throw new StringNotFoundException(item);


    }
    @Override
    public String remove(int index) {
        if (index < 0 || index >= strings.length) {
            throw new IndexOutOfBoundsException("Нет ячейки с номером " + index);
        }
        if (index >=  strings.length - freeCells) {
            throw new StringIsNullException();
        }
        String removedItem = strings[index];
        for (int i = index; i < strings.length - freeCells - 1; i++) {
            strings[i] = strings[i + 1];
        }
        strings[strings.length - freeCells - 1] = null;
        freeCells++;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        for (String string : strings) {
            if (item.equals(string)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Строка не может быть равна null!");
        }
        for (int i = 0; i < strings.length; i++) {
            if (item.equals(strings[i])) {
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
            for (int i = strings.length - freeCells -1; i >= 0 ; i--) {
                if (item.equals(strings[i])) {
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
        if (strings[index] == null) {
            throw new StringIsNullException();
        }
        return strings[index];
    }



    @Override
    public boolean equals(StringList otherList) throws StringIsNullException {
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
        return strings.length-freeCells;
    }

    @Override
    public boolean isEmpty() {
        return strings.length == freeCells;
    }

    @Override
    public void clear() {
        for (int i = 0; i < strings.length-freeCells; i++) {
            strings[i] = null;
        }
        freeCells = strings.length;

    }

    @Override
    public String[] toArray() {
        String[] array = new String[strings.length-freeCells];
        System.arraycopy(strings, 0, array, 0, array.length);
        return array;
    }


    public void print() {
        for (int i = 0; i < strings.length; i++) {
            System.out.println("Элемент " + i + ": " + strings[i]);
        }
    }
}
