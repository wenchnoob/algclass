package BankAccountStarter;

/**
* A typical consumer withdraws $10 x 5 = -$50
*/

public class Consumer extends Thread {
   private BankAccount account;
   public Consumer(BankAccount acct) { account = acct; }
   public void run() {
      for(int i = 0; i < 5; i++) {
//         synchronized (account) {
//            while ( account.getBalance() < 10) {
//               try {
//                  account.wait();
//               } catch (InterruptedException ie) {
//                  ie.printStackTrace();
//               }
//            }
            account.withdraw(10);
        // }
      }
   }
}