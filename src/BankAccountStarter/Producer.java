package BankAccountStarter;

/**
* A typical producer deposits $10 x 5 = $50
*/
public class Producer extends Thread {
   private BankAccount account;
   public Producer(BankAccount acct) { account = acct; }
   public void run() {
      for(int i = 0; i < 15; i++) {
         //synchronized (account) {
            account.deposit(10);
           // account.notifyAll();
         //}
      }
   }
}