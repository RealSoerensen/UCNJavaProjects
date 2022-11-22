package Exercises.ExceptionExercise;

import Exercises.ExceptionExercise.Exceptions.BalanceUnderrunException;

/**
 * Account
 */
public class Account {
    private String name;
    private double balance;

    public Account(String name, double balance) {
        if (name.trim().equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        } else {
            this.name = name;
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        } else {
            this.balance = balance;
        }
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        } else {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new BalanceUnderrunException("Amount must be positive");
        } else if (amount > balance) {
            throw new BalanceUnderrunException("Amount must be less than balance");
        } else {
            balance -= amount;
        }
    }

}