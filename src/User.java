import java.util.ArrayList;
import java.util.List;

public class User {

    String login;
    String password;
    String name;
    int age;
    int balance;


    List<String> history = new ArrayList<>();

    public User(String login, String password, String name, int age, int saldo) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.age = age;
        this.balance = saldo;
        this.history = new ArrayList<>();
    }

    public int function(int saldo, int wybor) {
        switch (wybor) {
            case 1:
                System.out.println("Your balance is: " + balance + " PLN");
                break;

            case 2:
                Transactions.pourOver(this);
                break;

            case 3:
                Transactions.showHistory(login);
                break;

            default:
                System.out.println("Incorrect selection");
        }
        return saldo;
    }

}
