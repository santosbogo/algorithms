//package algorithms.tree;
//
//import algorithms.queue.ArrayQueue;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//import java.util.Random;
//
//public class RandomizedBinarySearchTree<Key, Value> implements TreeMap<Key, Value> {
//
//    private Node<Key, Value> root;
//    private int size;
//    private final Comparator<Key> comparator;
//    private final Random random = new Random();
//
//    public RandomizedBinarySearchTree(Comparator<Key> comparator) {
//        root = null;
//        size = 0;
//        this.comparator = comparator;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return (size == 0);
//    }
//
//    private Node<Key, Value> find(Node<Key, Value> node, Key key){
//        if (node == null) return null;
//
//        int comp = comparator.compare(key, node.key);
//
//        if (comp > 0) return find(node.right, key);
//        else if (comp < 0) return find(node.left, key);
//        return node; //found the node
//    }
//
//    @Override
//    public boolean contains(@NotNull Key key) {
//        return find(root, key) != null;
//    }
//
//    @Override
//    public Value get(@NotNull Key key) {
//        Node<Key, Value> node = find(root, key);
//        return node == null ? null : node.value;
//    }
//
//    @Override
//    public void put(@NotNull Key key, Value value) {
//        root = randomizedPut(root, key, value);
//    }
//
//    private Node<Key, Value> randomizedPut(Node<Key, Value> node, Key key, Value value) {
//        if (node == null) {
//            size++;
//            return new Node<>(key, value);
//        }
//
//        boolean randomRoot = random.nextInt(size) == 0;
//        if (randomRoot) return rootPut(node, key, value);
//
//        else {
//            int comp = comparator.compare(key, node.key);
//            if (comp > 0) node.right = randomizedPut(node.right, key, value);
//            else if (comp < 0) node.left = randomizedPut(node.left, key, value);
//            else node.value = value;
//        }
//        return node;
//    }
//
//    private Node<Key, Value> rootPut(Node<Key, Value> node, Key key, Value value) {
//        if (node == null) {
//            size++;
//            return new Node<>(key, value);
//        } else {
//            int comp = comparator.compare(key, node.key);
//            if (comp > 0) {
//                node.right = randomizedPut(node.right, key, value);
//                return rotateLeft(node);
//            }
//            else if (comp < 0) {
//                node.left = randomizedPut(node.left, key, value);
//                return rotateRight(node);
//            }
//            else node.value = value;
//        }
//        return node;
//    }
//    private Node<Key, Value> rotateRight(Node<Key, Value> node) {
//        if (node.left != null) {
//            Node<Key, Value> result = node.left;
//            node.left = result.right;
//            result.right = node;
//            return result;
//        }
//        return null;
//    }
//    private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
//        if (node.right != null) {
//            Node<Key, Value> result = node.right;
//            node.right = result.left;
//            result.left = node;
//            return result;
//        }
//        return null;
//    }
//
//    @Override
//    public void remove(@NotNull Key key) {
//        root = remove(root, key);
//        size --;
//    }
//    private Node<Key, Value> remove(Node<Key, Value> node, Key key){
//        if (node == null){
//            size++;
//            return null;
//        }
//
//        int comp = comparator.compare(key, node.key);
//
//        if (comp > 0) node.right = remove(node.right, key);
//        if (comp < 0) node.left = remove(node.left, key);
//        if (comp == 0) {
//            if (node.right == null) return node.left;
//            if (node.left == null) return node.right;
//            else {
//                Node<Key, Value> bigger = max(node.left);
//                node.value = bigger.value;
//                node.key = bigger.key;
//                remove(node.left, bigger.key);
//            }
//        }
//        return node;
//    }
//
//    @Override
//    public void clear() {
//        root = null;
//        size = 0;
//    }
//
//    @Override
//    public Iterator<Key> inOrder() {
//        ArrayQueue<Key> keys = new ArrayQueue<>();
//        inOrder(root, keys);
//        return keys.iterator();
//    }
//    private void inOrder(Node<Key, Value> node, ArrayQueue<Key> keys){
//        if (node == null) return;
//        inOrder(node.left, keys);
//        keys.enqueue(node.key);
//        inOrder(node.right, keys);
//    }
//
//    @Override
//    public Iterator<Key> postOrder() {
//        ArrayQueue<Key> keys = new ArrayQueue<>();
//        postOrder(root, keys);
//        return keys.iterator();
//    }
//    private void postOrder(Node<Key, Value> node, ArrayQueue<Key> keys){
//        if (node == null) return;
//        postOrder(node.left, keys);
//        postOrder(node.right, keys);
//        keys.enqueue(node.key);
//    }
//
//    @Override
//    public Iterator<Key> preOrder() {
//        ArrayQueue<Key> keys = new ArrayQueue<>();
//        preOrder(root, keys);
//        return keys.iterator();
//    }
//    private void preOrder(Node<Key, Value> node, ArrayQueue<Key> keys) {
//        if (node == null) return;
//        keys.enqueue(node.key);
//        preOrder(node.left, keys);
//        preOrder(node.right, keys);
//    }
//
//    @Override
//    public Iterator<Key> levelOrder() {
//        return new levelOrderIterator();
//    }
//    private class levelOrderIterator implements Iterator{
//        Node<Key, Value> head;
//        ArrayQueue<Node<Key, Value>> nodes = new ArrayQueue<>();
//
//        public levelOrderIterator(){
//            head = root;
//            if (!isEmpty() && head != null) nodes.enqueue(head);
//        }
//
//        @Override
//        public boolean hasNext() {
//            return !nodes.isEmpty();
//        }
//
//        @Override
//        public Object next() {
//            if (!hasNext()) throw new NoSuchElementException();
//            Node<Key, Value> node = nodes.dequeue();
//            if (node.left != null) nodes.enqueue(node.left);
//            if (node.right != null) nodes.enqueue(node.right);
//            return node.key;
//        }
//    }
//
//    @Override
//    public void removeMin() {
//        remove(min());
//    }
//
//    @Override
//    public void removeMax() {
//        remove(max());
//    }
//
//    @Override
//    public Key min() {
//        if (isEmpty()) throw new NoSuchElementException();
//        return min(root).key;
//    }
//    private Node<Key,Value> min(Node<Key, Value> node){
//        if (node.left != null) return min(node.left);
//        return node;
//    }
//
//    @Override
//    public Key max() {
//        if (isEmpty()) throw new NoSuchElementException();
//        return max(root).key;
//    }
//    private Node<Key, Value> max(Node<Key, Value> node){
//        if (node.right != null) return max(node.right);
//        return node;
//    }
//}
package algorithms.tree;

