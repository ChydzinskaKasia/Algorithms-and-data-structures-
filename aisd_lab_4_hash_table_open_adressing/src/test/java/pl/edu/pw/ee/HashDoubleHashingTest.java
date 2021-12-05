package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Random;
import static org.junit.Assert.*;
import pl.edu.pw.ee.services.HashTable;

public class HashDoubleHashingTest {
    private HashOpenAdressing<Integer> hashTableInteger;
    private HashOpenAdressing<Conflict> conflictHashTab;

    @Before
    public void setUp() {
        hashTableInteger = new HashDoubleHashing<>();
        conflictHashTab = new HashDoubleHashing<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteNoExistingElement() {
        hashTableInteger.delete(16);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowException_WhenInitialSizeIsLowerThanOne() {
        int initialSize = 0;
        HashTable<Double> hash = new HashLinearProbing<>(initialSize);

        assert false;
    }

    @Test
    public void should_CorrectlyAddNewElems_WhenNotExistInHashTable() {
        // given
        HashTable<String> emptyHash = new HashLinearProbing<>();
        String newEleme = "nothing special";

        // when
        int nOfElemsBeforePut = getNumOfElems(emptyHash);
        emptyHash.put(newEleme);
        int nOfElemsAfterPut = getNumOfElems(emptyHash);

        // then
        assertEquals(0, nOfElemsBeforePut);
        assertEquals(1, nOfElemsAfterPut);
    }

    // mechanizm refleksji
    private int getNumOfElems(HashTable<?> hash) {
        String fieldNumOfElems = "nElems";
        try {
            System.out.println(hash.getClass().getSuperclass().getName());
            Field field = hash.getClass().getSuperclass().getDeclaredField(fieldNumOfElems);
            field.setAccessible(true);

            int numOfElems = field.getInt(hash);

            return numOfElems;

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPut() {
        hashTableInteger.put(1);
        assertEquals(Integer.valueOf(1), hashTableInteger.get(1));
    }

    @Test
    public void testGetNonExistentElement() {
        assertNull(hashTableInteger.get(2));
    }

    @Test
    public void testReplace() {
        Conflict value = new Conflict(10);
        Conflict value2 = new Conflict(10);
        conflictHashTab.put(value);
        conflictHashTab.put(value2);
        assertSame(value2, conflictHashTab.get(value));
    }

    @Test
    public void testDelete() {
        hashTableInteger.put(12);
        hashTableInteger.delete(12);
        assertNull(hashTableInteger.get(12));
    }

    @Test
    public void testConfilct() {
        Conflict eight = new Conflict(8);
        conflictHashTab.put(new Conflict(7));
        conflictHashTab.put(eight);
        assertEquals(eight, conflictHashTab.get(eight));
    }

    @Test
    public void testResize() {
        Random r = new Random();
        int size = hashTableInteger.getSize();
        int newSize = size + 2;
        int[] random = new int[newSize];
        for (int i = 0; i < newSize; ++i) {
            random[i] = r.nextInt();
            hashTableInteger.put(random[i]);
        }
        for (int i = 0; i < newSize; ++i) {
            assertEquals(Integer.valueOf(random[i]), hashTableInteger.get(random[i]));
        }
    }
}
