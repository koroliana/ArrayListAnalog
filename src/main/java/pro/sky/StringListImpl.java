package pro.sky;

public class StringListImpl implements StringList{

    private String[] strings;
    private int size;

    public StringListImpl(String[] strings) {
        this.strings = strings;
        this.size = strings.length;
    }

    @Override
    public String add(String item) {
        int freeCells = 0;
        for (int i = 0; i < size; i++) {
            if(strings[i].equals(null)) {
                freeCells++;
            }
        }
        if(freeCells!=0) {
            strings[size-freeCells+1] = item;
            return item;
        }
        else return null;
    }
    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.

    @Override
    public String add(int index, String item) {
        return null;
    }

    @Override
    public String set(int index, String item) {
        return null;
    }

    @Override
    public String remove(String item) {
        return null;
    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public boolean contains(String item) {
        return false;
    }

    @Override
    public int indexOf(String item) {
        return 0;
    }

    @Override
    public int lastIndexOf(String item) {
        return 0;
    }

    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }
}
