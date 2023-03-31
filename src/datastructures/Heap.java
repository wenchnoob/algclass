package datastructures;

import java.util.*;

public class Heap<T extends Comparable<? super T>> {

    private Object[] heap;
    Comparator<T> comparator;
    private int capacity;
    private int size;
    private static final int minCap = 24;


    public Heap() {
        this(minCap, Comparator.naturalOrder());
    }

    public Heap(int capacity) {
        this(capacity, Comparator.naturalOrder());
    }

    public Heap(Comparator<T> comparator) {
        this(minCap, comparator);
    }

    public Heap(int capacity, Comparator<T> comparator) {
        this.heap = new Object[capacity];
        this.comparator = comparator;
        this.capacity = capacity;
        this.size = 0;
    }

    protected void simple_init(List<T> arr) {
        while (capacity < arr.size()) grow();
        System.arraycopy(arr.toArray(), 0, heap, 0, arr.size());
        this.size = arr.size();
        buildheap();
    }

    public void insert(T val) {
        heap[this.size++] = val;
        swim(this.size - 1);
        if (this.size >= 4 * this.capacity / 5) grow();
    }

    protected T removeHead() {
        return remove(0);
    }

    public T remove(int i) {
        if (this.size <= 0) return null;
        T retVal = swap(i, --this.size);
        sink(i);
        if (this.size < this.capacity / 3 && this.size > minCap) shrink();
        return retVal;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    public int heapsize() {
        return this.size;
    }

    public boolean isLeaf(int pos) {
        return (pos >= this.size/2) && (pos < this.size);
    }

    public void buildheap() {
        for (int i=size/2-1; i>=0; i--) sink(i);
    }

    private void sink(int i) {
        if (i >= size) return;

        T cur = (T)this.heap[i];


        int l = 2 * i + 1;
        int r = 2 * i + 2;

        T left = l < size ? (T)this.heap[l] : null;
        T right = r < size ? (T)this.heap[r] : null;

        if (Objects.isNull(left) && Objects.isNull(right)) return;

        if (Objects.isNull(left)) {
            if (comparator.compare(right, cur) < 0) {
                swap(i, r);
                sink(r);
            }
        } else if (Objects.isNull(right)) {
            if (comparator.compare(left, cur) < 0) {
                swap(i, l);
                sink(l);
            }
        } else {
            if (comparator.compare(right, left) < 0) {
                if (comparator.compare(right, cur) < 0) {
                    swap(i, r);
                    sink(r);
                }
            } else {
                if (comparator.compare(left, cur) < 0) {
                    swap(i, l);
                    sink(l);
                }
            }
        }
    }

    private void swim(int j) {
        if (j == 0) return;

        int p = (j - 1) / 2;
        T cur = (T) this.heap[j];
        T parent = (T) this.heap[p];

        if (comparator.compare(cur, parent) < 0) {
            swap(j, p);
            swim(p);
        }
    }

    private T swap(int i, int j) {
        T temp = (T)heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
        return temp;
    }

    private void shrink() {
        resize(.5);
    }

    private void grow() {
        resize(2);
    }

    private void resize(double scale) {
        Object[] newHeap = new Object[(int)(heap.length * scale)];
        System.arraycopy(heap, 0, newHeap, 0, size);
        this.heap = newHeap;
        this.capacity = heap.length;
    }

    public String toString() {
        Object[] t = new Object[size];
        System.arraycopy(heap, 0, t, 0, size);
        return Arrays.toString(t);
    }

}
