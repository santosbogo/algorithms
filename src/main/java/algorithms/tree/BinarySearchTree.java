package algorithms.tree;


import algorithms.queue.ArrayQueue;
import algorithms.queue.LinkedListQueue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<Key, Value> implements TreeMap<Key, Value>{

    final Comparator<Key> comparator;
    Node<Key, Value> root;
    private int size;

    public BinarySearchTree(Comparator<Key> comparator) {
        this.comparator = comparator;
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(@NotNull Key key) {
        return get(key) != null;
    }

    private Node<Key, Value> find (Node<Key, Value> node, Key key){
        if (node == null) return null;

        int comp = comparator.compare(key, node.key);   // 0 if key == node.key, + if key < node.key, - if key > node.key
        if (comp > 0) return find(node.right, key);
        if (comp < 0) return find(node.left, key);
        else return node;
    }

    @Override
    public Value get(@NotNull Key key) {
        if (find(root, key) == null) return null;
        return (find(root, key).value);
    }

    @Override
    public void put(@NotNull Key key, Value value) {
        root = put(root, key, value);
    }
    private Node<Key, Value> put (Node<Key, Value> node, Key key, Value value){
        if (node == null){
            size++;
            return new Node<>(key, value);
        }

        int comp = comparator.compare(key, node.key);
        if (comp == 0) node.value = value;
        else if (comp > 0) node.right = put(node.right, key, value);
        else if(comp < 0) node.left = put(node.left, key, value);

        return node;
    }

    @Override
    public void remove(Key key) {
        if (!contains(key)) throw new NoSuchElementException();

        size --;
        root = remove(root, key);
    }
    private Node<Key, Value> remove (Node<Key, Value> node, Key key){

        if (node == null) return null;

        int comp = comparator.compare(key, node.key);

        if (comp > 0){
            node.right = remove(node.right, key);//1
        }
        else if (comp < 0) {
            node.left = remove(node.left, key);//2
        }
        else{
            if (node.left == null && node.right == null) return null; //Leaf node
            if (node.left == null) return node.right; //Node just have right child
            if (node.right == null) return node.left; //Node just have left child
            else{ //Has right and left children
                Node<Key, Value> bigger = find(node.left, max(node.left)); //Bigger node of the left son
                bigger.right = node.right; //Now put the bigger in the position of the deleted
                bigger.left = node.left;
                remove(bigger.left, max(node.left)); // if we dont do this we have 2 nodes with same key
                return bigger;//2
            }
        }
        return node; //1
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    //1: Process left
    //2: Process root
    //3: Process right
    @Override
    public Iterator<Key> inOrder() {
        final ArrayQueue<Key> keys = new ArrayQueue<>();
        inOrder(root, keys);
        return keys.iterator();
    }
    private void inOrder(Node<Key, Value> node, ArrayQueue<Key> keys){
        if (node == null) return;
        inOrder(node.left, keys);
        keys.enqueue(node.key);
        inOrder(node.right, keys);
    }

    //1: Process left
    //2: Process right
    //3: Process root
    @Override
    public Iterator<Key> postOrder() {
        final ArrayQueue<Key> keys = new ArrayQueue<>();
        postOrder(root, keys);
        return keys.iterator();
    }
    private void postOrder(Node<Key, Value> node, ArrayQueue<Key> keys){
        if (node == null) return;
        postOrder(node.left, keys);
        postOrder(node.right, keys);
        keys.enqueue(node.key);
    }

    //1: Process root
    //2: Process left
    //3: Process right
    @Override
    public Iterator<Key> preOrder() {
        ArrayQueue<Key> keys = new ArrayQueue<>();
        preOrder(root, keys);
        return keys.iterator();
    }
    private void preOrder(Node<Key,Value> node, ArrayQueue<Key> keys){
        if (node == null) return;
        keys.enqueue(node.key);
        preOrder(node.left, keys);
        preOrder(node.right, keys);
    }

    //1: Process root
    //2: Process next level left to right
    @Override
    public Iterator<Key> levelOrder() {
        return new LevelOrderIterator();
    }
    private class LevelOrderIterator implements Iterator{
        ArrayQueue<Node<Key, Value>> nodes = new ArrayQueue<>();

        Node<Key, Value> head;
        public LevelOrderIterator(){
            head = root;
            if (head != null) {
                nodes.enqueue(head);
            }
            //else {throw new NoSuchElementException();
        }
        public LevelOrderIterator(Node<Key, Value> node) {
            head = node;
            if (head != null) {
                nodes.enqueue(head);
            }
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public Key next() {
            Node<Key, Value> next = nodes.dequeue();
            if (next.left != null) nodes.enqueue(next.left);
            if (next.right != null) nodes.enqueue(next.right);
            return next.key;
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
