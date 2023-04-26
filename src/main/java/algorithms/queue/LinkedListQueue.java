package algorithms.queue;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E> {
    private Node first;
    private Node last;
    private int n;
    private class Node{
        E item;
        Node next;
    }

    @Override
    public void enqueue(@NotNull E item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }
        else{
            oldlast.next = first;
        }
        n++;

    }

    @Override
    public @NotNull E dequeue() {
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        E stat = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        n--;
        return stat;
    }

    @Override
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public int size() {
        return n;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new ItterableLinkedListQueueIterator();
    }

    private class ItterableLinkedListQueueIterator implements Iterator<E>{
        private final int pointer = n;
        private Node head = first;
        @Override
        public boolean hasNext() {
            return pointer>0;
        }

        @Override
        public E next() {
            E stat = head.next.item;
            head = head.next;
            return stat;
        }
    }
}
