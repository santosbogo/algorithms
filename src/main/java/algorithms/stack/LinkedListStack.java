package algorithms.stack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class LinkedListStack<E> implements Stack<E> {
    private Node first;
    private int n = 0;

    private class Node{
        E item;
        Node next;
    }

    @Override
    public void push(@NotNull E item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    @Override
    public E pop() {
        if(n == 0){
            throw new IllegalStateException();
        }
        E stat = first.item;
        first = first.next;
        n--;
        return stat;
    }

    @Override
    public boolean isEmpty() {
        return (first == null);
    }

    @Override
    public int size() {
        return n;
    }
    public Iterator<E> iterator(){
        return new ItterableLinkedStack();
    }
    private class ItterableLinkedStack implements Iterator<E>{
        private final Node pointer = first;
        @Override
        public boolean hasNext() {
            return (pointer != null);
        }

        @Override
        public E next() {
            return pointer.next.item;
        }
    }
}
