import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Transactions {

    private static Scanner scanner = new Scanner(System.in);

    public static void pourOver(User sender) {
        System.out.println("Enter the recipient's login: ");
        String recipientLogin = scanner.nextLine();


        boolean istnieje = UserService.isLoginExist("adult.json", recipientLogin)
                || UserService.isLoginExist("junior.json", recipientLogin);

        if (!istnieje) {
            System.out.println("Incorrect user!");
            return;
        }

        System.out.println("Enter the amount to be transferred: ");
        int amount = scanner.nextInt();
        scanner.nextLine();

        if (sender.balance < amount && amount>0) {
            System.out.println("Insufficient funds!");
            return;
        }


        boolean succes = UserService.pourOver(sender.login, recipientLogin, amount);
        sender.history.add("Transfer " + amount + " PLN to " + recipientLogin);
        safeHistory(sender, "Transfer " + amount + " PLN to " + recipientLogin);


        if (succes) {

            if (sender.history == null) sender.history = new ArrayList<>();
            sender.history.add("Transfer " + amount + " PLN to " + recipientLogin);

            int amountt = sender.balance;


            System.out.println("Transfer completed! New balance:" +(amountt-amount) + " PLN");
        }
    }
    public static void safeHistory(User user, String trescTransakcji) {
        String filename = user.login + "_history.txt";
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(trescTransakcji);

        } catch (IOException e) {
            System.out.println("Błąd zapisu historii: " + e.getMessage());
        }
    }
    public static void showHistory(String user) {

        String login = user;
        String filename = login + "_history.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                System.out.println(linia);
            }
        } catch (IOException e) {
            System.out.println("No history or reading error: " + e.getMessage());
        }
    }


}
