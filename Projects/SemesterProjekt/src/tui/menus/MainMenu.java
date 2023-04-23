package tui.menus;

import controller.EmployeeController;
import tui.TextInput;
import tui.TextOptions;
import tui.menus.customermenus.CustomerMenu;
import tui.menus.employeemenus.EmployeeMenu;
import tui.menus.productmenus.ProductMenu;

public class MainMenu {
    private final TextInput textInput;
    private final TextOptions textOptions;
    private final int role;
    private final EmployeeController employeeController = new EmployeeController();


    public MainMenu(int employeeId) {
        textInput = new TextInput();
        textOptions = new TextOptions("Hoved Menu", "Log ud");
        role = getEmployeeRole(employeeId);
    }

    private int getEmployeeRole(int employeeId) {
        return employeeController.getEmployeeRole(employeeId);
    }

    private void addOptions() {
        if(role > 2 ){
            textOptions.addOption("Medarbejder Menu");
        }
        if(role > 1){
            textOptions.addOption("Produkt Menu");
        }
        if(role > 0){
            textOptions.addOption("Kunde Menu");
            textOptions.addOption("Ordre Menu");
        }
    }

    public void adminOptions(int choice){
        switch (choice) {
            case 1 -> new EmployeeMenu().show();
            case 2 -> new ProductMenu().show();
            case 3 -> new CustomerMenu().show();
            case 4 -> new OrderMenu().show();
            default -> System.out.println("Invalid input");
        }
    }

    public void managerOptions(int choice){
        switch (choice) {
            case 1 -> new ProductMenu().show();
            case 2 -> new CustomerMenu().show();
            case 3 -> new OrderMenu().show();
            default -> System.out.println("Invalid input");
        }
    }

    public void employeeOptions(int choice){
        switch (choice) {
            case 1 -> new CustomerMenu().show();
            case 2 -> new OrderMenu().show();
            default -> System.out.println("Invalid input");
        }
    }

    public void show() {
        addOptions();
        while(true) {
            int choice;
            textOptions.prompt();
            try{
                choice = textInput.getIntInput();
            } catch (Exception e){
                System.out.println("BENYT KUN TAL: " + e.getMessage());
                continue;
            }
            if(choice == 0){
                break;
            }
            if(role > 2){
                adminOptions(choice);
            } else if(role > 1){
                managerOptions(choice);
            } else if(role > 0){
                employeeOptions(choice);
            }
        }
    }
}
