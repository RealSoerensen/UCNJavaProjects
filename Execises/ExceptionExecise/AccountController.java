public class AccountController {
    private AccountContainer accountContainer;

    public AccountController() {
        accountContainer = AccountContainer.getInstance();
    }

    public Account addAccount(String name) {
        Account account = null;
        try {
            account = new Account(name, 0);
            accountContainer.addAccount(account);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

    public boolean depositFunds(String accountName, double amount) {
        boolean success = false;
        try {
            accountContainer.findByName(accountName).deposit(amount);
            success = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success;

    }

    public boolean withdrawFunds(String accountName, double amount) {
        boolean success = false;
        try {
            accountContainer.findByName(accountName).withdraw(amount);
            success = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }

    public boolean transferFunds(String fromAccountName, String toAccountName, double amount) {
        return accountContainer.transferFunds(fromAccountName, toAccountName, amount);
    }

    public void printAccounts() {
        accountContainer.printAccounts();
    }
}
