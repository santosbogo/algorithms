package algorithms.stack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E>{
    private Node first;
    private int n = 0;

    private class Node{
        E item;
        Node next;
    }

    public void push(@NotNull E item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public E pop(){
        if(size() == 0){
            throw new NoSuchElementException();
        }
        E stat = first.item;
        first = first.next;
        n--;
        return stat;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public Iterator<E> iterator(){
        return new ItterableLinkedList();
    }

    private class ItterableLinkedList implements Iterator<E>{
        private Node pointer = first;

        public boolean hasNext() {
            return pointer != null;
        }

        public E next(){
            E stat = pointer.next.item;
            pointer = pointer.next;
            return stat;
        }
    }
}