import algorithms.queue.LinkedListQueue;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Random;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedBinarySearchTree<K, V> implements TreeMap<K, V>{
    private final Comparator<K> comparator;
    private int size;
    private Node<K, V> head = null;
    private final Random random = new Random();

    public RandomizedBinarySearchTree(Comparator<K> comp){
        this.comparator = comp;
        this.size = 0;
    }

    private Node<K, V> find(Node<K, V> node, K key){
        if (node == null){return null;}

        int comp = comparator.compare(key, node.key);
        return comp == 0 ? node :
                comp < 0 ? find(node.left, key) :
                        find(node.right, key);
    }

    @Override
    public int size() {return size;}

    @Override
    public boolean contains(@NotNull K key) {return find(head, key) != null;}

    @Override
    public V get(@NotNull K key) {return find(head, key) != null ? find(head, key).value : null;}

    @Override
    public void put(@NotNull K key, V value) {
        head = randomizedPut(head, key, value);
    }
    private Node<K, V> randomizedPut (Node<K, V> node, K key, V value){
        if (node == null){
            size++;
            return new Node<>(key, value);
        }
        boolean accept = random.nextInt(size) == 0;
        if (accept) return rootPut(node, key, value);
        else {
            int comp = comparator.compare(key, node.key);

            if (comp < 0) {node.left = randomizedPut(node.left, key, value);}
            else if (comp > 0) {node.right = randomizedPut(node.right, key, value);}
            else {node.value = value;}
        }
        return node;
    }
    private Node<K, V> rootPut (Node<K, V> node, K key, V value){
        if (node == null){
            size++;
            return new Node<>(key, value);
        } else {
            int comp = comparator.compare(key, node.key);

            if (comp < 0) {
                node.left = randomizedPut(node.left, key, value);
                return rotateRight(node);
            }
            else if (comp > 0) {
                node.right = randomizedPut(node.right, key, value);
                return rotateLeft(node);
            }
            else {
                node.value = value;
            }
        }
        return node;
    }
    private Node<K, V> rotateRight(Node<K, V> node){
        if (node.left != null){
            Node<K, V> result = node.left;
            node.left = result.right;
            result.right = node;
            return result;
        }
        return null;
    }
    private Node<K, V> rotateLeft(Node<K, V> node){
        if (node.right != null){
            Node<K, V> result = node.right;
            node.right = result.left;
            result.left = node;
            return result;
        }
        return null;
    }

    @Override
    public void remove(@NotNull K key) {
        if (contains(key)) size--;
        head = remove(head, key);
    }
    private Node<K, V> remove(Node<K, V> node, K key){
        if (node == null) return null;

        int comp = comparator.compare(key, node.key);
        if (comp < 0) {
            node.left = remove(node.left, key);
            return node;
        }
        else if (comp > 0){
            node.right = remove(node.right, key);
            return node;
        }
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else{
                Node<K, V> replacement = find(node, min(node.right));
                node.key = replacement.key;
                node.value = replacement.value;
                replacement.key = key;
                node.right = remove(node.right, key);
                return node;
            }
        }
    }

    @Override
    public void clear() {head = null; size = 0;}

    @Override
    public Iterator<K> inOrder() {
        final LinkedListQueue keys = new LinkedListQueue<>();
        inOrder(head, keys);
        return keys.iterator();
    }
    private void inOrder(Node<K, V> node, LinkedListQueue<K> keys){
        if (node == null) return;
        inOrder(node.left, keys);
        keys.enqueue(node.key);
        inOrder(node.right, keys);
    }

    @Override
    public Iterator<K> postOrder() {
        final LinkedListQueue keys = new LinkedListQueue<>();
        postOrder(head, keys);
        return keys.iterator();
    }
    private void postOrder(Node<K, V> node, LinkedListQueue<K> keys){
        if (node == null) return;
        postOrder(node.left, keys);
        postOrder(node.right, keys);
        keys.enqueue(node.key);
    }

    @Override
    public Iterator<K> preOrder() {
        final LinkedListQueue keys = new LinkedListQueue<>();
        preOrder(head, keys);
        return keys.iterator();
    }
    private void preOrder(Node<K, V> node, LinkedListQueue<K> keys){
        if (node == null) return;
        keys.enqueue(node.key);
        preOrder(node.left, keys);
        preOrder(node.right, keys);
    }

    @Override
    public Iterator<K> levelOrder() {return new LevelOrderIterator();}
    private class LevelOrderIterator implements Iterator<K> {
        LinkedListQueue<Node<K, V>> queue = new LinkedListQueue<>();
        Node<K, V> node = head;
        LevelOrderIterator(){queue.enqueue(node);}
        @Override
        public boolean hasNext() {return !queue.isEmpty();}

        @Override
        public K next() {
            Node<K, V> temp = queue.dequeue();
            if (temp.left != null) queue.enqueue(temp.left);
            if (temp.right != null) queue.enqueue(temp.right);
            return temp.key;
        }
    }

    @Override
    public void removeMin() {
        if (isEmpty()) throw new NoSuchElementException();
        remove(min());
    }

    @Override
    public void removeMax() {
        if (isEmpty()) throw new NoSuchElementException();
        remove(max());
    }

    @Override
    public K min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(head);
    }

    private K min(Node<K, V> node) {
        while (node.left != null){
            node = node.left;
        }
        return node.key;
    }

    @Override
    public K max() {
        if (isEmpty()) throw new NoSuchElementException();
        Node<K, V> node = head;
        while (node.right != null){
            node = node.right;
        }
        return node.key;
    }
}
