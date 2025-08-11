import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {
    private final String accountNumber;
    private final String accountHolder;
    protected double balance;
    private final List<String> transactionHistory;

    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created for " + accountHolder + " (" + accountNumber + ") with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + " | New Balance: " + balance);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + " | New Balance: " + balance);
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds!");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History for " + accountHolder + " (" + accountNumber + "):");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}


public class SavingsAccount extends Account {
    private final double interestRate; // as percentage

    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance, double interestRate) {
        super(accountNumber, accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

    public void withdraw(double amount) {
        if (balance - amount < 500) { // Minimum balance rule
            System.out.println("Cannot withdraw. Minimum balance of 500 required!");
        } else {
            super.withdraw(amount);
        }
    }

    public void addInterest() {
        double interest = (balance * interestRate) / 100;
        deposit(interest);
        System.out.println("Interest of " + interest + " added.");
    }
}
