import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RadixSort {

    public static final int DEFAULT_RADIX = 10;

    public static void sort(int[] arr) {
        sort(arr, DEFAULT_RADIX);
    }

    public static void sort(int[] arr, int radix) {
        int maxDigits = 1 + (int) (Math.log(max(arr)) / Math.log(radix));
        int divisor = 1;
        for (int pos = 0; pos < maxDigits; ++pos) {
            List<List<Integer>> buckets = splitToBuckets(arr, divisor, radix);
            flattenBuckets(arr, buckets);
            divisor *= radix;
        }
        List<List<Integer>> buckets = splitBySign(arr);
        flattenBuckets(arr, buckets);
    }

    private static List<List<Integer>> splitToBuckets(int[] arr, int divisor, int radix) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < radix; ++i) {
            buckets.add(new LinkedList<>());
        }
        for (int num : arr) {
            int bucketIndex = Math.abs(num) / divisor % radix;
            buckets.get(bucketIndex).add(num);
        }
        return buckets;
    }

    private static List<List<Integer>> splitBySign(int[] arr) {
        List<Integer> positive = new LinkedList<>();
        List<Integer> negative = new LinkedList<>();
        for (int num : arr) {
            if (num >= 0) {
                positive.add(num);
            } else {
                negative.add(0, num);
            }
        }
        return Arrays.asList(negative, positive);
    }

    private static void flattenBuckets(int[] arr, List<? extends List<Integer>> buckets) {
        int i = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[i++] = num;
            }
        }
    }

    private static int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}