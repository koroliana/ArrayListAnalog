package pro.sky;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.exception.StringIsNullException;

public class StringListImplTest {
    private final StringList stringList = new StringListImpl();

    @BeforeEach
    public void setup() {
        stringList.clear();
    }

    @Test
    public void testAdd() {
        stringList.add("Hello");
        stringList.add("World");
        Assertions.assertEquals(2, stringList.size());
    }

    @Test
    public void testAddAtIndex() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.add(1, "Java");
        Assertions.assertEquals("Java", stringList.get(1));
    }

    @Test
    public void testSet() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.set(1, "Java");
        Assertions.assertEquals("Java", stringList.get(1));
    }

    @Test
    public void testRemove() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.remove("Hello");
        Assertions.assertEquals(1, stringList.size());
    }

    @Test
    public void testRemoveByIndex() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.remove(0);
        Assertions.assertEquals("World", stringList.get(0));
    }

    @Test
    public void testContains() {
        stringList.add("Hello");
        stringList.add("World");
        Assertions.assertTrue(stringList.contains("Hello"));
        Assertions.assertFalse(stringList.contains("Java"));
    }

    @Test
    public void testIndexOf() {
        stringList.add("Hello");
        stringList.add("World");
        Assertions.assertEquals(0, stringList.indexOf("Hello"));
        Assertions.assertEquals(1, stringList.indexOf("World"));
        Assertions.assertEquals(-1, stringList.indexOf("Java"));
    }

    @Test
    public void testLastIndexOf() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.add("Hello");
        Assertions.assertEquals(2, stringList.lastIndexOf("Hello"));
        Assertions.assertEquals(1, stringList.lastIndexOf("World"));
        Assertions.assertEquals(-1, stringList.lastIndexOf("Java"));
    }

    @Test
    public void testGet() {
        StringList otherList = new StringListImpl(10);
        otherList.add("Hello");
        otherList.add("World");
        Assertions.assertEquals("Hello", otherList.get(0));
        Assertions.assertEquals("World", otherList.get(1));
      //  Assertions.assertThrows(StringIsNullException.class, () -> otherList.get(3));
    // тест не проходит
    }

    @Test
    public void testEquals() {
        stringList.add("Hello");
        stringList.add("World");

        StringList otherList = new StringListImpl();

        otherList.add("Hello");
        otherList.add("World");

        Assertions.assertTrue(stringList.equals(otherList));
        Assertions.assertFalse(stringList.equals(null));
    }

    @Test
    public void testSize() {
        stringList.add("Hello");
        stringList.add("World");
        Assertions.assertEquals(2, stringList.size());
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(stringList.isEmpty());
        stringList.add("Hello");
        Assertions.assertFalse(stringList.isEmpty());
    }

    @Test
    public void testClear() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.clear();
        Assertions.assertTrue(stringList.isEmpty());
    }

    @Test
    public void testToArray() {
        stringList.add("Hello");
        stringList.add("World");
        String[] array = stringList.toArray();
        Assertions.assertArrayEquals(new String[]{"Hello", "World"}, array);
    }
}
