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

    @Test
    public void iterationTest(){
        assertThat(testQueue.iterator().hasNext()).isFalse();
        testQueue.enqueue(3);
        assertThat(testQueue.iterator().hasNext()).isTrue();
        testQueue.enqueue(6);
        assertThat(testQueue.iterator().next()).isEqualTo(testQueue.dequeue());
    }

    @Test
    public void overflowTest(){
        testQueue.enqueue(1); testQueue.enqueue(1); testQueue.enqueue(1);
        testQueue.enqueue(1); testQueue.enqueue(1); testQueue.enqueue(1);
    }

    @Test
    public void constructorTest(){
        ArrayQueue<Integer> queue1 = new ArrayQueue<>();
    }

    @Test
    public void sizeTest(){
        testQueue.enqueue(3); testQueue.enqueue(4);
        testQueue.dequeue(); testQueue.enqueue(5);
        assertThat(testQueue.size()).isEqualTo(2);
    }

    @Test
    public void emptyTest(){
        assertThat(testQueue.isEmpty()).isTrue();
    }

}
