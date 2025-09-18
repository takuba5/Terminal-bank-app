# 💳 BankApp

A simple console project in **Java** that simulates the operation of a banking system.

It allows you to:
- create user accounts,
- log in,
- check your balance,
- make transfers,
- view transaction history.

---

## 🚀 Features

- [x] New user registration
- [x] Logging in to an existing account
- [x] Separate accounts for adults (`adult.json`) and juniors (`junior.json`)

- [x] Balance check
- [x] Transfers between accounts
- [x] Transaction history saved in a `.txt` file

---

## ⚙️ Requirements

- Java 17+ (project written/tested on OpenJDK 23)
- IntelliJ IDEA (or other IDE supporting Java)

---

## ▶️ Starting

1. Clone the repository:
```bash
git clone https://github.com/your-login/BankApp.git

2. Open the project in IntelliJ IDEA or another IDE.

3. Run the file:

src/Main.java

## 📦 Dependencies

This project uses **Gson** (Google JSON library) for saving and reading user data in JSON files (`adult.json` and `junior.json`).  

### How to add Gson:

#### Option 1 – Manual JAR (simple)
1. Download the Gson JAR from: [https://github.com/google/gson](https://github.com/google/gson)  
2. In IntelliJ IDEA:  
   - Right-click on your project → `Open Module Settings` → `Libraries` → `+` → `Java`  
   - Select the downloaded `gson-2.x.x.jar` file and add it.  
3. Now the project can compile and run with Gson.


