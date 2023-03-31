package datastructures;

import java.util.Comparator;
import java.util.List;

public class MaxHeap<T extends Comparable<? super T>> extends Heap<T> {

    public MaxHeap() {
        super(Comparator.reverseOrder());
    }

    public MaxHeap(List<T> init) {
        this();
        simple_init(init);
    }

    public T removemax() {
        return removeHead();
    }
}
