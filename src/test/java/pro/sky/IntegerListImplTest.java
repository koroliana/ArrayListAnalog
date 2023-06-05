package pro.sky;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {
    private IntegerListImpl list;

    @BeforeEach
    public void setUp() {
        list = new IntegerListImpl(5);
    }

    @Test
    public void testAddAndGet() {
        list.add(3);
        list.add(1);
        list.add(5);

        assertEquals(3, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void testAddAtIndex() {
        list.add(3);
        list.add(1);
        list.add(5);

        list.add(1, 2);
        assertEquals(3, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(5, list.get(3));
    }

    @Test
    public void testSet() {
        list.add(3);
        list.add(1);
        list.add(5);

        list.set(1, 2);
        assertEquals(3, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(5, list.get(2));
    }

    @Test
    public void testRemove() {
        list.add(3);
        list.add(1);
        list.add(5);

        Integer removedItem = list.remove(1);
        assertEquals(1, removedItem);
        assertEquals(3, list.get(0));
        assertEquals(5, list.get(1));
    }

    @Test
    public void testRemoveAtIndex() {
        list.add(3);
        list.add(1);
        list.add(5);

        Integer removedItem = list.remove(1);
        assertEquals(1, removedItem);
        assertEquals(3, list.get(0));
        assertEquals(5, list.get(1));
    }

    @Test
    public void testContains() {
        list.add(3);
        list.add(1);
        list.add(5);

        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    public void testIndexOf() {
        list.add(3);
        list.add(1);
        list.add(5);

        assertEquals(1, list.indexOf(1));
        assertEquals(-1, list.indexOf(2));
    }

    @Test
    public void testLastIndexOf() {
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(1);

        assertEquals(3, list.lastIndexOf(1));
        assertEquals(-1, list.lastIndexOf(2));
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size());

        list.add(3);
        list.add(1);
        list.add(5);

        assertEquals(3, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

        list.add(3);

        assertFalse(list.isEmpty());
    }

    @Test
    public void testClear() {
        list.add(3);
        list.add(1);
        list.add(5);

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToArray() {
        list.add(3);
        list.add(1);
        list.add(5);

        Integer[] array = list.toArray();

        assertEquals(3, array.length);
        assertEquals(3, array[0]);
        assertEquals(1, array[1]);
        assertEquals(5, array[2]);
    }

    @Test
    public void testEquals() {
        IntegerListImpl list1 = new IntegerListImpl(5);
        IntegerListImpl list2 = new IntegerListImpl(5);

        list1.add(3);
        list1.add(1);
        list1.add(5);

        list2.add(3);
        list2.add(1);
        list2.add(5);

        assertTrue(list1.equals(list2));
    }

    @Test
    public void testNotEquals() {
        IntegerListImpl list1 = new IntegerListImpl(5);
        IntegerListImpl list2 = new IntegerListImpl(5);

        list1.add(3);
        list1.add(1);
        list1.add(5);

        list2.add(3);
        list2.add(2);
        list2.add(5);

        assertFalse(list1.equals(list2));
    }
}
