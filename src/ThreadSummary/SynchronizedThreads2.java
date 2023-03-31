package ThreadSummary;

public class SynchronizedThreads2 {


    /**
     * This main program spawns a bunch of Incrementor threads
     * at the same time.  All Incrementors are modifying the same
     * shared Counter object.  Since they are all running at the
     * same time, we should see some thread interference (aka
     * race conditions) as they try to modify the same Counter.
     */
    public static void main(String args[]) throws InterruptedException {
        SynchronizedThreads2.Counter counter = new SynchronizedThreads2.Counter();
        int numThreads = 20;
        int numAdds = 15;
        for (int i = 0; i < numThreads; i++) {
            new Thread(new SynchronizedThreads2.Incrementor(counter, numAdds, i+1)).start();
        }
        Thread.sleep(1000);  // pauses main for 1000 msec to make sure all threads have finished before we getCount
        int expectedCount = numThreads * numAdds;
        int actualCount = counter.getCount();
        if (actualCount != expectedCount) {
            System.out.println("Thread interference!  Counter is " + actualCount + " but should be " + expectedCount + ".");
        } else {
            System.out.println("No thread interference detected. Counter is " + actualCount + ".");
        }
    }

    /**
     * Incrementer is a thread that has a reference to a shared Counter
     * object.  Once this thread starts, it increments the shared Counter
     * several times.
     */
    static class Incrementor implements Runnable {

        private final SynchronizedThreads2.Counter counter;
        private final int numIncrements;
        private final String name;

        public Incrementor(SynchronizedThreads2.Counter counter, int numIncrements, int index) {
            this.name = "Thread " + index;
            this.counter = counter;
            this.numIncrements = numIncrements;
        }

        public void run() {
            // increment the counter numIncrements times
            for (int i = 0; i < numIncrements; i++) {
                synchronized (counter) {
                    counter.increment(name);
                }
            }
        }
    }

    static class Counter {
        private int count = 0;

        /**
         * Increase the counter by one.
         * @param name the name of the incrementer (i.e., whoever called this method)
         */
        public void increment(String name) {
            int currCount = count;  // read
            // introduce a delay between read and write to "encourage" race conditions
            System.out.println("Shared counter incremented by " + name + ".");
            count = currCount + 1;  // write
        }

        public int getCount() {
            return count;
        }
    }

}
