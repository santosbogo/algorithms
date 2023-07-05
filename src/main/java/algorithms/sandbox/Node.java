package algorithms.sandbox;

public class Node<K, V> {
        K key;
        V value;
        Node<K, V> right;
        Node<K, V> left;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.right = this.left = null;
        }
    }