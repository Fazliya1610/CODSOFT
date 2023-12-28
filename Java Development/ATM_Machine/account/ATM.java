package account;

import java.util.Scanner;

public class ATM {
    private BankAccount userAccount;

    public ATM(BankAccount account) {
        this.userAccount = account;
    }

    public void displayOptions() {
        System.out.println("\nATM Options:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void processOption(int option) {
        switch (option) {
            case 1:
                checkBalance();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please choose a valid option.");
        }
    }

    public void checkBalance() {
        double balance = userAccount.getBalance();
        System.out.println("Your balance is: $" + balance);
    }

    public void deposit() {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the deposit amount: $");
        double amount = sc.nextDouble();

        if (amount > 0) {
            userAccount.deposit(amount);
            System.out.println("Deposit successful. Your new balance is: $" + userAccount.getBalance());
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive amount.");
        }
    }

    public void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the withdrawal amount: $");
        double amount = sc.nextDouble();

        if (userAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. Your new balance is: $" + userAccount.getBalance());
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance $1000
        ATM atm = new ATM(userAccount);
        Scanner sc = new Scanner(System.in);

        while (true) {
            atm.displayOptions();
            System.out.print("Enter your choice (1-4): ");
            int option = sc.nextInt();
            atm.processOption(option);
        }
    }
}
