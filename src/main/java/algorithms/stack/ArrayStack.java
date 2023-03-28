package algorithms.stack;

import org.jetbrains.annotations.NotNull;

public class ArrayStack<E> implements Stack<E>{

    private E[] stack;
    private int point;

    public ArrayStack(){
        stack = (E[]) new Object[10];
    }
    public ArrayStack(int size){
        stack = (E[]) new Object[size];
    }

    private void resize(int size){
        E[] newstack = (E[]) new Object[size];
        for (int i = 0; i < size(); i++)
            newstack[i] = stack[i];
        stack = newstack;
    }

    @Override
    public void push(@NotNull E item) {
        if (size() == point){
            resize(size()*2);
        }
        stack[point] = item;
        point++;
    }

    @Override
    public E pop() {
        if (point == size()/4){
            resize(size()/2);
        }
        E old = stack[--point];
        stack[point] = null;
        return old;
    }

    @Override
    public boolean isEmpty() {
        return point == 0;
    }

    @Override
    public int size() {
        return stack.length;
    }
}
