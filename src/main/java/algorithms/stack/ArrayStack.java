package algorithms.stack;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E>{
    private E[] stack;
    private int n = 0;

    public ArrayStack(int size){
        stack = (E[]) new Object[size];
    }

    public ArrayStack(){
        stack = (E[]) new Object[10];
    }

    private void resize(int capacity){
        E[] stat = (E[]) new Object[capacity];
        for (int i = 0; i < size(); i++){
            stat[i] = stack[i];
        }
        stack = stat;
    }

    public void push(@NotNull E item){
        if (stack.length == size()){
            resize(size()*2);
        }
        stack[n] = item;
        n++;
    }

    public E pop(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        if ((stack.length/4) == size()){
            resize(stack.length/2);
        }
        E stat = stack[--n];
        stack[n] = null;
        return stat;
    }

    public boolean isEmpty(){
        return  n == 0;
    }

    public int size(){
        return n;
    }

    public Iterator<E> iterator(){
        return new ItterableArrayStack();
    }

    private class ItterableArrayStack implements Iterator<E>{
        private int pointer = n - 1;

        public boolean hasNext(){
            return pointer>0;
        }

        public E next(){
            return stack[--pointer];
        }
    }
}