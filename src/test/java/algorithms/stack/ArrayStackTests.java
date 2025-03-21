package algorithms.stack;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class ArrayStackTests {

    ArrayStack<Integer> testStack = new ArrayStack<>(5);

    @Test
    public void pushTest() {
        testStack.push(2);
        assertThat(testStack.size()).isEqualTo(1);
    }

    @Test
    public void popTest() {
        testStack.push(2);
        testStack.pop();
        assertThat(testStack.size()).isEqualTo(0);
    }

    @Test
    public void popEmptyTest() {
        assertThrows(NoSuchElementException.class, () -> {
            testStack.pop();
        });
    }

    @Test
    public void loiteringTest(){
        testStack.push(1);
        testStack.push(2);
        testStack.pop();

    }

    @Test
    public void resizeTest(){
        testStack.push(1); testStack.push(1); testStack.push(2);
        testStack.push(2); testStack.push(1); testStack.push(2);

        testStack.pop(); testStack.pop(); testStack.pop();
        testStack.pop(); testStack.pop(); testStack.pop();
    }

    @Test
    public void constructorTest(){
        ArrayStack<Integer> stack1 = new ArrayStack<>();
    }

    @Test
    public void iteratorTest(){
        assertThat(testStack.iterator().hasNext()).isFalse();
        testStack.push(4);
        testStack.push(8);
        testStack.push(6);
        assertThat(testStack.iterator().hasNext()).isTrue();
        assertThat(testStack.iterator().next()).isEqualTo(6);
    }

    @Test
    public void sizeTest(){
        testStack.push(4); testStack.push(5);
        testStack.pop(); testStack.push(6);
        assertThat(testStack.size()).isEqualTo(2);
    }

    @Test
    public void emptyTest (){
        testStack.push(4); testStack.pop();
        assertThat(testStack.isEmpty()).isTrue();
    }
}
