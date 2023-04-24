package algorithms.queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class ArrayQueueTests {

    ArrayQueue<Integer> testQueue = new ArrayQueue<>(5);

    @Test
    public void enqueueTest(){
        testQueue.enqueue(2);
        testQueue.enqueue(9);
    }

    @Test
    public void dequeueTest(){
        testQueue.enqueue(2);
        testQueue.dequeue();
    }

    @Test
    public void underflowTest(){
        assertThrows(NoSuchElementException.class, () ->{
            testQueue.dequeue();
        });
    }


}
