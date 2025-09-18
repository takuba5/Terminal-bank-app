import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean loop = true;

        while (loop) {
            System.out.println("What do you want to do?");
            System.out.println("0 - Exit");
            System.out.println("1 - Create an account");
            System.out.println("2 - Log in");


            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createAccount();
                    loop = false;
                    break;
                case "2":
                    login();
                    loop = false;
                    break;
                case "0":
                    System.out.println("End of program.");
                    loop = false;
                    break;
                default:
                    System.out.println("Incorrect choice.");
            }
        }
    }

    private static void createAccount() {
        System.out.println("What's your name?");
        String name = scanner.nextLine();

        System.out.println("How old are you?");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter your login:");
        String login = scanner.nextLine();

        int attempts = 3;
        while (attempts > 0) {
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            if (password.length() < 8) {
                attempts--;
                System.out.println("Password too short! Attempts left: " + attempts);
                if (attempts == 0) {
                    System.out.println("Failed to create account.");
                    return;
                }
            } else {
                User user = new User(login, password, name, age, 0);
                String filename = (age < 18) ? "junior.json" : "adult.json";
                UserService.saveUser(user, filename);
                System.out.println("The account has been created.");
                break;
            }
        }
    }

    private static void login() {
        System.out.println("Enter your login:");
        String logintoenter= scanner.nextLine();
        String login = logintoenter.trim();

        System.out.println("Enter your password:");
        String passwordtoenter = scanner.nextLine();
        String password = passwordtoenter.trim();


        User user = UserService.loginUser("adult.json", login, password);
        if (user == null) {
            user = UserService.loginUser("junior.json", login, password);
        }

        if (user != null) {
            System.out.println("Logged in as: " + user.name);

            System.out.println("What do you want to do? 1 - Check your account balance, 2 - Transfer money to another account, 3 - Check recent transactions\n");
            int choise = scanner.nextInt();
            scanner.nextLine();
            int result = user.function(user.balance, choise);


        } else {
            System.out.println("Incorrect login or password.\n");
        }
    }

}
