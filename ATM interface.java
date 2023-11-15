import java.util.Scanner;

class User {
    private String userId;
    private String pin;

    public User(String userId, String pin) {
        this.userId = userId;
        this.pin = pin;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }
}

class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("Transfer successful");
        } else {
            System.out.println("Transfer failed. Insufficient funds.");
        }
    }
}

class ATM {
    private User user;
    private BankAccount bankAccount;

    public ATM(User user, BankAccount bankAccount) {
        this.user = user;
        this.bankAccount = bankAccount;
    }

    public void displayMenu() {
        System.out.println("ATM Interface");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Transactions History");
        System.out.println("6. Quit");
    }

    public void viewBalance() {
        System.out.println("Current Balance: $" + bankAccount.getBalance());
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
        System.out.println("Deposit successful. Current balance: $" + bankAccount.getBalance());
    }

    public void withdraw(double amount) {
        if (bankAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current balance: $" + bankAccount.getBalance());
        }
    }

    public void transfer(BankAccount targetAccount, double amount) {
        bankAccount.transfer(targetAccount, amount);
    }

    public void showTransactionsHistory() {
        // Implementation for displaying transactions history
        System.out.println("Transaction History: Not implemented in this example.");
    }

    public static void main(String[] args) {
        User user = new User("12345", "5678");
        BankAccount bankAccount = new BankAccount("987654321", 1000.0);
        ATM atm = new ATM(user, bankAccount);

        Scanner scanner = new Scanner(System.in);

        // User authentication
        System.out.print("Enter User ID: ");
        String enteredUserId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (enteredUserId.equals(user.getUserId()) && enteredPin.equals(user.getPin())) {
            System.out.println("Authentication successful. Welcome to the ATM!");

            int choice;

            do {
                atm.displayMenu();
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.viewBalance();
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawalAmount = scanner.nextDouble();
                        atm.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        System.out.print("Enter transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        System.out.print("Enter target account number: ");
                        String targetAccountNumber = scanner.next();
                        BankAccount targetAccount = new BankAccount(targetAccountNumber, 0.0);
                        atm.transfer(targetAccount, transferAmount);
                        break;
                    case 5:
                        atm.showTransactionsHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 6);

        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }
}
