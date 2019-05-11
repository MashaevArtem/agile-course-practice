
package ru.unn.agile.RadixSorter.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public final class RadixSorter {

    private RadixSorter() {
    }

    public static final int DEFAULT_RADIX = 10;

    public static void sort(final int[] arr) {
        sort(arr, DEFAULT_RADIX);
    }

    public static void sort(final int[] arr, final int radix) {
        int maxDigits = 1 + (int) (Math.log(max(arr)) / Math.log(radix));
        int div = 1;
        for (int pos = 0; pos < maxDigits; ++pos) {
            List<List<Integer>> buckets = splitToBuckets(arr, div, radix);
            flattenBuckets(arr, buckets);
            div *= radix;
        }
        List<List<Integer>> buckets = splitBySign(arr);
        flattenBuckets(arr, buckets);
    }

private static List<List<Integer>> splitToBuckets(final int[] arr, final int div, final int radix) {
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < radix; ++i) {
            buckets.add(new LinkedList<>());
        }
        for (int num : arr) {
            int bucketIndex = Math.abs(num) / div % radix;
            buckets.get(bucketIndex).add(num);
        }
        return buckets;
    }

    private static List<List<Integer>> splitBySign(final int[] arr) {
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

 private static void flattenBuckets(final int[] arr, final List<? extends List<Integer>> buckets) {
        int i = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[i++] = num;
            }
        }
    }

    private static int max(final int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}
