package Exercises.ExceptionExercise;

import java.util.ArrayList;
import java.util.List;

import Exercises.ExceptionExercise.Exceptions.DuplicateAccountNameException;
import Exercises.ExceptionExercise.Exceptions.InvalidAccountNameException;

public class AccountContainer {
    private List<Account> accounts;
    private static AccountContainer instance;

    private AccountContainer() {
        accounts = new ArrayList<Account>();
    }

    public static AccountContainer getInstance() {
        if (instance == null) {
            instance = new AccountContainer();
        }
        return instance;
    }

    public void addAccount(Account account) {
        // Check if account is already in the list
        for (Account a : accounts) {
            if (a.getName().equals(account.getName())) {
                throw new DuplicateAccountNameException("Account already exists");
            }
        }
        accounts.add(account);
    }

    public Account findByName(String name) {
        Boolean found = false;
        Account account = null;
        for (int i = 0; i < accounts.size() && !found; i++) {
            if (accounts.get(i).getName().equals(name)) {
                found = true;
                account = accounts.get(i);
            }
        }
        if (account == null) {
            throw new InvalidAccountNameException("Account not found");
        } else {
            return account;
        }
    }

    public boolean transferFunds(String fromAccountName, String toAccountName, double amount) {
        boolean success = false;
        try {
            Account fromAccount = findByName(fromAccountName);
            Account toAccount = findByName(toAccountName);
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            success = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }

    public void printAccounts() {
        for (Account a : accounts) {
            System.out.println(a.getName() + ": " + a.getBalance());
        }
    }
}
