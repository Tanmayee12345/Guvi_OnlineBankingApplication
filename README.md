# Banking Application

This is a simple console-based banking application written in Java. It allows users to register, log in, create accounts, deposit/withdraw money, check balance, generate transaction statements, and calculate interest for savings accounts.

## Features

- **User Registration**: Allows new users to register by providing a username and password.
- **Login**: Users can log in using their username and password.
- **Account Creation**: After logging in, users can create a savings or checking account with an initial deposit.
- **Deposit Money**: Users can deposit money into their account.
- **Withdraw Money**: Users can withdraw money from their account if sufficient funds are available.
- **Check Balance**: Users can check their current balance.
- **Generate Transaction Statement**: Users can view a statement of all their transactions.
- **Interest Calculation**: Users with a savings account can calculate and add interest (monthly).
- **Logout**: Users can log out of the application.

## Prerequisites

- **Java 8 or higher** installed on your machine.
- Basic knowledge of Java programming.

## How to Run the Application

### Created Entities

This application consists of the following key classes/entities:

1. **Main**:
   - The entry point of the application that interacts with the user. It displays the menu and calls the corresponding methods for operations like registration, login, and user menu access.

2. **User**:
   - Represents the user details such as `username`, `password`, and a list of `accounts` associated with the user. It is used for handling user login and registration.

3. **Account**:
   - Represents a bank account. It includes details like `account number`, `account type`, `balance`, `transactions`, and methods for `deposit`, `withdraw`, and adding `interest` for savings accounts.

4. **Transaction**:
   - Represents a single financial transaction (deposit, withdrawal, interest addition). It stores details like `transaction type`, `amount`, and `date`.

5. **Bank**:
   - Handles the main logic for user registration, login, account management, and transactions. It stores all users and their corresponding accounts.

### How to Run the Application

1. Clone the repository or download the Java files.
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse) or a text editor.
3. Compile and run the `Main.java` class to start the application.

### Running the Application in Terminal (if not using an IDE):

1. Open a terminal/command prompt.
2. Navigate to the folder containing your Java files.
3. Compile the code:
   ```bash
   javac Main.java User.java Account.java Transaction.java Bank.java
