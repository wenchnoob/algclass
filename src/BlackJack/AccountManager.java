package BlackJack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AccountManager {

    private ArrayList<Account> accounts;

    public AccountManager(String pathToData) {
        this.accounts = new ArrayList<>();
        List<String> records = readCSV(pathToData);
        if (Objects.nonNull(records))
            for (String record: records) {
                String[] elems = record.split(",");
                String name = elems[0];
                int balance = Integer.parseInt(elems[1]);
                this.accounts.add(new Account(name, balance));
            }
    }

    public boolean createAccount(String name, int startingBalance) {
        if (accountExist(name)) return false;
        addAccount(new Account(name, startingBalance));
        return true;
    }

    public boolean accountExist(String name) {
        return false;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public void removeAccount(String username) {
        int idx = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getName().equals(username)) {
                idx = i;
                break;
            }
        }
        accounts.remove(idx);
        flush();
    }

    public static List<String> readCSV(String path) {
        Path file = Paths.get(path);
        if (!Files.exists(file)) return null;
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void flush() {

    }
    @Override
    public String toString() {
        return accounts.stream().map(Account::toString).collect(Collectors.joining("\n"));
    }

}
