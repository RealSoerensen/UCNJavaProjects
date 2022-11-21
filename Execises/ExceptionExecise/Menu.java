public class Menu {
    private MenuInput menuInput;
    private AccountController accountCtrl;

    public Menu() {
        menuInput = new MenuInput();
        accountCtrl = new AccountController();
    }

    public void run() {
        while (true) {
            displayMenu();
        }
    }

    private void displayMenu() {
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Display All Accounts");
        System.out.println("6. Exit");
        evalChoice();
    }

    public void evalChoice() {
        switch (menuInput.getIntChoice()) {
            case 1:
                createAccount();
                break;

            case 2:
                deposit();
                break;

            case 3:
                withdraw();
                break;

            case 4:
                transfer();
                break;

            case 5:
                displayAccount();
                break;

            case 6:
                closeMenu();
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    private void createAccount() {
        System.out.println("Enter account name: ");
        String name = menuInput.getStringChoice();
        accountCtrl.addAccount(name);
    }

    private void deposit() {
        System.out.println("Enter account name: ");
        String name = menuInput.getStringChoice();
        System.out.println("Enter amount: ");
        double amount = menuInput.getDoubleChoice();
        accountCtrl.depositFunds(name, amount);
    }

    private void withdraw() {
        System.out.println("Enter account name: ");
        String name = menuInput.getStringChoice();
        System.out.println("Enter amount: ");
        double amount = menuInput.getDoubleChoice();
        accountCtrl.withdrawFunds(name, amount);
    }

    private void transfer() {
        System.out.println("Enter from account name: ");
        String fromName = menuInput.getStringChoice();
        System.out.println("Enter to account name: ");
        String toName = menuInput.getStringChoice();
        System.out.println("Enter amount: ");
        double amount = menuInput.getDoubleChoice();
        accountCtrl.transferFunds(fromName, toName, amount);
    }

    private void displayAccount() {
        accountCtrl.printAccounts();
    }

    public void closeMenu() {
        System.out.println("Goodbye");
        menuInput.closeScanner();
    }

}
