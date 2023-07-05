package algorithms.sandbox;


import algorithms.stack.ArrayStack;
import algorithms.tree.TreeMap;
import algorithms.queue.ArrayQueue;

import org.jetbrains.annotations.NotNull;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinarySearchTree<Key, Value> implements TreeMap<Key, Value> {

    private Node<Key, Value> root;
    int size;
    Comparator<Key> comparator;

    public BinarySearchTree(Comparator<Key> comparator){
        this.comparator = comparator;
        root = null;
        size = 0;
    }


    public Node<Key, Value> getRoot(){
        return root;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private Node<Key, Value> find (Node<Key, Value> node, Key key){
        if (node == null) return null;

        int comp = comparator.compare(key, node.key);

        if (comp < 0) return find(node.left, key);
        else if(comp > 0) return find(node.right, key);
        else return node;
    }

    @Override
    public boolean contains(@NotNull Key key) {
        return (find(root, key) != null);
    }

    @Override
    public Value get(@NotNull Key key) {
        if (!contains(key)) throw new NoSuchElementException();
        return find(root, key).value;
    }

    @Override
    public void put(@NotNull Key key, Value value) {
        root = put(root, key, value);
    }
    private Node<Key, Value> put(Node<Key, Value> node, Key key, Value value){
        if (node == null){
            size++;
            return new Node<>(key, value);
        }

        int comp = comparator.compare(key, node.key);

        if (comp > 0) node.right = put(node.right, key, value);
        else if (comp < 0) node.left = put(node.left, key, value);
        else node.value = value;

        return node;
    }


    @Override
    public void remove(@NotNull Key key) {
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
        else if (comp < 0) node.left = remove(node.left, key);
        else{ //Found the node to remove

            if (node.right == null) return node.left; //Does not have right child. (if even does not have left child return null)
            else if (node.left == null) return node.right; //Does not have left child.
            else{ //Has right and left children

                Node<Key, Value> lower = min(node.right); //The lower node of its right child node
                node.key = lower.key;
                node.value = lower.value;
                remove(node.right, lower.key); //Remove the lower node that remains in the tree
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
        ArrayQueue<Key> keys = new ArrayQueue<Key>();
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
    private void preOrder(Node<Key, Value> node, ArrayQueue<Key> keys){
        if (node == null) return;
        keys.enqueue(node.key);
        preOrder(node.left, keys);
        preOrder(node.right, keys);

    }

    @Override
    public Iterator<Key> levelOrder() {
        return new LevelOrderIterator();
    }
    public Iterator<Key> levelOrderFromNode(Node<Key, Value> node) {
        return new LevelOrderIterator(node);
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
        public LevelOrderIterator(Node <Key, Value> node) {
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
        remove(min(root).key);
    }

    @Override
    public void removeMax() {
        remove(max(root).key);
    }

    @Override
    public Key min() {
        return min(root).key;
    }
    private Node<Key, Value> min(Node<Key, Value> node){
        if (node.left == null) return node;
        else return min(node.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }
    private Node<Key, Value> max(Node<Key,  Value> node){
        if (node.right == null) return node;
        return max(node.right);
    }


    public void joinTree(Node<Key, Value> newtree){
        root = joinTrees(root, newtree);
    }
    private Node<Key, Value> joinTrees(Node <Key, Value> node, Node <Key, Value> newnode){
        ArrayStack<Node<Key, Value>> nodes = new ArrayStack<>();

        for (Iterator<Key> iter = levelOrderFromNode(newnode); iter.hasNext();) {
            nodes.push(find(newnode, iter.next()));
        }

        while (!nodes.isEmpty()) {
            Node<Key,Value> temp = nodes.pop();
            put(node, temp.key, temp.value);
        }

        return node;
    }

    public void printIntKeys(){
        ArrayQueue<Node<Key, Value>> nodes = new ArrayQueue<>();
        nodes.enqueue(root);
        int i = 0;
        while(i < size()) {
            Node<Key, Value> next = nodes.dequeue();
            System.out.println(next.key);
            if (next.left != null) nodes.enqueue(next.left);
            if (next.right != null) nodes.enqueue(next.right);
            i++;
        }

    } // Ver para imprimir lindo

    public int height(){
        return height(root);
    }
    private int height(Node<Key, Value> node){
        if (node == null) return -1;

        int left = height(node.left);
        int right = height(node.right);

        if (left > right) return left + 1;
        return right + 1;
    }

    public double avgCompares(){
        return avgCompares(root);
    }
    private double avgCompares(Node<Key, Value> node){
        return ((double) internalPathLength(node, 0)/size()) + 1;
    }
    private int internalPathLength(Node<Key, Value> node, int depth){
        if (node == null) return 0;
        int leftPath = internalPathLength(node.left, depth+1);
        int rightPath = internalPathLength(node.right, depth+1);
        return depth + leftPath + rightPath;
    }

    public Key floor(Key key){ //Return the bigger value of the tree but lower or equal than the node
        if (contains(key)) return find(root, key).key;
        return floor(root, key).key;
    }
    private Node<Key, Value> floor(Node<Key, Value> node, Key key){
        int comp = comparator.compare(key, node.key);
        int leftcomp = comparator.compare(key, node.right.key);
        if (comp > 0) return floor(node.right, key);
        if (comp > 0 && leftcomp  < 0) return floor(node.right, key);

        if (comp < 0 && leftcomp > 0) return node;
        return node;
    }

    public Key ceiling(Key key){ //Return the lower key but bigger from
        Node<Key, Value> node = find(root, key);
        if (node.right == null) return node.key;
        return min(node.right).key;
    }

    public ArrayQueue<Key> subset(Key low, Key high){
        ArrayQueue<Key> queue = new ArrayQueue<>();
        subset(root, queue, low, high);
        return queue;
    }

    private void subset(Node<Key, Value> node, ArrayQueue<Key> keys, Key low, Key high){
        if (node == null) return;

        int lowcomp = comparator.compare(low, node.key); //lowcomp <= 0 its affirmative
        int highcomp = comparator.compare(high, node.key); //highcomp >= 0 its affirmative

        if (lowcomp <= 0){
            keys.enqueue(node.key);
            subset(node.left, keys, low, high);
            return;
        }
        if(highcomp >= 0){
            keys.enqueue(node.key);
            subset(node.right, keys, low, high);
        }
    }

//    public void reversed(){
//        comparator = comparator.reversed();
//        root = reversed(root);
//    }
//    private Node<Key, Value> reversed(Node<Key, Value> node){
//        if (node == null) return null;
//        node = put(node, node.key, node.value);
//        node.right = reversed(node.left);
//        node.left = reversed(node.right);
//        return node;
//    }


    public void  reversed() {
        root = reversed(root);
    }
    public Node<Key, Value>  reversed(Node<Key, Value>  node) {
        //base case 1
        if (node == null)
            return null;
        //base case 2
        if ((node.left == null) && (node.right == null)) {
            return node;
        } else {

            Node<Key, Value>  left = reversed(node.left);
            Node<Key, Value>  right = reversed(node.right);
            //swap nodes
            node.right = left;
            node.left = right;
            return node;
        }
    }
}
//
//package algorithms.tree;
//
//
//        import algorithms.queue.ArrayQueue;
//        import algorithms.queue.LinkedListQueue;
//        import org.jetbrains.annotations.NotNull;
//
//        import java.util.Comparator;
//        import java.util.Iterator;
//        import java.util.NoSuchElementException;
//
//public class BinarySearchTree<Key, Value> implements TreeMap<Key, Value>{
//
//    final Comparator<Key> comparator;
//    algorithms.tree.Node<Key, Value> root;
//    private int size;
//
//    public BinarySearchTree(Comparator<Key> comparator) {
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
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public boolean contains(@NotNull Key key) {
//        return get(key) != null;
//    }
//
//    private algorithms.tree.Node<Key, Value> find (algorithms.tree.Node<Key, Value> node, Key key){
//        if (node == null) return null;
//
//        int comp = comparator.compare(key, node.key);   // 0 if key == node.key, + if key < node.key, - if key > node.key

//        if (comp > 0) return find(node.right, key);
//        if (comp < 0) return find(node.left, key);
//        else return node;
//    }
//
//    @Override
//    public Value get(@NotNull Key key) {
//        if (find(root, key) == null) return null;
//        return (find(root, key).value);
//    }
//
//    @Override
//    public void put(@NotNull Key key, Value value) {
//        root = put(root, key, value);
//    }
//    private algorithms.tree.Node<Key, Value> put (algorithms.tree.Node<Key, Value> node, Key key, Value value){
//        if (node == null){
//            size++;
//            return new algorithms.tree.Node<>(key, value);
//        }
//
//        int comp = comparator.compare(key, node.key);
//        if (comp == 0) node.value = value;
//        else if (comp > 0) node.right = put(node.right, key, value);
//        else if(comp < 0) node.left = put(node.left, key, value);
//
//        return node;
//    }
//
//    @Override
//    public void remove(Key key) {
//        if (!contains(key)) throw new NoSuchElementException();
//
//        size --;
//        root = remove(root, key);
//    }
//    private algorithms.tree.Node<Key, Value> remove (algorithms.tree.Node<Key, Value> node, Key key){
//
//        if (node == null) return null;
//
//        int comp = comparator.compare(key, node.key);
//
//        if (comp > 0){
//            node.right = remove(node.right, key);//1
//        }
//        else if (comp < 0) {
//            node.left = remove(node.left, key);//2
//        }
//        else{
//            if (node.left == null && node.right == null) return null; //Leaf node
//            if (node.left == null) return node.right; //Node just have right child
//            if (node.right == null) return node.left; //Node just have left child
//            else{ //Has right and left children
//                algorithms.tree.Node<Key, Value> bigger = find(node.left, max(node.left)); //Bigger node of the left son
//                bigger.right = node.right; //Now put the bigger in the position of the deleted
//                bigger.left = node.left;
//                remove(bigger.left, max(node.left)); // if we dont do this we have 2 nodes with same key
//                return bigger;//2
//            }
//        }
//        return node; //1
//    }
//
//    @Override
//    public void clear() {
//        root = null;
//        size = 0;
//    }
//
//    //1: Process left
//    //2: Process root
//    //3: Process right
//    @Override
//    public Iterator<Key> inOrder() {
//        final ArrayQueue<Key> keys = new ArrayQueue<>();
//        inOrder(root, keys);
//        return keys.iterator();
//    }
//    private void inOrder(algorithms.tree.Node<Key, Value> node, ArrayQueue<Key> keys){
//        if (node == null) return;
//        inOrder(node.left, keys);
//        keys.enqueue(node.key);
//        inOrder(node.right, keys);
//    }
//
//    //1: Process left
//    //2: Process right
//    //3: Process root
//    @Override
//    public Iterator<Key> postOrder() {
//        final ArrayQueue<Key> keys = new ArrayQueue<>();
//        postOrder(root, keys);
//        return keys.iterator();
//    }
//    private void postOrder(algorithms.tree.Node<Key, Value> node, ArrayQueue<Key> keys){
//        if (node == null) return;
//        postOrder(node.left, keys);
//        postOrder(node.right, keys);
//        keys.enqueue(node.key);
//    }
//
//    //1: Process root
//    //2: Process left
//    //3: Process right
//    @Override
//    public Iterator<Key> preOrder() {
//        ArrayQueue<Key> keys = new ArrayQueue<>();
//        preOrder(root, keys);
//        return keys.iterator();
//    }
//    private void preOrder(algorithms.tree.Node<Key,Value> node, ArrayQueue<Key> keys){
//        if (node == null) return;
//        keys.enqueue(node.key);
//        preOrder(node.left, keys);
//        preOrder(node.right, keys);
//    }
//
//    //1: Process root
//    //2: Process next level left to right
//    @Override
//    public Iterator<Key> levelOrder() {
//        return new algorithms.tree.BinarySearchTree.LevelOrderIterator();
//    }
//    private class LevelOrderIterator implements Iterator{
//        ArrayQueue<algorithms.tree.Node<Key, Value>> nodes = new ArrayQueue<>();
//
//        algorithms.tree.Node<Key, Value> head;
//        public LevelOrderIterator(){
//            head = root;
//            if (head != null) {
//                nodes.enqueue(head);
//            }
//            //else {throw new NoSuchElementException();
//        }
//        public LevelOrderIterator(algorithms.tree.Node<Key, Value> node) {
//            head = node;
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
//        public Key next() {
//            algorithms.tree.Node<Key, Value> next = nodes.dequeue();
//            if (next.left != null) nodes.enqueue(next.left);
//            if (next.right != null) nodes.enqueue(next.right);
//            return next.key;
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
//        return min(root);
//    }
//    private Key min (algorithms.tree.Node<Key, Value> node){
//        if (node.left == null) return node.key;
//        return min(node.left);
//    }
//
//    @Override
//    public Key max() {
//        return max(root);
//    }
//    private Key max(algorithms.tree.Node<Key, Value> node){
//        if (node.right == null) return node.key;
//        return max(node.right);
//    }
//}
