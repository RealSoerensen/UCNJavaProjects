import java.util.ArrayList;

import Exceptions.DuplicateAccountNameException;
import Exceptions.InvalidAccountNameException;

public class AccountContainer {
    private ArrayList<Account> accounts;
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

    public void printAccounts() {
        for (Account a : accounts) {
            System.out.println(a.getName() + ": " + a.getBalance());
        }
    }
}
