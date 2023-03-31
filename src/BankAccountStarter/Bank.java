package BankAccountStarter;

/**
* The master thread is the bank. 
* It creates an account with an initial balance of $100. 
* It creates 4 account holders, 2 producers and 2 consumers
*/

public class Bank {

   public static void main(String[] args) {
      BankAccount account = new BankAccount(0);
      int tCount = 4;
      Thread[] threads = new Thread[tCount];
      for(int i = 0; i < tCount; i++) {
         if (i > 2) {
            threads[i] = new Producer(account);
         } else {
            threads[i] = new Consumer(account);
         }
      }
      for(int i = 0; i < tCount; i++) {
         threads[i].start();
      }
      for(int i = 0; i < tCount; i++) {
         try {
            threads[i].join();
         } catch(InterruptedException ie) {
               System.err.println(ie.getMessage());
         } finally {
            System.out.println("thread "+ i + " has died");
         }
      }
      System.out.print("Closing balance = ");
      System.out.println("$" + account.getBalance());
   }
}