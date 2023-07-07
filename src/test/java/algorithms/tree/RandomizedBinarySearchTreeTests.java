package algorithms.tree;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

public class RandomizedBinarySearchTreeTests {
    Comparator<Integer> comparator = Comparator.naturalOrder();
    RandomizedBinarySearchTree<Integer, Integer> T = new RandomizedBinarySearchTree<>(comparator);
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
    public void getNullTest() {
        assertThat(T.get(1)).isEqualTo(null);
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
        //assertThat(T.size()).isEqualTo(0);
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
        assertThat(T.levelOrder().hasNext()).isFalse();
        //assertThat(T.size()).isEqualTo(0);
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
    public void removeMaxEmptyTest() {
        assertThrows(NoSuchElementException.class, () ->{
            T.removeMax();
        });

    }

    @Test
    public void removeMinEmptyTest() {
        assertThrows(NoSuchElementException.class, () ->{
            T.removeMin();
        });


    }

    @Test
    public void levelOrderEmptyTest() {
        for (Iterator<Integer> a = T.levelOrder(); a.hasNext();) {
            int i = a.next();
            assertThat(i).isEqualTo(null);
        }
    }
//    @Test(expected = NoSuchElementException.class)
//    public void levelOrderEmptyTest() {
//        for (Iterator<Integer> a = T.levelOrder(); a.hasNext(); ) {
//            int i = a.next();
//        }
//    }

    @Test
    public void variationsTest() {
        T.put(1, 1);
        T.put(2, 2);
        T.put(3, 3);
        T.put(4, 4);
        T.put(5, 5);
        T.put(6, 6);
        T.put(7, 7);
        T.put(8, 8);
        T.put(9, 9);
        T.remove(9);
        T.remove(7);
        T.remove(8);
        T.remove(5);
        T.remove(6);
        T.remove(4);
        T.remove(2);
        T.remove(3);
        T.remove(1);
    }
    @Test
    public void variations2Test() {
        T.put(9, 9);
        T.put(8, 8);
        T.put(7, 7);
        T.put(6, 6);
        T.put(5, 5);
        T.put(4, 4);
        T.put(3, 3);
        T.put(2, 2);
        T.put(1, 1);
    }

    @Test
    public void removeEmptyTest() {
        T.put(1, 1);
        T.remove(1);
        assertThrows(NoSuchElementException.class, () ->{
            T.remove(1);
        });
    }

}