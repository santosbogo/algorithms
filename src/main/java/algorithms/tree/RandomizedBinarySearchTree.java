package algorithms.tree;

import algorithms.queue.ArrayQueue;
import algorithms.queue.LinkedListQueue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedBinarySearchTree<Key, Value> implements TreeMap<Key, Value> {


    private Node<Key, Value> root;
    private int size;
    private final Comparator<Key> comparator;
    private final Random random = new Random();

    public RandomizedBinarySearchTree(Comparator<Key> comparator) {
        root = null;
        size = 0;
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private Node<Key, Value> find(Node<Key, Value> node, Key key) {
        if (node == null) return null;

        int comp = comparator.compare(key, node.key);
        if (comp > 0) return find(node.right, key);
        else if (comp < 0) return find(node.left, key);
        else return node;
    }

    @Override
    public boolean contains(@NotNull Key key) {
        return find(root, key) != null;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node<Key, Value> node = find(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void put(@NotNull Key key, Value value) {
        root = randomizedPut(root, key, value);
    }

    private Node<Key, Value> randomizedPut(Node<Key, Value> node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        boolean randomRoot = random.nextInt(size) == 0;
        if (randomRoot) {
            node = rootPut(node, key, value);
        } else {
            int comp = comparator.compare(key, node.key);
            if (comp > 0) node.right = randomizedPut(node.right, key, value);
            if (comp < 0) node.left = randomizedPut(node.left, key, value);
            else node.value = value;
        }
        return node;
    }

    private Node<Key, Value> rootPut(Node<Key, Value> node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        } else {
            int comp = comparator.compare(key, node.key);
            if (comp > 0) {
                node.right = randomizedPut(node.right, key, value);
                return rotateLeft(node);
            }
            if (comp < 0) {
                node.left = randomizedPut(node.left, key, value);
                return rotateRight(node);
            } else {
                node.value = value;
                return node;
            }
        }
    }

    private Node<Key, Value> rotateRight(Node<Key, Value> node) {
        if (node.left != null) {
            Node<Key, Value> result = node.left;
            node.left = result.right;
            result.right = node;
            return result;
        } else return null;
    }

    private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
        if (node.right != null) {
            Node<Key, Value> result = node.right;
            node.right = result.left;
            result.left = node;
            return result;
        } else return null;
    }

    @Override
    public void remove(@NotNull Key key) {
        if (!contains(key)) throw new NoSuchElementException();
        size--;
        root = remove(root, key);
    }

    private Node<Key, Value> remove(Node<Key, Value> node, Key key) {

        int comp = comparator.compare(key, node.key);

        if (comp > 0) node.right = remove(node.right, key);
        else if (comp < 0) node.left = remove(node.left, key);
        else {
            if (node.right == null) return node.left;
            else if (node.left == null) return node.right;
            else {
                Node<Key, Value> lower = find(node.left, min(node.right));
                lower.right = node.right;
                lower.left = node.left;
                remove(lower.right, lower.key);
                return lower;
            }
        }
        return node;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Iterator<Key> inOrder() {
        ArrayQueue<Key> keys = new ArrayQueue<Key>();
        inOrder(root, keys);
        return keys.iterator();
    }
    private void inOrder(Node<Key, Value> node, ArrayQueue<Key> keys) {
        if (node == null) return;
        inOrder(node.left, keys);
        keys.enqueue(node.key);
        inOrder(node.right, keys);
    }

    @Override
    public Iterator<Key> postOrder() {
        ArrayQueue<Key> keys = new ArrayQueue<>();
        postOrder(root, keys);
        return keys.iterator();
    }
    private void postOrder(Node<Key,Value> node, ArrayQueue<Key> keys) {
        if (node == null) return;;
        postOrder(node.left, keys);
        postOrder(node.right, keys);
        keys.enqueue(node.key);
    }

    @Override
    public Iterator<Key> preOrder() {
        ArrayQueue<Key> keys = new ArrayQueue<>();
        preOrder(root, keys);
        return keys.iterator();
    }
    private void preOrder(Node<Key,Value> node, ArrayQueue<Key> keys) {
        if (node == null) return;
        keys.enqueue(node.key);
        preOrder(node.left, keys);
        preOrder(node.right, keys);
    }

    @Override
    public Iterator<Key> levelOrder() {
        return new LevelOrderIterator();
    }
    private class LevelOrderIterator implements Iterator<Key>{
        ArrayQueue<Node<Key, Value>> nodes = new ArrayQueue<>();
        Node<Key, Value> node = root;

        public LevelOrderIterator(){
            if (node != null) nodes.enqueue(node);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public Key next() {
            Node<Key, Value> temp = nodes.dequeue();
            if(node.left != null) nodes.enqueue(node.left);
            if(node.right != null) nodes.enqueue(node.right);
            return temp.key;
        }
    }

    @Override
    public void removeMin() {
        remove(min());
    }

    @Override
    public void removeMax() {
        if (isEmpty()) throw new NoSuchElementException();
        remove(max());
    }

    @Override
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root);
    }
    private Key min (Node<Key, Value> node){
        if (node.left == null) return node.key;
        return min(node.left);
    }

    @Override
    public Key max() {
        return max(root);
    }
    private Key max(Node<Key, Value> node){
        if (node.right == null) return node.key;
        return max(node.right);
    }
}