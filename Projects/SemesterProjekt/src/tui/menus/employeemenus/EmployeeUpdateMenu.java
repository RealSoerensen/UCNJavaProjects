package tui.menus.employeemenus;

import controller.EmployeeController;
import tui.TextInput;
import tui.TextOptions;

public class EmployeeUpdateMenu {
    private final TextInput textInput;
    private final TextOptions textOptions;
    private final EmployeeController employeeController;
    private String employeeUsername;
    
    public EmployeeUpdateMenu(String employeeUsername) {
        this.employeeUsername = employeeUsername;
        textInput = new TextInput();
        textOptions = new TextOptions("Medarbejder opdater Menu", "Tilbage");
        employeeController = new EmployeeController();
    }

    private void updateOptions() {
        textOptions.addOption("Opdatere navn");
        textOptions.addOption("Opdatere username");
        textOptions.addOption("Opdatere password");
        textOptions.addOption("Opdatere rolle");
    }

    public void show() {
        updateOptions();
        while (true) {
            textOptions.prompt();
            int choice;
            try {
                choice = textInput.getIntInput();
            } catch (NumberFormatException e) {
                System.out.println("Prøv lige at skriv en tal istedet for. Det kan ikke være så svært :)");
                continue;
            }
            switch (choice) {
                case 1 -> updateName();
                case 2 -> updateUsername();
                case 3 -> updatePassword();
                case 4 -> updateRole();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void updateName() {
        System.out.println("********** Opdatere navn **********");
        System.out.println("Nyt navn: ");
        String name = textInput.getStringInput();
        if(employeeController.updateName(employeeUsername, name)){
            System.out.println("Navn opdateret");
        } else {
            System.out.println("Navn kunne ikke opdateres");
        }
    }

    private void updateUsername() {
        System.out.println("********** Opdatere brugernavn **********");
        System.out.println("Nyt brugernavn: ");
        String username = textInput.getStringInput();
        if(employeeController.updateUsername(employeeUsername, username)){
            employeeUsername = username;
            System.out.println("Brugernavn opdateret");
        } else {
            System.out.println("Brugernavn kunne ikke opdateres");
        }
    }

    private void updatePassword() {
        System.out.println("********** Opdatere adgangskode **********");
        System.out.println("Ny adgangskode: ");
        String password = textInput.getStringInput();
        if(employeeController.updatePassword(employeeUsername, password)){
            System.out.println("Adgangskode opdateret");
        } else {
            System.out.println("Adgangskode kunne ikke opdateres");
        }
    }

    private void updateRole() {
        System.out.println("********** Opdatere rolle **********");
        System.out.println("Ny rolle: ");
        int role;
        try {
            role = textInput.getIntInput();
        } catch (NumberFormatException e) {
            System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
            return;
        }
        int numberOfRoleThree = 0;
        if(employeeController.getEmployee(employeeUsername).getRole() == 3) {
            int i = 0;
            while (i < employeeController.getEmployees().size()) {
                if ((employeeController.getEmployees().get(i).getRole() == 3)) {
                    numberOfRoleThree++;
                }
                i++;
            }
        }
        if(employeeController.getEmployee(employeeUsername).getRole() == 3 && numberOfRoleThree < 2) {
            System.out.println("Kunne ikke opdatere din rolle, da du er den eneste direktør.");
            System.out.println("Tilføj endnu en direktør hvis du vil ændre din rolle.");
        } else if(employeeController.updateRole(employeeUsername, role)){
            System.out.println("Rolle opdateret");
            System.out.println("Næste gang denne medarbejde logger ind har de rollen: " + role);
        } else {
            System.out.println("Rolle kunne ikke opdateres");
        }
    }
}
