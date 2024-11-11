import java.util.Scanner;

public class Main {
    private static Bank bank = new Bank();
    private static Scanner scanner = new Scanner(System.in);
    private static int currentAccountNumber = -1;

    public static void main(String[] args) {
        while (true) {
            showListToEnterChoice();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    showRegistration();
                    break;
                case 2:
                    showLogin();
                    break;
                case 3:
                    if (currentAccountNumber != -1) {
                        showMenuOptions();
                    } else {
                        System.out.println("Please log in and create an account first.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting from Banking Application...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private static void showListToEnterChoice() {
        System.out.println("\n--- Banking Application ---");
        System.out.println("1. User Registration");
        System.out.println("2. Login Here");
        System.out.println("3. User Menu");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void showRegistration() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (bank.register(username, password)) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("User already exists.");
        }
    }

    private static void showLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (bank.login(username, password)) {
            System.out.println("Login successful.");
            // Check if the logged-in user has an account
            Account account = bank.getAccount();
            if (account == null) {
                System.out.println("No account found. Please create an account.");
                showMenuOptions(); // This will include the option to create an account
            } else {
                currentAccountNumber = account.getAccountNumber();
                System.out.println("Your account number: " + currentAccountNumber);  // Display the account number
                showMenuOptions();
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }


    private static void showMenuOptions() {
        while (true) {
            showUserMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    if (currentAccountNumber == -1) {
                        createAccount();
                    } else {
                        System.out.println("Account already exists.");
                    }
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    generateStatement();
                    break;
                case 6:
                    calculateInterest();
                    break;
                case 7:
                    bank.logout();
                    currentAccountNumber = -1;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private static void showUserMenu() {
        System.out.println("\nUser Menu:");
        if (currentAccountNumber == -1) {
            System.out.println("1. Create Account");
        }
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. View Transaction Statement");
        System.out.println("6. Calculate Interest (Savings Account)");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
    }

    private static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account type (savings/checking): ");
        String type = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        if (bank.createAccount(name, type, deposit)) {
            Account account = bank.getAccount();
            currentAccountNumber = account.getAccountNumber();
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Account creation failed.");
        }
    }

    private static void generateStatement() {
        Account account = bank.getAccount();
        currentAccountNumber = account.getAccountNumber();
        System.out.println("Your account number: " + currentAccountNumber);
        System.out.print("Enter account number for statement: ");
        int accountNumber = scanner.nextInt();
        bank.generateStatement(accountNumber);
    }
    private static void depositMoney() {
        Account account = bank.getAccount();
        currentAccountNumber = account.getAccountNumber();
        System.out.println("Your account number: " + currentAccountNumber);
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        if (bank.deposit(accountNumber, amount)) {
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Deposit failed.");
        }
    }




    private static void withdrawMoney() {
        Account account = bank.getAccount();
        currentAccountNumber = account.getAccountNumber();
        System.out.println("Your account number: " + currentAccountNumber);
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (bank.withdraw(accountNumber, amount)) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void calculateInterest() {
        Account account = bank.getAccount();
        currentAccountNumber = account.getAccountNumber();
        System.out.println("Your account number: " + currentAccountNumber);
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        System.out.print("Enter interest rate: ");
        double rate = scanner.nextDouble();
        bank.calculateInterest(accountNumber, rate);
        System.out.println("Interest added.");
    }





    private static void checkBalance() {
        System.out.println("Current balance: " + bank.getBalance(currentAccountNumber));
    }
}
