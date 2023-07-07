package algorithms.tree;


import algorithms.queue.ArrayQueue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<Key, Value> implements TreeMap<Key, Value> {

    private Node<Key, Value> root = null;
    private final Comparator<Key> comparator;
    private int size;

    public BinarySearchTree(Comparator <Key> comparator){
        this.comparator = comparator;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<Key, Value> find(Node<Key, Value> node, Key key){
        if (node == null) return null;

        int comp = comparator.compare(key, node.key);

        if (comp > 0) return find(node.right, key);
        else if (comp < 0) return find(node.left, key);
        return node; //found the node
    }

    @Override
    public boolean contains(@NotNull Key key) {
        return find(root, key) != null;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node<Key, Value> node = find(root, key);
        if (node == null) throw new NoSuchElementException();
        return node.value;
    }

    @Override
    public void put(@NotNull Key key, Value value) {
        root = put (root, key, value);
    }
    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value){
        if (node == null){
            size ++;
            return new Node<Key, Value>(key, value);
        }

        int comp = comparator.compare(key, node.key);

        if (comp == 0) node.value = value;
        else if (comp > 0) node.right = put(node.right, key, value);
        else node.left = put(node.left, key, value);

        return node;
    }

    @Override
    public void remove(@NotNull Key key) {
        get(key); //We call get becouse we use its no such ellement exeption if the key is not in the tree
        root = remove(root, key);
        size --;
    }
    private Node<Key, Value> remove(Node<Key, Value> node, Key key){
        if (node == null){
            size++;
            throw new NoSuchElementException();
        }

        int comp = comparator.compare(key, node.key);

        if (comp > 0) node.right = remove(node.right, key);
        if (comp < 0) node.left = remove(node.left, key);
        if (comp == 0) {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            else {
                Node<Key, Value> bigger = max(node.left);
                node.value = bigger.value;
                node.key = bigger.key;
                remove(node.left, bigger.key);
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
        if (isEmpty()) throw new NoSuchElementException();
        ArrayQueue<Key> keys = new ArrayQueue<>();
        inOrder(root, keys);
        return keys.iterator();
    }
    private void inOrder(Node<Key, Value> node, ArrayQueue<Key> keys){
        if (node == null) return;
        inOrder(node.left, keys);
        keys.enqueue(node.key);
        inOrder(node.right, keys);
    }

    @Override
    public Iterator<Key> postOrder() {
        if (isEmpty()) throw new NoSuchElementException();
        ArrayQueue<Key> keys = new ArrayQueue<>();
        postOrder(root, keys);
        return keys.iterator();
    }
    private void postOrder(Node<Key, Value> node, ArrayQueue<Key> keys){
        if (node == null) return;
        postOrder(node.left, keys);
        postOrder(node.right, keys);
        keys.enqueue(node.key);
    }

    @Override
    public Iterator<Key> preOrder() {
        if (isEmpty()) throw new NoSuchElementException();
        ArrayQueue<Key> keys = new ArrayQueue<>();
        preOrder(root, keys);
        return keys.iterator();
    }
    private void preOrder(Node<Key, Value> node, ArrayQueue<Key> keys) {
        if (node == null) return;
        keys.enqueue(node.key);
        preOrder(node.left, keys);
        preOrder(node.right, keys);
    }

    @Override
    public Iterator<Key> levelOrder() {
        if (isEmpty()) throw new NoSuchElementException();
        return new levelOrderIterator();
    }
    private class levelOrderIterator implements Iterator{
        private Node<Key, Value> head;
        ArrayQueue<Node<Key, Value>> nodes = new ArrayQueue<>();

        public levelOrderIterator(){
            head = root;
            if (head != null) nodes.enqueue(head);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public Object next() {
            if (!hasNext()) throw new NoSuchElementException();
            Node<Key, Value> node = nodes.dequeue();
            if (node.left != null) nodes.enqueue(node.left);
            if (node.right != null) nodes.enqueue(node.right);
            return node.key;
        }
    }

    @Override
    public void removeMin() {
        remove(min());
    }

    @Override
    public void removeMax() {
        remove(max());
    }

    @Override
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }
    private Node<Key,Value> min(Node<Key, Value> node){
        if (node.left != null) return min(node.left);
        return node;
    }

    @Override
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }
    private Node<Key, Value> max(Node<Key, Value> node){
        if (node.right != null) return max(node.right);
        return node;
    }
}