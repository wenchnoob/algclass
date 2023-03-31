package datastructures;


import java.util.Comparator;
import java.util.List;

public class MinHeap<T extends Comparable<? super T>> extends Heap<T> {

    public MinHeap() {
        super(Comparator.naturalOrder());
    }

    public MinHeap(List<T> init) {
        this();
        simple_init(init);
    }

    public T removemin() {
        return removeHead();
    }

}
