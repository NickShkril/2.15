package pro.sky;

import java.util.Arrays;
import java.util.Random;

import static pro.sky.SortUtils.*;

public class SortTest {
    private static final int ARRAY_CAPACITY = 10_000;
    private static final int ARRAY_MAX_RANGE_VALUE = 100_000;


    public static void main(String[] args) {
        Random random = new Random();
        Integer[] arr1 = new Integer[ARRAY_CAPACITY];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt(ARRAY_MAX_RANGE_VALUE);
        }

        Integer[] arr2 = Arrays.copyOf(arr1, ARRAY_CAPACITY);
        Integer[] arr3 = Arrays.copyOf(arr1, ARRAY_CAPACITY);
        Integer[] arr4 = Arrays.copyOf(arr1, ARRAY_CAPACITY);
        Integer[] arr5 = Arrays.copyOf(arr1, ARRAY_CAPACITY);


        long start = System.currentTimeMillis();
        sortBubble(arr1);
        System.out.println("Bubble sort: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sortSelection(arr2);
        System.out.println("Selection sort: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sortInsertion(arr3);
        System.out.println("Insert sort: " + (System.currentTimeMillis() - start));
       // Insert is faster then others

    }
}