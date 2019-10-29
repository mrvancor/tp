package lw.lab_2;

public class RadixSort {
    public static void main(String[] args) {
        int[] array = {58, 34, 28, 1, 94, 105, 2, 7, 8, 456,
                425, 22, 54, 12, 88, 73, 100, 15, 2, 147,
                89, 15, 3, 96, 451, 0, 33, 75, 53, 19};
        show(array);
        System.out.println("After sorting: ");
        sort(array, 10);
    }

    public static void sort(int[] array, int radix) {
        if (array.length == 0) {
            return;
        }

        int minValue = array[0];
        int maxValue = array[0];
        long time = System.nanoTime();
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            } else if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }

        int exponent = 1;
        while ((maxValue - minValue) / exponent >= 1) {
            RadixSort.countingSortByDigit(array, radix, exponent, minValue);
            exponent *= radix;
        }
        time = System.nanoTime() - time;
        show(array);
        System.out.println("Operating time: " + time + " ns.");
    }

    private static void countingSortByDigit(int[] array, int radix, int exponent, int minValue) {
        int bucketIndex;
        int[] buckets = new int[radix];
        int[] output = new int[array.length];

        for (int i = 0; i < radix; i++) {
            buckets[i] = 0;
        }

        for (int i = 0; i < array.length; i++) {
            bucketIndex = (int) (((array[i] - minValue) / exponent) % radix);
            buckets[bucketIndex]++;
        }

        for (int i = 1; i < radix; i++) {
            buckets[i] += buckets[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            bucketIndex = (int) (((array[i] - minValue) / exponent) % radix);
            output[--buckets[bucketIndex]] = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
    }

    private static void show(int[] array) {
        for (int item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
