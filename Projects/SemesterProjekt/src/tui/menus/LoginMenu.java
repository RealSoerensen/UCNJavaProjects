package tui.menus;

import controller.EmployeeController;
import tui.TextInput;

public class LoginMenu {
    TextInput textInput;
    EmployeeController employeeController;

    public LoginMenu() {
        textInput = new TextInput();
        employeeController = new EmployeeController();
    }

    public void show() {
        while (true) {
            System.out.println("********** Login **********");
            System.out.println("Brugernavn: ");
            String username = textInput.getStringInput();
            System.out.println("Adgangskode: ");
            String password = textInput.getStringInput();

            int employeeId = employeeController.login(username, password);
            if(employeeId != 0) {
                MainMenu mainMenu = new MainMenu(employeeId);
                mainMenu.show();
            } else {
                System.out.println("Forkert brugernavn eller adgangskode. Prøv venligst igen.");
            }
        }
    }
}
