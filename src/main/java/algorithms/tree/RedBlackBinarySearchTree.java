package algorithms.tree;

import algorithms.queue.ArrayQueue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RedBlackBinarySearchTree<Key, Value> implements TreeMap<Key, Value>{

    private final boolean RED = true;
    private final boolean BLACK = false;
    Node<Key, Value> root;
    Comparator<Key> comparator;
    int size;


    public RedBlackBinarySearchTree(Comparator<Key> comparator){
        this.comparator = comparator;
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private Node<Key, Value> find(Node<Key, Value> node, Key key){
        if(node == null) return null;

        int comp = comparator.compare(key, node.key);

        if (comp > 0) return find(node.right, key);
        else if (comp < 0) return find(node.left, key);
        return node;
    }
    @Override
    public boolean contains(@NotNull Key key) {
        return find(root, key) != null;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node<Key, Value> node = find(root, key);
        if (node == null) return null;
        return node.value;
    }

    private Node<Key, Value> findParent(Node<Key, Value> node, Key key){
        if (node == null) return null;

        int comp = comparator.compare(key, node.key);

        if (comp > 0){
            comp = comparator.compare(key, node.right.key);
            if (comp == 0) return node;
            return findParent(node.right, key);
        }
        else if (comp < 0) {
            comp = comparator.compare(key, node.left.key);
            if (comp == 0) return node;
            return findParent(node.left, key);
        }
        return null;
    }
    private Node<Key, Value> findGrandparent(Node<Key, Value> node, Key key){
        return findParent(node, findParent(node, key).key);
    }
    private Node<Key, Value> findUncle(Node<Key, Value> node, Key key){
        Node<Key, Value> parent = findParent(node, key);
        if (parent != null){
            Node<Key, Value> grandparent = findGrandparent(node, key);
            if (grandparent.right == parent) return grandparent.left;
            if (grandparent.left == parent) return grandparent.right;
        }
        return null;
    }


    @Override
    public void put(Key key, Value value) {
        root = put(root, key, value);
        root.color = false;  // Ensure the root is always black
    }

    // Helper method for insertion
    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value) {
        if (node == null) {
            // If the node is null, create a new red node
            return new Node<>(key, value, true);
        }

        int comp = comparator.compare(key, node.key);
        if (comp < 0) {
            // Recursively insert into the left subtree
            node.left = put(node.left, key, value);
        } else if (comp > 0) {
            // Recursively insert into the right subtree
            node.right = put(node.right, key, value);
        } else {
            // If the key already exists, update the value
            node.value = value;
        }

        // Perform necessary rotations and color adjustments
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    // Helper method to check if a node is red
    private boolean isRed(Node<Key, Value> node) {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    private Node<Key, Value> rotateRight(Node<Key, Value> node){
        if (node.left != null) {
            Node<Key, Value> result = node.left;
            node.left = result.right;
            result.right = node;
            result.color = node.color;
            node.color = RED;
            return result;
        } else return null; // QUE PASA SI NO ES POSIBLE ROTAR?????
    }
    private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
        if (node.right != null) {
            Node<Key, Value> result = node.right;
            node.right = result.left;
            result.left = node;
            result.color = node.color;
            node.color = RED;
            return result;
        } else return null; // QUE PASA SI NO ES POSIBLE ROTAR?????
    }

    private void flipColors(Node<Key, Value> node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }


    @Override
    public void remove(@NotNull Key key) {
        throw new RuntimeException();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Iterator<Key> inOrder() {
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
        return new levelOrderIterator();
    }
    private class levelOrderIterator implements Iterator{
        private Node<Key, Value> head;
        ArrayQueue<Node<Key, Value>> nodes = new ArrayQueue<>();

        public levelOrderIterator(){
            head = root;
            if (head != null) nodes.enqueue(head);
            else throw new NoSuchElementException();
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
