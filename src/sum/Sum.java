package sum;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

public class Sum {

    public static void main(String...args) {

        double[] elems = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble()).limit(200000000).toArray();

        // Iterative
        long start = System.currentTimeMillis();
        double val = iterativeSum(elems);
        long end = System.currentTimeMillis();
        System.out.printf("Iterative sum result: %s. In time: %sms.%n", val, end - start);

        // Thread -- Parallel
        start = System.currentTimeMillis();
        val = new Calc(elems, 0, elems.length).compute();
        end = System.currentTimeMillis();
        System.out.printf("Parallel sum result: %s. In time: %sms.%n", val, end - start);

    }

    public static double iterativeSum(double[] elems) {
        double sum = 0;
        for (double d: elems) sum += d;
        return sum;
    }

    static class Calc extends RecursiveTask<Double> {
        private double[] elems;
        private int start;
        private int end;

        public Calc(double[] elems, int start, int end) {
            this.elems = elems;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Double compute() {
            if (end - start <= 1000) {
                double sum = 0;
                for (int i = start; i < end; i++) sum += elems[i];
                return sum;
            }
            int mid = start + (end - start) / 2;

            Calc p1 = new Calc(elems, start, mid);
            p1.fork();
            Calc p2 = new Calc(elems, mid, end);

            return p2.compute() + p1.join();
        }
    }

}
