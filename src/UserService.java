import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveUser(User user, String filename) {
        try {
            File file = new File(filename);
            List<User> users = new ArrayList<>();

            if (file.exists()) {
                try (Reader reader = new FileReader(file)) {
                    users = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
                }
                if (users == null) {
                    users = new ArrayList<>();
                }
            }

            users.add(user);

            try (Writer writer = new FileWriter(file)) {
                gson.toJson(users, writer);
            }

            System.out.println("Dane zapisane do " + filename);
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }

    public static User loginUser(String filename, String login, String password) {
        try (Reader reader = new FileReader(filename)) {
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
            if (users != null) {
                for (User u : users) {
                    if (u.login.equals(login) && u.password.equals(password)) {
                        return u;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        return null;
    }
    public static boolean isLoginExist(String filename, String login) {
        try (Reader reader = new FileReader(filename)) {
            List<User> users = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
            if (users != null) {
                for (User u : users) {
                    if (u.login.equals(login)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Read error: " + e.getMessage());
        }
        return false;
    }
    public static boolean pourOver(String nadawcaLogin, String odbiorcaLogin, int kwota) {
        try {

            List<User> adultUsers = new ArrayList<>();
            File adultFile = new File("adult.json");
            if (adultFile.exists()) {
                try (Reader reader = new FileReader(adultFile)) {
                    List<User> temp = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
                    if (temp != null) adultUsers.addAll(temp);
                }
            }


            List<User> juniorUsers = new ArrayList<>();
            File juniorFile = new File("junior.json");
            if (juniorFile.exists()) {
                try (Reader reader = new FileReader(juniorFile)) {
                    List<User> temp = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
                    if (temp != null) juniorUsers.addAll(temp);
                }
            }

            List<User> allUsers = new ArrayList<>();
            allUsers.addAll(adultUsers);
            allUsers.addAll(juniorUsers);

            User sender = null, recipient = null;
            for (User u : allUsers) {
                if (u.login.equals(nadawcaLogin)) sender = u;
                if (u.login.equals(odbiorcaLogin)) recipient = u;
            }

            if (sender == null || recipient == null) return false;
            if (sender.balance < kwota) return false;


            sender.balance -= kwota;

            recipient.balance += kwota;


            try (Writer writer = new FileWriter(adultFile)) {
                gson.toJson(adultUsers, writer);
            }
            try (Writer writer = new FileWriter(juniorFile)) {
                gson.toJson(juniorUsers, writer);
            }

            return true;
        } catch (IOException e) {
            System.out.println("Transfer error: " + e.getMessage());
            return false;
        }
    }



}
