package algorithms.queue;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Queue<E>{
    private Node first;
    private Node last;
    private int n = 0;

    private class Node{
        E item;
        Node next;
    }

    public void enqueue(@NotNull E item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        }
        else{
            oldlast.next = last;
        }
        n++;
    }
    public @NotNull E dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        E stat = first.item;
        first = first.next;
        n--;
        return stat;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public Iterator<E> iterator(){
        return new ItterableLinkedListQueue();
    }

    private class ItterableLinkedListQueue implements Iterator<E>{
        private Node pointer = first;

        public boolean hasNext(){
            return pointer != null;
        }

        public E next(){
            E stat = pointer.next.item;
            pointer = pointer.next;
            return  stat;
        }
    }
}
