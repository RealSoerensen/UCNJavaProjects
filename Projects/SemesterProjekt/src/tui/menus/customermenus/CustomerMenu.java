package tui.menus.customermenus;

import controller.CustomerController;
import tui.TextInput;
import tui.TextOptions;

import java.util.List;

public class CustomerMenu {
    private final TextInput textInput;
    private final TextOptions textOptions;
    private final CustomerController customerController;

    public CustomerMenu() {
        textInput = new TextInput();
        textOptions = new TextOptions("Kunde Menu", "Tilbage");
        customerController = new CustomerController();
    }

    public void addOptions(){
        textOptions.addOption("Tilføj Kunde");
        textOptions.addOption("Fjern Kunde");
        textOptions.addOption("Opdatere Kunde");
        textOptions.addOption("Vis Kunde");
        textOptions.addOption("Vis alle Kunder");
    }

    public void show() {
        addOptions();
        while (true){
            textOptions.prompt();
            int choice;
            try{
                choice = textInput.getIntInput();
            } catch(NumberFormatException e){
                System.out.println("Skriv da et tal din taber. Du skrev: " + e.getMessage());
                continue;
            }
            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> removeCustomer();
                case 3 -> updateCustomer();
                case 4 -> showCustomer();
                case 5 -> showAllCustomers();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    public void addCustomer(){
        System.out.println("********** Tilføj Kunde **********");
        System.out.println("Firma(f) eller private(p) kunde: ");
        String type = textInput.getStringInput();
        switch(type){
            case "f" -> addBusinessCustomer();
            case "p" -> addPrivateCustomer();
            default -> System.out.println("Invalid input");
        }
    }

    private String[] getCustomerInfo(){
        System.out.println("Navn: ");
        String name = textInput.getStringInput();
        System.out.println("Adresse: ");
        String address = textInput.getStringInput();
        System.out.println("Telefonnummer: ");
        String phone = textInput.getStringInput();
        System.out.println("Email: ");
        String email = textInput.getStringInput();
        System.out.println("CVR/CPR: ");
        String uniqueId = textInput.getStringInput();
        System.out.println("Pinkode: ");
        String pin = textInput.getStringInput();
        return new String[]{name, address, phone, email, pin, uniqueId};
    }
    private void addBusinessCustomer(){
        String[] info = getCustomerInfo();
        if(customerController.addBusinessCustomer(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]), info[5])){
            System.out.println("Kunde tilføjet");
        } else {
            System.out.println("Kunde ikke tilføjet");
        }
    }

    private void addPrivateCustomer(){
        String[] info = getCustomerInfo();
        if(customerController.addPrivateCustomer(info[0], info[1], info[2], info[3], Integer.parseInt(info[4]), info[5])){
            System.out.println("Kunde tilføjet");
        } else {
            System.out.println("Kunde ikke tilføjet");
        }
    }

    private void removeCustomer(){
        System.out.println("********** Fjern Kunde **********");
        System.out.println("Indtast kundens tlf. nr.: ");
        String phone = textInput.getStringInput();
        if(customerController.removeCustomer(phone)){
            System.out.println("Kunde fjernet");
        } else {
            System.out.println("Kunde ikke fjernet");
        }
    }

    private void updateCustomer(){
        System.out.println("********** Opdatere Kunde **********");
        System.out.println("Indtast kundens tlf. nr.: ");
        String phone = textInput.getStringInput();
        try {
            int customerId = customerController.getCustomerId(phone);
            if(customerId != -1){
                new CustomerUpdateMenu(customerId).show();
            } else {
                System.out.println("Kunde ikke fundet");
            }
        } catch (Exception e) {
            System.out.println("Skriv venligst et tal");
        }
    }
    private void showCustomer(){
        System.out.println("********** Vis Kunde **********");
        System.out.println("Indtast kundens tlf. nr.: ");
        String phone = textInput.getStringInput();
        if(customerController.getCustomer(phone) != null){
            System.out.println(customerController.getCustomer(phone));
        } else {
            System.out.println("Kunde ikke fundet");
        }
        System.out.println("\nTryk på enter for at komme tilbage");
        textInput.getStringInput();
    }

    private void showAllCustomers(){
        System.out.println("********** Vis alle Kunder **********");
        List<String> customers = customerController.printAllCustomers();
        for(String customer : customers){
            System.out.println();
            System.out.println(customer);
        }
        System.out.println("\nTryk på enter for at komme tilbage");
        textInput.getStringInput();
    }
}
