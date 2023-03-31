package writename;

import java.util.concurrent.Executors;

public class WriteName {

    public static void main(String[] args) throws InterruptedException {
        printLetters("Wenchy");
    }

    public static void printLetters(String str) throws InterruptedException {
        Thread pred = null;
        for (char s: str.toCharArray()) {
            pred = new Thread(new WriteLetter(s, pred));
            pred.start();
//            t.join();
        }
    }


    private static class WriteLetter implements Runnable {

        private char l;

        private Thread pred;

        public WriteLetter(char l, Thread pred) {
            this.l = l;
            this.pred = pred;
        }

        @Override
        public void run() {
            if (pred != null)
                try {
                    pred.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            System.out.print(l + " ");
        }
    }
}
