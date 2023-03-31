package BlackJack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static String pathToData = "./blackjack.csv";
    public static void main(String[] args) throws IOException {
        start();
        AccountManager manager = new AccountManager(pathToData);
        System.out.println(manager);
    }

    public static void start() throws IOException {
        Path datafile = Paths.get(pathToData);
        if (!Files.exists(datafile)) {
            Files.createFile(datafile);
        }

    }




}
