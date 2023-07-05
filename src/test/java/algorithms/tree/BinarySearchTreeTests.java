package algorithms.tree;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class BinarySearchTreeTests {
    Comparator<Integer> comparator = Comparator.naturalOrder();
    BinarySearchTree<Integer, Integer> T = new BinarySearchTree<>(comparator);
    @Test
    public void sizeTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.put(3, 3);
        assertThat(T.size()).isEqualTo(3);
    }

    @Test
    public void containsTest() {
        T.put(1, 1);
        assertThat(T.contains(1)).isEqualTo(true);
        assertThat(T.contains(0)).isEqualTo(false);
    }

    @Test
    public void getTest() {
        T.put(1, 1);
        T.put(2, 2);
        assertThat(T.get(1)).isEqualTo(1);
        assertThat(T.get(2)).isEqualTo(2);
    }


    @Test
    public void putTest() {
        T.put(1, 1);
        T.put(1, 2);
        assertThat(T.get(1)).isEqualTo(2);
    }

    @Test
    public void removeRootTest() {
        T.put(1, 1);
        T.remove(1);
        assertThat(T.size()).isEqualTo(0);
    }

    @Test
    public void removeTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.remove(1);
        assertThat(T.size()).isEqualTo(1);
    }

    @Test
    public void clearTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.clear();
        assertThat(T.size()).isEqualTo(0);
    }

    @Test
    public void minTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.put(3, 3);
        assertThat(T.min()).isEqualTo(1);
    }

    @Test
    public void maxTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.put(3, 3);
        assertThat(T.max()).isEqualTo(3);
    }

    @Test
    public void removeMaxTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.put(3, 3);
        T.removeMax();
        assertThat(T.max()).isEqualTo(2);
    }

    @Test
    public void removeMinTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.put(3, 3);
        T.removeMin();
        assertThat(T.min()).isEqualTo(2);
    }

    @Test
    public void inOrderTest() {
        T.put(2, 2);
        T.put(1, 1);
        T.put(3, 3);
        int[] array = {1, 2, 3};
        int var = 0;
        for (Iterator<Integer> a = T.inOrder(); a.hasNext(); ) {
            int i = a.next();
            assertThat(i).isEqualTo(array[var]);
            var++;
        }
    }

    @Test
    public void postOrderTest() {
        T.put(2, 2);
        T.put(1, 1);
        T.put(3, 3);
        int[] array = {1, 3, 2};
        int var = 0;
        for (Iterator<Integer> a = T.postOrder(); a.hasNext(); ) {
            int i = a.next();
            assertThat(i).isEqualTo(array[var]);
            var++;
        }
    }

    @Test
    public void preOrderTest() {
        T.put(2, 2);
        T.put(1, 1);
        T.put(3, 3);
        int[] array = {2, 1, 3};
        int var = 0;
        for (Iterator<Integer> a = T.preOrder(); a.hasNext(); ) {
            int i = a.next();
            assertThat(i).isEqualTo(array[var]);
            var++;
        }
    }

    @Test
    public void levelOrderTest() {
        T.put(3, 3);
        T.put(2, 2);
        T.put(4, 4);
        int[] array = {3, 2, 4};
        int var = 0;
        for (Iterator<Integer> a = T.levelOrder(); a.hasNext();) {
            int i = a.next();
            assertThat(i).isEqualTo(array[var]);
            var++;
        }
    }
    @Test
    public void levelOrderEmptyTest() {
        for (Iterator<Integer> a = T.levelOrder(); a.hasNext();) {
            int i = a.next();
            assertThat(i).isEqualTo(null);
        }
    }

//    @Test
//    public void subsetTest() {
//        T.put(5, 5);
//        T.put(3, 3);
//        T.put(7, 7);
//        T.put(2, 2);
//        T.put(4, 4);
//        Integer[] a = {3, 4};
//        int var = 0;
//        for (Integer i : T.subset(3, 4)) {
//            assertThat(i).isEqualTo(a[var]);
//            var++;
//        }
//    }
//    @Test
//    public void subsetTest2() {
//        T.put(5, 5);
//        T.put(3, 3);
//        T.put(7, 7);
//        T.put(2, 2);
//        T.put(4, 4);
//        Integer[] a = {5, 3, 2, 4};
//        int var = 0;
//        for (Integer i : T.subset(2, 5)) {
//            assertThat(i).isEqualTo(a[var]);
//            var++;
//        }
//    }
    //creo comparator de integers
    Comparator<Integer> compa = Comparator.naturalOrder();
    //creo un mapa implementado con binary search tree, y le paso el compa.
    BinarySearchTree<Integer, Integer> mapita = new BinarySearchTree<Integer, Integer>(compa);

    @Test
    public void MapsizeTest() {
        assertThat(mapita.size()).isEqualTo(0);
    }

    @Test
    public void clearTest2() {
        mapita.put(1,1);
        mapita.put(2, 20);
        mapita.put(80, 600);
        mapita.put(90, 500);
        mapita.clear();
        assertThat(mapita.size()).isEqualTo(0);
    }

    @Test
    public void containsTest2() {
        mapita.put(1, 10);
        mapita.put(2, 20);
        assertThat(mapita.contains(1)).isEqualTo(true);
    }

    @Test
    public void getTest2() {
        mapita.put(1, 10);
        mapita.put(2, 20);
        assertThat(mapita.get(1)).isEqualTo(10);
    }

    @Test
    public void putTest2() {
        mapita.put(1, 10);
        mapita.put(1, 20);
        assertThat(mapita.get(1)).isEqualTo(20);
    }

    @Test
    public void removeTest2() {
        mapita.put(1, 10);
        mapita.put(2, 20);
        mapita.remove(2);
        assertThat(mapita.size()).isEqualTo(1);
    }

    @Test
    public void removeTest3() {
        assertThrows(NoSuchElementException.class, () ->{
            mapita.remove(8);
        });
    }

    @Test
    public void removeTest4() {
        mapita.put(6, 10);
        mapita.put(4, 20);
        mapita.put(5, 10);
        mapita.put(3, 20);
        mapita.put(8, 10);
        mapita.put(7, 20);
        mapita.put(9, 90);
        mapita.put(2, 20);

        mapita.remove(8);
        mapita.remove(7);
        mapita.remove(2);
        mapita.remove(5);
        assertThat(mapita.size()).isEqualTo(4);
    }

    @Test
    public void minTest2() {
        mapita.put(6, 10);
        mapita.put(4, 20);
        mapita.put(5, 10);

        assertThat(mapita.min()).isEqualTo(4);
    }

    @Test
    public void maxTest2() {
        mapita.put(6, 10);
        mapita.put(4, 20);
        mapita.put(5, 10);
        mapita.put(80, 7);

        assertThat(mapita.max()).isEqualTo(80);

    }

    @Test
    public void removeMinTest2() {
        mapita.put(6, 10);
        mapita.put(4, 20);
        mapita.put(5, 10);
        mapita.put(80, 7);

        mapita.removeMin();

        int m = mapita.min();
        assertThat(m).isEqualTo(5);

    }

    @Test
    public void removeMaxTest2() {
        mapita.put(6, 10);
        mapita.put(4, 20);
        mapita.put(5, 10);
        mapita.put(80, 7);

        mapita.removeMax();
        int m = mapita.max();
        assertThat(m).isEqualTo(6);

    }

}