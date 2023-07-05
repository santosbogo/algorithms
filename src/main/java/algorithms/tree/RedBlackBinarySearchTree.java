package algorithms.tree;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class RedBlackBinarySearchTree<Key, Value> implements TreeMap<Key, Value>{
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return TreeMap.super.isEmpty();
    }

    @Override
    public boolean contains(@NotNull Key key) {
        return false;
    }

    @Override
    public Value get(@NotNull Key key) {
        return null;
    }

    @Override
    public void put(@NotNull Key key, Value value) {

    }

    @Override
    public void remove(@NotNull Key key) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<Key> inOrder() {
        return null;
    }

    @Override
    public Iterator<Key> postOrder() {
        return null;
    }

    @Override
    public Iterator<Key> preOrder() {
        return null;
    }

    @Override
    public Iterator<Key> levelOrder() {
        return null;
    }

    @Override
    public void removeMin() {

    }

    @Override
    public void removeMax() {

    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
    }
}
