package util;

import java.util.Arrays;

/**
 * @program: LeetCode
 * @description: 堆排序
 * @author: wd
 * @create: 2020-07-18 16:26
 **/

public class HeapSort {
    private int[] heap;
    private int high;

    public HeapSort(int[] heap) {
        this.heap = heap;
        high = heap.length;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2, 4, 6, 8, 7};
        HeapSort heapSort = new HeapSort(nums);
        heapSort.heapSort();
        System.out.println(Arrays.toString(nums));
    }

    private void heapSort() {
        createHeap();
        for (int i = high; i > 0; i--) {
            Swap.swap(heap, 0, i - 1);
            downAdjust(0, i - 1);
        }
    }

    void createHeap() {
        for (int i = high / 2 - 1; i >= 0; i--) {
            downAdjust(i, high);
        }
    }

    private void downAdjust(int low, int high) {
        int i = low, j = 2 * i;
        while (j < high) {
            if ((j + 1) < high && heap[j] < heap[j + 1]) {
                j = j + 1;
            }
            if (heap[i] < heap[j]) {
                Swap.swap(heap, i, j);
                i = j;
                j = 2 * i;
            } else {
                break;
            }
        }

    }

    private int deleteTop() {
        int temp = heap[0];
        heap[0] = heap[--high];
        downAdjust(0, high);
        return temp;
    }

    private void upAdjust(int low, int high) {
        int i = high - 1, j = i / 2;
        while (j >= low) {
            if (heap[i] > heap[j]) {
                Swap.swap(heap, i, j);
                i = j;
                j = i / 2;
            } else {
                break;
            }
        }
    }

    private void add(int x) {
        heap[high++] = x;
        upAdjust(0, high);
    }
}
