package tui.menus.employeemenus;

import controller.EmployeeController;
import model.employeemodel.Employee;
import tui.TextInput;
import tui.TextOptions;

import java.util.List;

public class EmployeeMenu {
    private final TextInput textInput;
    private final TextOptions textOptions;
    private final EmployeeController employeeController;

    public EmployeeMenu() {
        textInput = new TextInput();
        textOptions = new TextOptions("Medarbejder Menu", "Tilbage");
        employeeController = new EmployeeController();
    }
    public void addOptions() {
        textOptions.addOption("Tilføj Medarbejder");
        textOptions.addOption("Fjern Medarbejder");
        textOptions.addOption("Opdater Medarbejder");
        textOptions.addOption("Vis Medarbejder");
        textOptions.addOption("Vis alle Medarbejder");
    }

    public void show() {
        textOptions.clear();
        addOptions();
        boolean running = true;
        while (running){
            int choice;
            textOptions.prompt();
            try{
                choice = textInput.getIntInput();
            } catch (NumberFormatException e){
                System.out.println("Skriv da et tal din taber. Du skrev: " + e.getMessage());
                continue;
            }
            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> removeEmployee();
                case 3 -> updateEmployee();
                case 4 -> showEmployee();
                case 5 -> showAllEmployees();
                case 0 -> running = false;
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void updateEmployee() {
        System.out.println("********** Opdatere Medarbejder **********");
        System.out.println("Indtast medarbejderens brugernavn: ");
        String username = textInput.getStringInput();
        if(employeeController.getEmployee(username) != null){
            new EmployeeUpdateMenu(username).show();
        } else {
            System.out.println("Medarbejder ikke fundet");
        }
    }

    private void addEmployee() {
        while (true) {
            System.out.println("********** Tilføj Medarbejder **********");
            System.out.println("Indtast '0' for at gå tilbage");
            System.out.println("Indtast navn: ");
            String name = textInput.getStringInput();
            if (checkIfZero(name)) {
                break;
            }
            System.out.println("Indtast brugernavn: ");
            String username = textInput.getStringInput();
            if (checkIfZero(username)) {
                break;
            }
            System.out.println("Indtast adgangskode: ");
            String password = textInput.getStringInput();
            if (checkIfZero(password)) {
                break;
            }
            System.out.println("Bekræft adgangskode: ");
            String passwordConfirm = textInput.getStringInput();
            if (checkIfZero(passwordConfirm)) {
                break;
            }
            System.out.println("Indtast Rolle: ");
            int role;
            try {
                role = textInput.getIntInput();
            } catch (NumberFormatException e) {
                System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
                return;
            }
            checkIfZero(String.valueOf(role));
            if (password.equals(passwordConfirm)) {
                if (employeeController.addEmployee(name, username, password, role)) {
                    System.out.println("Medarbejder Tilføjet");
                } else {
                    System.out.println("Medarbejder blev ikke tilføjet");
                }
            } else {
                System.out.println("Adgangskoderne matcher ikke");
            }
        }
    }

    private boolean checkIfZero(String test) {
        return test.equals("0");
    }

    private void removeEmployee() {
        while (true) {
            System.out.println("********** Fjern Medarbejder **********");
            System.out.println("Indtast '0' hvor som når for at gå tilbage");
            System.out.println("Indtast medarbejder brugernavn: ");
            String username = textInput.getStringInput();
            if (checkIfZero(username)) {
                break;
            } else if (employeeController.getEmployee(username) == null){
                System.out.println("Medarbejder blev ikke fundet\n");
                continue;
            } else if (employeeController.getEmployee(username).getRole() == 3) {
                System.out.println("Du kan ikke slette en direktør fra systmet.");
                continue;
            }

            System.out.println("Er du sikker på du vil fjerne " + username + "? [ja/nej]:");
            String input = textInput.getStringInput();
            if (input.equals("ja")) {
                if(employeeController.removeEmployee(username)){
                    System.out.println("Medarbejder fjernet\n");
                } else {
                    System.out.println("Medarbejder blev ikke fjernet\n");
                }
            } else {
                System.out.println("Medarbejder blev ikke fjernet\n");
            }

        }
    }

    private void showEmployee() {
        while (true) {
            System.out.println("********** Vis Medarbejder **********");
            System.out.println("Indtast '0' for at gå tilbage");
            System.out.println("Indtast Medarbejder brugernavn: ");
            String username = textInput.getStringInput();
            if (checkIfZero(username)) {
                break;
            }
            Employee employee = employeeController.getEmployee(username);
            if (employee != null) {
                System.out.println(employee);
                System.out.println("\nTryk på enter for at komme tilbage");
                textInput.getStringInput();
                break;
            } else {
                System.out.println("Medarbejder blev ikke fundet\n");
            }
        }
    }

    private void showAllEmployees() {
        System.out.println("********** Vis alle medarbejder **********");
        List<Employee> employees = employeeController.getEmployees();
        for (Employee employee : employees) {
            System.out.println(employee + "\n");
        }
        System.out.println("Try på enter for at komme tilbage");
        textInput.getStringInput();
    }
}
