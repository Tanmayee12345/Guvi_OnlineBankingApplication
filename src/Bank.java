import java.util.*;

public class Bank {
    private Map<String, User> users;
    private Map<Integer, Account> accounts;
    private String loggedInUser;
    private Map<String, Integer> userAccountNumbers;

    public Bank() {
        users = new HashMap<>();
        accounts = new HashMap<>();
        userAccountNumbers = new HashMap<>();
    }

    public boolean register(String username, String password) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username, password));
        return true;
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = username;
            return true;
        }
        return false;
    }

    public boolean createAccount(String accountHolderName, String accountType, double initialDeposit) {
        if (loggedInUser == null) {
            return false;
        }
        Account newAccount = new Account(accountHolderName, accountType, initialDeposit);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        userAccountNumbers.put(loggedInUser, newAccount.getAccountNumber());
        return true;
    }

    public Account getAccount() {
        Integer accountNumber = userAccountNumbers.get(loggedInUser);
        return accountNumber != null ? accounts.get(accountNumber) : null;
    }

    public void logout() {
        loggedInUser = null;
    }

    public boolean deposit(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.withdraw(amount);
        }
        return false;
    }

    public void generateStatement(int accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Transaction statement for account number: " + accountNumber);
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("No account found with account number: " + accountNumber);
        }
    }

    public double getBalance(int accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getBalance() : 0;
    }

    public void calculateInterest(int accountNumber, double rate) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.addInterest(rate);
        }
    }
}
