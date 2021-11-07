
package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class HashListChainingTest {

    private static final Random random = new Random();

    private HashListChaining<Integer> hashTable;
    private HashListChaining<String> hashTableString;

    @Test
    public void Should_Return_Null_When_Empty_ArrInput() {
        hashTable = new HashListChaining<>(15);
        assertNull(hashTable.get(6));
    }

    @Test
    public void Should_Return_OneElem_When_OneElemArray() {
        hashTable = new HashListChaining<>(15);
        hashTable.add(6);
        assertEquals(Integer.valueOf(6), hashTable.get(6));
    }

    @Test
    public void Should_Return_String_When_OneElemArray() {
        hashTableString = new HashListChaining<>(15);
        hashTableString.add("Dell");
        assertEquals(String.valueOf("Dell"), hashTableString.get("Dell"));
    }

    @Test
    public void Should_Return_Null_When_Delete_Method_Was_Called_In_String_ArrInput() {

        hashTableString = new HashListChaining<>(3);
        hashTableString.add("RasberryPi");
        hashTableString.add("Verilog");
        hashTableString.add("VHDL");
        hashTableString.add("FPGA");
        hashTableString.delete("VHDL");
        assertNull(hashTableString.get("VHDL"));
    }

    @Test
    public void Should_Return_ManyElements_When_ManyElemArrInput() {
        hashTable = new HashListChaining<>(5);
        List<Integer> dataTable = new ArrayList<>(15);
        for (int k = 0; k < 15; k++) {
            int nextElem = random.nextInt();
            dataTable.add(nextElem);
            hashTable.add(nextElem);
        }
        for (int j = 0; j < 15; j++) {
            assertEquals(dataTable.get(j), hashTable.get(dataTable.get(j)));
        }
    }

    @Test
    public void Should_Return_Null_When_Delete_Method_Was_Called() {

        hashTable = new HashListChaining<>(3);
        hashTable.add(13);
        hashTable.add(6);
        hashTable.add(10);
        hashTable.delete(6);
        assertNull(hashTable.get(6));
    }

    @Test
    public void Should_Delete_Elements_When_Delete_Method_Was_Called() {

        hashTable = new HashListChaining<>(5);
        List<Integer> dataTable = new ArrayList<>(15);
        for (int k = 0; k < 15; k++) {
            int nextElem = random.nextInt();
            dataTable.add(nextElem);
            hashTable.add(nextElem);
        }
        for (int j = 0; j < 15; j++) {
            hashTable.delete(dataTable.get(j));
        }
        for (int j = 0; j < 15; j++) {
            assertNull(hashTable.get(dataTable.get(j)));
        }
    }

}