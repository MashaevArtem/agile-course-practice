package ru.unn.agile.RadixSorter.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class RadixSorter {

    public static final double DEFAULT_RADIX = 10;

    public static void sort(double[] arr) {
        sort(arr, DEFAULT_RADIX);
    }

    public static void sort(double[] arr, double radix) {
        int maxDigits = 1 + (int) (Math.log(max(arr)) / Math.log(radix));
        int divisor = 1;
        for (int pos = 0; pos < maxDigits; ++pos) {
            List<List<Double>> buckets = splitToBuckets(arr, divisor, radix);
            flattenBuckets(arr, buckets);
            divisor *= radix;
        }
        List<List<Double>> buckets = splitBySign(arr);
        flattenBuckets(arr, buckets);
    }

    private static List<List<Double>> splitToBuckets(double[] arr, int divisor, double radix) {
        List<List<Double>> buckets = new ArrayList<>();
        for (int i = 0; i < radix; ++i) {
            buckets.add(new LinkedList<>());
        }
        for (int num : arr) {
            int bucketIndex = Math.abs(num) / divisor % radix;
            buckets.get(bucketIndex).add(num);
        }
        return buckets;
    }

    private static List<List<Double>> splitBySign(double[] arr) {
        List<Double> positive = new LinkedList<>();
        List<Double> negative = new LinkedList<>();
        for (int num : arr) {
            if (num >= 0) {
                positive.add(num);
            } else {
                negative.add(0, num);
            }
        }
        return Arrays.asList(negative, positive);
    }

    private static void flattenBuckets(double[] arr, List<? extends List<Double>> buckets) {
        int i = 0;
        for (List<Double> bucket : buckets) {
            for (int num : bucket) {
                arr[i++] = num;
            }
        }
    }

    private static double max(double[] arr) {
        double max = Double.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}