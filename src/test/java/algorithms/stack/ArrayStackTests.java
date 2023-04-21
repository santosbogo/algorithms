package algorithms.stack;

import org.junit.Test;

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
        assertThrows(IllegalStateException.class, () -> {
            testStack.pop();
        });
    }

    @Test
    public void iterationTest(){
        assertThat(testStack.iterator().hasNext()).isEqualTo(false);
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
    public void constructorsTest(){
        ArrayStack<Integer> stack1 = new ArrayStack<>();
        ArrayStack<Integer> stack2 = new ArrayStack<>(3);

    }
}
//ArrayStack<Integer> stack = new ArrayStack<>();
//        stack.push(4);
//        Iterator<Integer> i = stack.iterator();
//        i.hasNext();
//        i.next();