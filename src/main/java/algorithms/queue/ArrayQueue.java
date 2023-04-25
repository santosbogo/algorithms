package algorithms.queue;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] queue;
    private int first = 0;
    private int last = 0;
    public ArrayQueue(){ queue = (E[]) new Object[10]; }

    public ArrayQueue(int capacity){queue = (E[]) new Object[capacity]; }

    private void resize(int size){
        E[] queue1 = (E[]) new Object[size];
        for (int i = first, j = 0; i < last; i++, j++)
            queue1[j] = queue[i];
        queue = queue1;
    }

    @Override
    public void enqueue(@NotNull E item) {
        if(queue.length == last){
            resize(queue.length * 2);
        }
        queue[last] = item;
        last++;
    }

    @NotNull
    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        E stat = queue[first];
        queue[first] = null;
        first++;
        return stat;
    }

    @Override
    public boolean isEmpty() { return last == first; } // Por que es public???

    @Override
    public int size() { return last - first; } // Por que es public???

    @NotNull
    @Override
    public Iterator iterator() {return new IterableArrayQueue();}

    private class IterableArrayQueue implements Iterator<E>{
        private final int lastposition = last;
        private int firstposition = first;
        @Override
        public boolean hasNext() {
            return lastposition != firstposition;
        }

        @Override
        public E next() {
            return queue[firstposition++];
        }
    }
}
