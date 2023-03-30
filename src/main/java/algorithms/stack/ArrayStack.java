package algorithms.stack;

import org.jetbrains.annotations.NotNull;

public class ArrayStack<E> implements Stack<E>{
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
        if (stack.length == n){
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
        if (n == size()/4){
            resize(size()/2);
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
}
