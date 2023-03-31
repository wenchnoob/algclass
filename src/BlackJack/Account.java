package BlackJack;

public class Account {
    private String name;
    private int balance;

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void updateBalance(int amount) {
        this.balance += amount;
    }

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    @Override
    public String toString() {
        return String.format("Username: %s, Balance: %d", this.name, this.balance);
    }
}
