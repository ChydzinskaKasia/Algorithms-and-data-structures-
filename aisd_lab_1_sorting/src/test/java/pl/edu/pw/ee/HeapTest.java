package pl.edu.pw.ee;
import org.junit.*;

public class HeapTest {
    private Heap<Double> testHeap;

    @Test
    public void popElementCheck() {
        Double[] testArray = { 5.0, 20.0, 1.0, 4.0, 6.0, 15.0, 9.0, 10.0, 1.0, 7.0, 5.0 };

        testHeap = new Heap<>(testArray);
        Double rootValue = testHeap.pop();
        Double expectedValue = 20.0;

        Assert.assertEquals(rootValue, expectedValue);
    }

    @Test
    public void popEmptyHeapCheck() {
        testHeap = new Heap<>(null);
        Double rootValue = testHeap.pop();
        Double expectedValue = null;
        Assert.assertEquals(rootValue, expectedValue);
    }

    @Test
    public void putElementCheck() {
        Double[] testArray = { 2.0, 5.0, 3.0, 7.0 };
        testHeap = new Heap<>(testArray);
        double putValue = 10.0;

        testHeap.put(putValue);

        Double expectedValue = 10.0;
        Assert.assertEquals(testHeap.getHeapList().get(0), expectedValue);
    }

}
