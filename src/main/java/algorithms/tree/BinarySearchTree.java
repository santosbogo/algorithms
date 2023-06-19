package algorithms.tree;


import algorithms.queue.ArrayQueue;
import algorithms.queue.LinkedListQueue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<Key, Value> implements TreeMap<Key, Value>{

    private final Comparator<Key> comparator;
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
        return find(root, key) != null;
    }

    private Node<Key, Value> find (Node<Key, Value> node, Key key){
        if (node == null) return null;
        int comp = comparator.compare(key, node.key);   // 0 if key == node.key, + if key < node.key, - if key > node.key
        if (comp == 0) return node;
        if (comp > 0) find(node.right, key);
        if (comp < 0) find(node.left, key);
        return node;
    }

    @Override
    public Value get(@NotNull Key key) {
        return find(root, key).value;
    }

    @Override
    public void put(@NotNull Key key, Value value) {
        Node <Key, Value> node = new Node<>(key, value);
        if (root == null) root = node;
        else if (find(root, key) != null) find(root, key).value = value;
        else root = put(root, node);
    }
    private Node<Key, Value> put (Node<Key, Value> head, Node<Key, Value> node){
        if (head == null) {
            head = node;
            size ++;
            return head;
        }

        int comp = comparator.compare(node.key, head.key);

        if (comp > 0) head.left = put(head.right, node);
        if (comp < 0) head.left = put(head.left, node);

        return head;
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
            node.right = remove(node.right, key);
        }
        else if (comp < 0) {
            node.left = remove(node.left, key);
        }
        else {
            if (node.left == null && node.right == null) return null; //Leaf node
            if (node.left == null) return node.right; //Node just have right child
            if (node.right == null) return node.left; //Node just have left child
            else{ //Has right and left children
                Node<Key, Value> bigger = find(node.left, max(node.left)); //Bigger node of the left son
                bigger.right = node.right; //Now put the bigger in the position of the deleted
                bigger.left = node.left;
                remove(bigger.left, max(node.left)); // if we dont do this we have 2 nodes with same key
                return bigger;
            }
        }
        return node;
    }

    @Override
    public void clear() {

    }

    //1: Process left
    //2: Process root
    //3: Process right
    @Override
    public Iterator<Key> inOrder() {
        final LinkedListQueue<Key> keys = new LinkedListQueue<>();
        inOrder(root, keys);
        return keys.iterator();
    }
    private void inOrder(Node<Key, Value> node, LinkedListQueue<Key> keys){
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
        final LinkedListQueue<Key> keys = new LinkedListQueue<>();
        postOrder(root, keys);
        return keys.iterator();
    }
    private void postOrder(Node<Key, Value> node, LinkedListQueue<Key> keys){
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
        LinkedListQueue<Key> keys = new LinkedListQueue<>();
        preOrder(root, keys);
        return keys.iterator();
    }
    private void preOrder(Node<Key,Value> node, LinkedListQueue<Key> keys){
        if (node == null) return;
        keys.enqueue(node.key);
        preOrder(node.left, keys);
        preOrder(node.right, keys);
    }

    //1: Process root
    //2: Process next level left to right
    @Override
    public Iterator<Key> levelOrder() {
        return new levelOrderIterator();
    }
    private class levelOrderIterator implements Iterator{
        LinkedListQueue<Node<Key, Value>> nodes = new LinkedListQueue<>();
        Node<Key, Value> node = root;

        levelOrderIterator(){
            nodes.enqueue(node);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public Key next() {
            Node <Key, Value> temp = nodes.dequeue();
            if(temp.left != null) nodes.enqueue(temp.left);
            if (temp.right != null) nodes.enqueue(temp.right);
            return temp.key;
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
