import java.util.*;

public class Account {
    private static int accountNumberCounter = 1000;
    private int accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private List<Transaction> transactions;
    private Date lastInterestDate;

    public Account(String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = accountNumberCounter++;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add(new Transaction("Deposit", initialDeposit, new Date()));
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, new Date()));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount, new Date()));
            return true;
        }
        return false;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }



    public void addInterest(double rate) {
        if (accountType.equalsIgnoreCase("savings")) {
            Date currentDate = new Date();
            // If it's the first time adding interest or one full month has passed since the last interest addition
            if (lastInterestDate == null || hasOneFullMonthPassed(lastInterestDate, currentDate)) {
                double interest = balance * rate / 100;
                balance += interest;  // Add interest to the balance
                transactions.add(new Transaction("Interest", interest, currentDate)); // Record transaction
                lastInterestDate = currentDate; // Update last interest date to current date
            } else {
                System.out.println("Interest has already been added this month.");
            }
        }
    }

    // Updated method to check for a full month difference.
    private boolean hasOneFullMonthPassed(Date lastDate, Date currentDate) {
        Calendar calLast = Calendar.getInstance();
        Calendar calCurrent = Calendar.getInstance();

        calLast.setTime(lastDate);  // Set last interest date
        calCurrent.setTime(currentDate);  // Set current date

        // Add 1 month to the last interest date and compare it to current date
        calLast.add(Calendar.MONTH, 1);
        return calCurrent.after(calLast);  // Returns true if current date is after the last interest date + 1 month
    }


}
