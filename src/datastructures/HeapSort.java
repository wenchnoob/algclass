package datastructures;

import java.util.Comparator;
import java.util.Random;

public class HeapSort {

    public static void main(String[] args) {

        int[] arr = new int[] {2, 5, 6, 3, 1, 2, 12, 6, 7};
        Heap<Integer> heap = new Heap<Integer>(Comparator.reverseOrder());
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        MinHeap<Integer> minHeap = new MinHeap<>();

        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            heap.insert(r.nextInt(100));
        }

        while (!heap.isEmpty()) {
            System.out.printf("%d ", heap.removeHead());
        }

        System.out.println();
        System.out.println(heap.heapsize());

    }

}
