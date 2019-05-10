package ru.unn.agile.Radix.model;

import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.*;

public class RadixSorterTest 
{
    @Test
    public void canSortOfArrayWithOneInteger() 
	{
        Integer[] actualArray = {1};
        Integer[] expectedArray = actualArray.clone();
        Arrays.sort(expectedArray);

        RadixSorter.sort(actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void canSortOfSortedArrayWithTwoIntegers() 
	{
        Integer[] actualArray = {1, 2};
        Integer[] expectedArray = actualArray.clone();
        Arrays.sort(expectedArray);

        RadixSorter.sort(actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void canSortOfNonSortedArrayWithTwoIntegers() 
	{
        Integer[] actualArray = {4, 3};
        Integer[] expectedArray = actualArray.clone();
        Arrays.sort(expectedArray);

        RadixSorter.sort(actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void canSortOfNonSortedArrayWithThreeIntegers() 
	{
        Integer[] actualArray = {4, 2, 3};
        Integer[] expectedArray = actualArray.clone();
        Arrays.sort(expectedArray);

        RadixSorter.sort(actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void canSortOfSortedArrayWithThreeIntegers() 
	{
        Integer[] actualArray = {2, 3, 4};
        Integer[] expectedArray = actualArray.clone();
        Arrays.sort(expectedArray);

        RadixSorter.sort(actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void canSortOfNonSortedBigArrayOfIntegers() 
	{
        Integer[] actualArray = {3, 6, 5, -3, 7, 10, 10, -200, 11, 6, 3, 2};
        Integer[] expectedArray = actualArray.clone();
        Arrays.sort(expectedArray);

        RadixSorter.sort(actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSortOfEmptyArrayOfIntegers() 
	{
        Integer[] array = {};

        RadixSorter.sort(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSortOfNullArray() {
        Integer[] actualArray = null;

        RadixSorter.sort(actualArray);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsWhenSortOfArrayWithNullElement() 
	{
        Integer[] actualArray = {0, null, -1};

        RadixSorter.sort(actualArray);
    }

    @Test
    public void canSortOfNonSortedArrayOfStrings() 
	{
        String[] actualArray = {"ab", "bc", "ac"};
        String[] expectedArray = actualArray.clone();
        Arrays.sort(expectedArray);

        RadixSorter.sort(actualArray);

        assertArrayEquals(expectedArray, actualArray);
    }
}
