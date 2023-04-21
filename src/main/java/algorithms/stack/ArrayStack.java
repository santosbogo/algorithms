package algorithms.stack;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;

public class ArrayStack<E> implements Stack<E>, Iterable<E> {
    private E[] stack;
    private int n;

    public ArrayStack(){
        stack = (E[]) new Object[10];
    }

    public ArrayStack(int size){
        stack = (E[]) new Object[size];
    }

    private void resize(int size){
        E[] stack2 = (E[]) new Object[size];
        for (int i = 0; i < size(); i++)
            stack2[i] = stack[i];
        stack = stack2;
    }

    @Override
    public void push(@NotNull E item) {
        if (totalspace() == n){
            resize(size()*2);
        }
        stack[n] = item;
        n++;
    }

    @Override
    public E pop() {
        if (isEmpty()){
            throw new IllegalStateException();
        }
        if (n == (stack.length / 4)){
            resize(stack.length/2);
        }
        E old = stack[--n];
        stack[n] = null;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    public int totalspace(){
        return stack.length;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new IterableArrayStack();
    }

    private class IterableArrayStack implements Iterator<E> {
        private int pointer = n;
        @Override
        public boolean hasNext() {
            return pointer > 0;
        }

        @Override
        public E next() {
            return stack[--pointer];
        }
    }
}
