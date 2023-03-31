package BankAccountStarter;

/** BankAccount object to demonstrate synchronization issues
*/
public class BankAccount {
	private double balance;
	/**
	 * @param bal balance that this account is given when created
	 */
	public BankAccount(double bal) { balance = bal; }
	/** 
	* Default construct set initial balance to zero
	*/
	public BankAccount() { this(0); }
	/**
	* @return balance of this account
	*/
	public synchronized double getBalance() { return balance; }

	/**
	* @param amt amount of money to be deposited into this account
	*/
	public synchronized void deposit(double amt) {
		double temp = balance;
		temp = temp + amt;
		try {
			Thread.sleep(300); // simulate production time
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
		System.out.println("after deposit balance = $" + temp);
		balance = temp;
		notify();
	}
	/**
	* @param amt amount of money to be withdrawn from this account
	*/
	public synchronized void withdraw(double amt) {
		while (balance < amt) {
			try { wait(); }
			catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		double temp = balance;
		temp = temp - amt;
		try {
			Thread.sleep(200); // simulate consumption time
		} catch (InterruptedException ie) {
			System.err.println(ie.getMessage());
		}
		System.out.println("after withdrawl balance = $" + temp);
		balance = temp;
	}
 }