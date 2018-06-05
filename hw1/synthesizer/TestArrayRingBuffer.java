package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        int expected_fillCount = 3;
        assertEquals(expected_fillCount,arb.fillCount());
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals(0,arb.first);
        int expected_remove = 1;
        int actual_remove = arb.dequeue();
        assertEquals(expected_remove, actual_remove);
        expected_fillCount = 4;
        assertEquals(expected_fillCount, arb.fillCount());
        int expected_first = 1;
        int actual_first = arb.first;
        assertEquals(expected_first,actual_first);
        arb.enqueue(6);
        for (int x : arb){
            System.out.print(x + " ");
        }
        expected_fillCount = 5;
        assertEquals(expected_fillCount,arb.fillCount());
        int expected_last = 0;
        int actual_last = arb.last;
        assertEquals(expected_last,actual_last);
        assertEquals(2,(int) arb.dequeue());
        assertEquals(3,(int) arb.dequeue());
        assertEquals(4,(int) arb.dequeue());
        assertEquals(5,(int) arb.dequeue());
        assertEquals(6,(int) arb.dequeue());
        assertEquals(arb.first,arb.last);

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
