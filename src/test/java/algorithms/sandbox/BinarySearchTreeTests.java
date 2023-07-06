//package algorithms.sandbox;
//
//import org.junit.Test;
//
//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertThrows;
//
//public class BinarySearchTreeTests {
//    Comparator<Integer> comparator = Comparator.naturalOrder();
//    BinarySearchTree<Integer, Integer> T = new BinarySearchTree<>(comparator);
//    @Test
//    public void sizeTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        T.put(3, 3);
//        assertThat(T.size()).isEqualTo(3);
//    }
//
//    @Test
//    public void containsTest() {
//        T.put(1, 1);
//        assertThat(T.contains(1)).isEqualTo(true);
//        assertThat(T.contains(0)).isEqualTo(false);
//    }
//
//    @Test
//    public void getTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        assertThat(T.get(1)).isEqualTo(1);
//        assertThat(T.get(2)).isEqualTo(2);
//    }
//
//    @Test
//    public void getNullTest() {
//        assertThrows(NoSuchElementException.class, () ->{
//            T.get(1);
//        });
//    }
//
//    @Test
//    public void putTest() {
//        T.put(1, 1);
//        T.put(1, 2);
//        assertThat(T.get(1)).isEqualTo(2);
//    }
//
//    @Test
//    public void removeRootTest() {
//        T.put(1, 1);
//        T.remove(1);
//        assertThat(T.size()).isEqualTo(0);
//    }
//
//    @Test
//    public void removeTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        T.remove(1);
//        assertThat(T.size()).isEqualTo(1);
//    }
//
//    @Test
//    public void clearTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        T.clear();
//        assertThat(T.size()).isEqualTo(0);
//    }
//
//    @Test
//    public void minTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        T.put(3, 3);
//        assertThat(T.min()).isEqualTo(1);
//    }
//
//    @Test
//    public void maxTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        T.put(3, 3);
//        assertThat(T.max()).isEqualTo(3);
//    }
//
//    @Test
//    public void removeMaxTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        T.put(3, 3);
//        T.removeMax();
//        assertThat(T.max()).isEqualTo(2);
//    }
//
//    @Test
//    public void removeMinTest() {
//        T.put(1, 1);
//        T.put(2, 2);
//        T.put(3, 3);
//        T.removeMin();
//        assertThat(T.min()).isEqualTo(2);
//    }
//
//    @Test
//    public void inOrderTest() {
//        T.put(2, 2);
//        T.put(1, 1);
//        T.put(3, 3);
//        int[] array = {1, 2, 3};
//        int var = 0;
//        for (Iterator<Integer> a = T.inOrder(); a.hasNext(); ) {
//            int i = a.next();
//            assertThat(i).isEqualTo(array[var]);
//            var++;
//        }
//    }
//
//    @Test
//    public void postOrderTest() {
//        T.put(2, 2);
//        T.put(1, 1);
//        T.put(3, 3);
//        int[] array = {1, 3, 2};
//        int var = 0;
//        for (Iterator<Integer> a = T.postOrder(); a.hasNext(); ) {
//            int i = a.next();
//            assertThat(i).isEqualTo(array[var]);
//            var++;
//        }
//    }
//
//    @Test
//    public void preOrderTest() {
//        T.put(2, 2);
//        T.put(1, 1);
//        T.put(3, 3);
//        int[] array = {2, 1, 3};
//        int var = 0;
//        for (Iterator<Integer> a = T.preOrder(); a.hasNext(); ) {
//            int i = a.next();
//            assertThat(i).isEqualTo(array[var]);
//            var++;
//        }
//    }
//
//    @Test
//    public void levelOrderTest() {
//        T.put(3, 3);
//        T.put(2, 2);
//        T.put(4, 4);
//        int[] array = {3, 2, 4};
//        int var = 0;
//        for (Iterator<Integer> a = T.levelOrder(); a.hasNext();) {
//            int i = a.next();
//            assertThat(i).isEqualTo(array[var]);
//            var++;
//        }
//    }
//    @Test
//    public void levelOrderEmptyTest() {
//        for (Iterator<Integer> a = T.levelOrder(); a.hasNext();) {
//            int i = a.next();
//            assertThat(i).isEqualTo(null);
//        }
//    }
//
//    @Test
//    public void MapsizeTest() {
//        assertThat(T.size()).isEqualTo(0);
//    }
//
//    @Test
//    public void clearTest2() {
//        T.put(1,1);
//        T.put(2, 20);
//        T.put(80, 600);
//        T.put(90, 500);
//        T.clear();
//        assertThat(T.size()).isEqualTo(0);
//    }
//
//    @Test
//    public void containsTest2() {
//        T.put(1, 10);
//        T.put(2, 20);
//        assertThat(T.contains(1)).isEqualTo(true);
//    }
//
//    @Test
//    public void getTest2() {
//        T.put(1, 10);
//        T.put(2, 20);
//        assertThat(T.get(1)).isEqualTo(10);
//    }
//
//    @Test
//    public void putTest2() {
//        T.put(1, 10);
//        T.put(1, 20);
//        assertThat(T.get(1)).isEqualTo(20);
//    }
//
//    @Test
//    public void removeTest2() {
//        T.put(1, 10);
//        T.put(2, 20);
//        T.remove(2);
//        assertThat(T.size()).isEqualTo(1);
//    }
//
//    @Test
//    public void removeTest3() {
//        assertThrows(NoSuchElementException.class, () ->{
//            T.remove(8);
//        });
//    }
//
//    @Test
//    public void removeTest4() {
//        T.put(6, 10);
//        T.put(4, 20);
//        T.put(5, 10);
//        T.put(3, 20);
//        T.put(8, 10);
//        T.put(7, 20);
//        T.put(9, 90);
//        T.put(2, 20);
//
//        T.remove(8);
//        T.remove(7);
//        T.remove(2);
//        T.remove(5);
//        assertThat(T.size()).isEqualTo(4);
//    }
//
//    @Test
//    public void minTest2() {
//        T.put(6, 10);
//        T.put(4, 20);
//        T.put(5, 10);
//
//        assertThat(T.min()).isEqualTo(4);
//    }
//
//    @Test
//    public void maxTest2() {
//        T.put(6, 10);
//        T.put(4, 20);
//        T.put(5, 10);
//        T.put(80, 7);
//
//        assertThat(T.max()).isEqualTo(80);
//
//    }
//
//    @Test
//    public void removeMinTest2() {
//        T.put(6, 10);
//        T.put(4, 20);
//        T.put(5, 10);
//        T.put(80, 7);
//
//        T.removeMin();
//
//        int m = T.min();
//        assertThat(m).isEqualTo(5);
//
//    }
//
//    @Test
//    public void removeMaxTest2() {
//        T.put(6, 10);
//        T.put(4, 20);
//        T.put(5, 10);
//        T.put(80, 7);
//
//        T.removeMax();
//        int m = T.max();
//        assertThat(m).isEqualTo(6);
//
//    }
//
//    @Test
//    public void joinTreesTest() {
//        T.put(8, 8);
//        T.put(4, 4);
//        T.put(11, 11);
//        T.put(9, 9);
//        T.put(12, 12);
//        T.put(1, 1);
//        T.put(7, 7);
//        BinarySearchTree<Integer, Integer> T2 = new BinarySearchTree<>(comparator);
//        T2.put(6, 6);
//        T2.put(3, 3);
//        T2.put(13, 7);
//        T.joinTree(T2.getRoot());
//        assertThat(T.contains(6)).isEqualTo(true);
//        assertThat(T.contains(3)).isEqualTo(true);
//        assertThat(T.contains(7)).isEqualTo(true);
//        //T.printIntKeys();
//    }
//
////    @Test
////    public void subsetTest() {
////        T.put(50, 5);
////        T.put(25, 3);
////        T.put(75, 7);
////        T.put(15, 2);
////        T.put(35, 4);
////        T.put(30, 40);
////        T.put(70, 2);
////        T.put(80, 4);
////        T.put(79, 40);
////        T.put(90, 2);
////        Integer[] a = {50, 35, 40, 75, 70, 80, 79};
////        int var = 0;
////        for (Integer i : T.subset(3, 4)) {
////            assertThat(i).isEqualTo(a[var]);
////            var++;
////        }
////    }
////    @Test
////    public void subsetTest2() {
////        T.put(50, 5);
////        T.put(25, 3);
////        T.put(75, 7);
////        T.put(15, 2);
////        T.put(35, 4);
////        T.put(30, 40);
////        T.put(70, 2);
////        T.put(80, 4);
////        T.put(79, 40);
////        T.put(90, 2);
////
////        Integer[] a = {5, 3, 2, 4};
////        int var = 0;
////        for (Integer i : T.subset(2, 5)) {
////            assertThat(a[var]).isEqualTo(i);
////            var++;
////        }
////        //assertThat(T.subset(2, 5).size()).isEqualTo(4);
////    }
//
//    @Test
//    public void heightTest() {
//        T.put(50, 5);
//        T.put(25, 6);
//        T.put(60, 7);
//        T.put(24, 8);
//        T.put(30, 5);
//        T.put(26, 6);
//        T.put(35, 7);
//        T.put(40, 8);
//        assertThat(T.height()).isEqualTo(4);
//    }
//    @Test
//    public void LinearHeightTest() {
//        T.put(50, 5);
//        T.put(60, 6);
//        T.put(70, 7);
//        T.put(80, 8);
//        T.put(90, 5);
//
//        assertThat(T.height()).isEqualTo(4);
//    }
//
//    @Test
//    public void avgComparesTest() {
//        T.put(8, 8);
//        T.put(4, 4);
//        T.put(11, 11);
//        T.put(9, 9);
//        T.put(12, 12);
//        T.put(1, 1);
//        T.put(7, 7);
//        assertThat(T.avgCompares()).isEqualTo(((double) 10/7) + 1);
//    }
//
////    @Test
////    public void floorTest() {
////        T.put(6, 6);
////        T.put(4, 4);
////        T.put(8, 8);
////        T.put(2, 2);
////        T.put(5, 5);
////        assertThat(T.floor(5)).isEqualTo(5);
////        assertThat(T.floor(6)).isEqualTo(5);
////        assertThat(T.floor(2)).isEqualTo(2);
////    }
//
//    @Test
//    public void ceilingTest() {
//        T.put(6, 6);
//        T.put(4, 4);
//        T.put(8, 8);
//        T.put(2, 2);
//        T.put(5, 5);
//        assertThat(T.ceiling(5)).isEqualTo(5);
//        assertThat(T.ceiling(6)).isEqualTo(8);
//        assertThat(T.ceiling(4)).isEqualTo(5);
//    }
//
////    @Test
////    public void selectTest() {
////        T.put(6, 6);
////        T.put(4, 4);
////        T.put(8, 8);
////        T.put(2, 2);
////        T.put(5, 5);
////        assertThat(8).isEqualTo((T.select(4)).key);
////        assertThat(4).isEqualTo((T.select(1)).key);
////    }
////
////    @Test
////    public void rankTest() {
////        T.put(6, 6);
////        T.put(4, 4);
////        T.put(8, 8);
////        T.put(2, 2);
////        T.put(5, 5);
////        assertThat(3).isEqualTo(T.rank(6));
////        assertThat(4).isEqualTo(T.rank(8));
////        assertThat(2).isEqualTo(T.rank(5));
////    }
////
////    @Test
////    public void InternalPathLengthTest() {
////        T.put(8, 8);
////        T.put(4, 4);
////        T.put(11, 11);
////        T.put(9, 9);
////        T.put(12, 12);
////        T.put(1, 1);
////        T.put(7, 7);
////        assertThat(10).isEqualTo(T.InternalPathLength(8));
////    }
////
////    @Test
////    public void ExternalPathLengthTest() {
////        T.put(8, 8);
////        T.put(4, 4);
////        T.put(11, 11);
////        T.put(9, 9);
////        T.put(12, 12);
////        T.put(1, 1);
////        T.put(7, 7);
////        assertThat(24).isEqualTo(T.ExternalPathLength());
////    }
//
//    @Test
//    public void reversedTest() {
//        T.put(2, 8);
//        T.put(1, 4);
//        T.put(6, 11);
//        T.put(7, 9);
//        T.put(4, 12);
//        T.put(3, 1);
//        T.put(5, 2);
//
//        int[] array = {7, 6, 5, 4, 3, 2, 1};
//        T.reversed();
//        int var = 0;
//        for (Iterator<Integer> a = T.inOrder(); a.hasNext(); ) {
//            int i = a.next();
//            assertThat(i).isEqualTo(array[var]);
//            var++;
//        }
//    }
//
//}