//package algorithms.tree;
//
//import algorithms.queue.ArrayQueue;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//public class RedBlackBinarySearchTree<Key, Value> implements TreeMap<Key, Value>{
//
//    private final boolean RED = true;
//    Node<Key, Value> root;
//    Comparator<Key> comparator;
//    int size;
//
//
//    public RedBlackBinarySearchTree(Comparator<Key> comparator){
//        this.comparator = comparator;
//        root = null;
//        size = 0;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    private Node<Key, Value> find(Node<Key, Value> node, Key key){
//        if(node == null) return null;
//
//        int comp = comparator.compare(key, node.key);
//
//        if (comp > 0) return find(node.right, key);
//        else if (comp < 0) return find(node.left, key);
//        return node;
//    }
//    @Override
//    public boolean contains(@NotNull Key key) {
//        return find(root, key) != null;
//    }
//
//    @Override
//    public Value get(@NotNull Key key) {
//        Node<Key, Value> node = find(root, key);
//        if (node == null) return null;
//        return node.value;
//    }
//
//    private Node<Key, Value> findParent(Node<Key, Value> node, Key key){
//        if (node == null) return null;
//
//        int comp = comparator.compare(key, node.key);
//
//        if (comp > 0){
//            comp = comparator.compare(key, node.right.key);
//            if (comp == 0) return node;
//            return findParent(node.right, key);
//        }
//        else if (comp < 0) {
//            comp = comparator.compare(key, node.left.key);
//            if (comp == 0) return node;
//            return findParent(node.left, key);
//        }
//        return null;
//    }
//    private Node<Key, Value> findGrandparent(Node<Key, Value> node, Key key){
//        return findParent(node, findParent(node, key).key);
//    }
//    private Node<Key, Value> findUncle(Node<Key, Value> node, Key key){
//        Node<Key, Value> parent = findParent(node, key);
//        if (parent != null){
//            Node<Key, Value> grandparent = findGrandparent(node, key);
//            if (grandparent.right == parent) return grandparent.left;
//            if (grandparent.left == parent) return grandparent.right;
//        }
//        return null;
//    }
//
//
//    @Override
//    public void put(Key key, Value value) {
//        root = put(root, key, value);
//        root.color = false;  // Ensure the root is always black
//    }
//
//    // Helper method for insertion
//    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value) {
//        if (node == null) {
//            // If the node is null, create a new red node
//            return new Node<>(key, value, RED);
//        }
//
//        int comp = comparator.compare(key, node.key);
//        if (comp < 0) {
//            // Recursively insert into the left subtree
//            node.left = put(node.left, key, value);
//        } else if (comp > 0) {
//            // Recursively insert into the right subtree
//            node.right = put(node.right, key, value);
//        } else {
//            // If the key already exists, update the value
//            node.value = value;
//        }
//
//        // Perform necessary rotations and color adjustments
//        if (isRed(node.right) && !isRed(node.left)) {
//            node = rotateLeft(node);
//        }
//        if (isRed(node.left) && isRed(node.left.left)) {
//            node = rotateRight(node);
//        }
//        if (isRed(node.left) && isRed(node.right)) {
//            flipColors(node);
//        }
//
//        return node;
//    }
//
//    // Helper method to check if a node is red
//    private boolean isRed(Node<Key, Value> node) {
//        if (node == null) {
//            return false;
//        }
//        return node.color;
//    }
//
//    private Node<Key, Value> rotateRight(Node<Key, Value> node){
//        if (node.left != null) {
//            Node<Key, Value> result = node.left;
//            node.left = result.right;
//            result.right = node;
//            result.color = node.color;
//            node.color = RED;
//            return result;
//        } else return null; // QUE PASA SI NO ES POSIBLE ROTAR?????
//    }
//    private Node<Key, Value> rotateLeft(Node<Key, Value> node) {
//        if (node.right != null) {
//            Node<Key, Value> result = node.right;
//            node.right = result.left;
//            result.left = node;
//            result.color = node.color;
//            node.color = RED;
//            return result;
//        } else return null;
//    }
//
//    private void flipColors(Node<Key, Value> node) {
//        node.color = !node.color;
//        node.left.color = !node.left.color;
//        node.right.color = !node.right.color;
//    }
//
//
//    @Override
//    public void remove(@NotNull Key key) {
//        throw new RuntimeException();
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
//        private Node<Key, Value> head;
//        ArrayQueue<Node<Key, Value>> nodes = new ArrayQueue<>();
//
//        public levelOrderIterator(){
//            head = root;
//            if (head != null) {
//                nodes.enqueue(head);
//            }
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
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RedBlackBinarySearchTree<K,V> implements TreeMap<K, V>{
    private Comparator<K> comparator;
    private int size;
    private Node<K,V> head;

    //constructor
    public RedBlackBinarySearchTree(Comparator<K> compa){
        this.comparator = compa;
        this.size = 0;
        this.head = null;
    }

    //variables estáticas RED y BLACK
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private boolean isRed(Node<K,V> node){
        if (node == null){return false;} //si no hay nodo, no es rojo
        return node.color == RED;
    }

    //switching from having the smaller of the two keys at the root to
    //having the larger of the two keys at the root.
    private Node rotateLeft(Node<K,V> node){
        Node<K,V> x = node.right;
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private Node rotateRight(Node<K,V> node){
        Node<K,V> x = node.left;
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }


    //in addition to flipping the colors of the children from red to
    //black, we also flip the color of the parent from black to red
    private void flipColors(Node<K,V> node){
        //flipping colors to split a 4-node
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    @Override
    public int size() {
        return size;
    }

    private Node<K, V> find(Node<K, V> node, K key){
        ///Esta función buscará desde un nodo, el nodo q tenga la key q le pasé.
        if (node == null) {return null; }

        int compa = comparator.compare(key, node.key);

        if (compa > 0) { //
            return find(node.right, key);
        }
        else if(compa < 0) {
            return find(node.left, key);
        }
        else {
            return node;
        }
    }

    @Override
    public boolean contains(@NotNull K key) {
        //True if map contains key.
        return find(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        final Node<K, V> node = find(head, key);
        if (node != null){
            return node.value; }
        else {
            return null;
        }
    }

    @Override
    public void put(@NotNull K key, V value) {
        head = put(head, key, value);
        head.color = BLACK;
    }

    private Node<K,V> put(Node<K,V> node, K key, V value){
        if (node == null){
            size++;
            return new Node<>(key, value, RED);
        }
        //busco el nodo q tiene la key donde quiero poner el value.
        int compa = comparator.compare((K) node.key, key);
        if (compa > 0) {
            node.left = put(node.left, key, value);
        } else if (compa < 0) {
            node.right = put(node.right, key, value);
        }
        else {
            //una vez q encuentra la key, le pone el valor a ese nodo.
            node.value = value;
        }


        if (isRed(node.right) && !isRed(node.left)) {node = rotateLeft(node);}
        //inserting into a 3 node a smaller key
        if (isRed(node.left) && isRed(node.left.left)){node = rotateRight(node);}

        if (isRed(node.left) && isRed(node.right)){flipColors(node);}

        return node;
    }

    @Override
    public void remove(@NotNull K k) {
        throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Iterator<K> inOrder() {
        LinkedListQueue<K> keys = new LinkedListQueue<>(); //armo una queue de keys
        inOrder(head, keys); //corro la función recursiva inORder
        return keys.iterator();
    }

    private void inOrder(Node<K,V> node, LinkedListQueue<K> keys){
        if (node == null){return;} //condición de corte, sino el loop nunca terminará.

        inOrder(node.left, keys);
        keys.enqueue(node.key);
        inOrder(node.right, keys);

    }

    @Override
    public Iterator<K> postOrder() {
        LinkedListQueue<K> keys_queue = new LinkedListQueue<>();
        postOrder(head, keys_queue);
        return keys_queue.iterator();
    }

    private void postOrder(Node<K,V> node, LinkedListQueue<K> keys_queue){
        if (node == null) {return;}

        postOrder(node.left, keys_queue);
        postOrder(node.right, keys_queue);
        keys_queue.enqueue(node.key);
    }

    @Override
    public Iterator<K> preOrder() {
        LinkedListQueue<K> keys_queue = new LinkedListQueue<>();
        preOrder(head, keys_queue);
        return keys_queue.iterator();
    }

    private void preOrder(Node<K,V> node, LinkedListQueue<K> queue){
        if (node == null) {
            return; //si el nodo es nulo, q siga con el codigo y se lo saltee. ya q no quiero hacer nada si es nulo
        }
        queue.enqueue(node.key);
        preOrder(node.left, queue);
        preOrder(node.right, queue);
    }

    @Override
    public Iterator<K> levelOrder() {
        return new RedBlackBinarySearchTree.levelOrderIterator();
    }

    private class levelOrderIterator implements Iterator<K> {
        LinkedListQueue<Node<K,V>> Nqueue = new LinkedListQueue<>();
        Node<K,V> node = head;

        public levelOrderIterator(){
            if (node != null) {
                Nqueue.enqueue(node); //constructor, y enqueueo el nodo head
            }
        }

        @Override
        public boolean hasNext() {
            return (!Nqueue.isEmpty());
        }

        @Override
        public K next() {
            Node<K,V> next = Nqueue.dequeue();
            if (next.left != null){Nqueue.enqueue(next.left);}
            if (next.right != null){Nqueue.enqueue(next.right);}
            return next.key;
        }
    }

    @Override
    public void removeMin() {
        throw new NoSuchElementException();
    }

    @Override
    public void removeMax() {
        throw new NoSuchElementException();
    }

    private Node<K,V> first(Node<K,V> node) {
        if (node == null){return null;}

        if (node.left != null) {
            return first(node.left);
        }
        else {
            return node;
        }

    }

    @Override
    public K min() {
        if (isEmpty()) {throw new NoSuchElementException();}
        return first(head).key;
    }


    private Node<K,V> last(Node<K,V> node) {
        if (node == null){return null;}

        if (node.right != null){
            return last(node.right);
        }
        else {
            return node;
        }
    }

    @Override
    public K max() {
        if (isEmpty()) {throw new NoSuchElementException(); }

        return last(head).key;
    }
}