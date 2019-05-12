import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public abstract class RadixSorterTest {

    abstract void sort(int[] arr);

    private int[] newRandomArray(final int num) {
        Random random = new Random(0);
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    private void sortAndVerify(final int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        sort(arr);
        assertArrayEquals(copy, arr);
    }

    @Test
    public void testempty() {
        sortAndVerify(new int[0]);
    }

    @Test
    public void test1() {
        sortAndVerify(new int[]{1});
    }

    @Test
    public void test12() {
        sortAndVerify(new int[]{1, 2});
    }

    @Test
    public void test21() {
        sortAndVerify(new int[]{2, 1});
    }

    @Test
    public void test123() {
        sortAndVerify(new int[]{1, 2, 3});
    }

    @Test
    public void test321() {
        sortAndVerify(new int[]{3, 2, 1});
    }

    @Test
    public void testrandom10() {
        sortAndVerify(newRandomArray(10));
    }

    @Test
    public void testrandom100() {
        sortAndVerify(newRandomArray(100));
    }

    @Test
    public void testrandom1000() {
        sortAndVerify(newRandomArray(1000));
    }

}

 //class RadixSortTest extends RadixSorterTest {
 //   @Override
 //   void sort(final int[] arr) {
 //       RadixSorter.sort(arr);
 //   }
 //}
