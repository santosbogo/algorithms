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
    public void put(@NotNull Key key, Value value) {
        if (root == null) { //Case 1 node to insert is the root
            root = new Node<>(key, value, BLACK);
            size ++;
            return;
        }
        else if(comparator.compare(key, root.key) == 0){ //Case 1 node to insert is the root
            root.value = value;
            return;
        }
        root = put(root, key, value);
    }
    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value) {

        if (node == null) {
            size++;
            return new Node<>(key, value, RED);
        }

        int comp = comparator.compare(key, node.key);

        if (comp > 0) node.right = put(node.right, key, value);
        else if (comp < 0) node.left = put(node.left, key, value);
        else return node;

        //Check properties of RedBlack trees
        if (node.right.color == RED && node.left.color == BLACK) {
            node = rotateLeft(node);
        }
        if(node.left.color == RED && node.left.left.color == RED){
            node = rotateRight(node);
        }
        if(node.left.color == RED && node.right.color == RED){
            recolor(node);
        }
        return node;
    }

    private void recolor(Node<Key, Value> node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
    private Node<Key, Value> rotateRight(Node<Key, Value> node){
        if (node.left != null) {
            Node<Key, Value> result = node.left;
            node.left = result.right;
            result.right = node;
            result.color = node.color;
            node.color = RED;
            return result;
        } else return node; // QUE PASA SI NO ES POSIBLE ROTAR?????
    }
    private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
        if (node.right != null) {
            Node<Key, Value> result = node.right;
            node.right = result.left;
            result.left = node;
            result.color = node.color;
            node.color = RED;
            return result;
        } else return node; // QUE PASA SI NO ES POSIBLE ROTAR?????
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
