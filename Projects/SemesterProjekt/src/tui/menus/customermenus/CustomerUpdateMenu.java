package tui.menus.customermenus;

import controller.CustomerController;
import model.customermodel.BusinessCustomer;
import tui.TextInput;
import tui.TextOptions;

public class CustomerUpdateMenu {
    private final int customerId;
    private final TextOptions textOptions;
    private final TextInput textInput;
    private final CustomerController customerController;

    public CustomerUpdateMenu(int customerId) {
        this.customerId = customerId;
        customerController = new CustomerController();
        textOptions = new TextOptions("Opdatere medarbejder", "Tilbage");
        textInput = new TextInput();
    }

    public void addUpdateOptions(){
        textOptions.addOption("Opdatere navn");
        textOptions.addOption("Opdatere adresse");
        textOptions.addOption("Opdatere telefonnummer");
        textOptions.addOption("Opdatere email");
        textOptions.addOption("Opdatere pinkode");
        textOptions.addOption("Opdatere CPR/CVR");
        if(customerController.getCustomerById(customerId) instanceof BusinessCustomer){
            textOptions.addOption("Opdatere kredit");
            textOptions.addOption("Opdatere rabat");
        }
    }

    public void show() {
        addUpdateOptions();
        while (true){
            textOptions.prompt();
            int choice;
            try {
                choice = textInput.getIntInput();
            } catch (NumberFormatException e) {
                System.out.println("Ugyldigt input");
                continue;
            }
            switch (choice) {
                case 1 -> updateName();
                case 2 -> updateAddress();
                case 3 -> updatePhone();
                case 4 -> updateEmail();
                case 5 -> updatePin();
                case 6 -> updateUniqueIdentifier();
                case 7 -> {
                    if (customerController.getCustomerById(customerId) instanceof BusinessCustomer) {
                        updateCredit();
                    }
                }
                case 8 -> {
                    if (customerController.getCustomerById(customerId) instanceof BusinessCustomer) {
                        updateDiscount();
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void updateDiscount() {
        System.out.println("Indtast den nye rabat");
        double discount;
        try {
            discount = textInput.getDoubleInput();
        } catch (NumberFormatException e) {
            System.out.println("Ugyldigt input");
            return;
        }
        if(customerController.updateDiscount(customerId, discount)){
            System.out.println("Rabat opdateret");
        } else {
            System.out.println("Rabat kunne ikke opdateres");
        }
    }

    private void updateCredit() {
        System.out.println("Indtast kredit");
        double credit = textInput.getDoubleInput();
        if(customerController.updateCredit(customerId, credit)){
            System.out.println("Kredit opdateret");
        } else {
            System.out.println("Kredit kunne ikke opdateres");
        }
    }

    private void updateUniqueIdentifier() {
        System.out.println("Indtast det nye CPR/CVR: ");
        String uniqueIdentifier = textInput.getStringInput();
        if(customerController.updateUniqueIdentifier(customerId, uniqueIdentifier)){
            System.out.println("CPR/CVR opdateret");
        } else {
            System.out.println("CPR/CVR kunne ikke opdateres");
        }
    }

    private void updateName() {
        System.out.println("********** Opdatere navn **********");
        System.out.println("Nyt navn: ");
        String name = textInput.getStringInput();
        if(customerController.updateName(customerId, name)){
            System.out.println("Navn opdateret");
        } else {
            System.out.println("Navn kunne ikke opdateres");
        }
    }

    private void updateAddress() {
        System.out.println("********** Opdatere adresse **********");
        System.out.println("Ny adresse: ");
        String address = textInput.getStringInput();
        if(customerController.updateAddress(customerId, address)){
            System.out.println("Adresse opdateret");
        } else {
            System.out.println("Adresse kunne ikke opdateres");
        }
    }

    private void updatePhone() {
        System.out.println("********** Opdatere telefonnummer **********");
        System.out.println("Nyt telefonnummer: ");
        String phone = textInput.getStringInput();
        if(customerController.updatePhone(customerId, phone)){
            System.out.println("Telefonnummer opdateret");
        } else {
            System.out.println("Telefonnummer kunne ikke opdateres");
        }
    }

    private void updateEmail() {
        System.out.println("********** Opdatere email **********");
        System.out.println("Ny email: ");
        String email = textInput.getStringInput();
        if(customerController.updateEmail(customerId, email)){
            System.out.println("Email opdateret");
        } else {
            System.out.println("Email kunne ikke opdateres");
        }
    }

    private void updatePin() {
        System.out.println("********** Opdatere pinkode **********");
        System.out.println("Ny pinkode: ");
        int pin;
        try{
            pin = textInput.getIntInput();
        } catch (NumberFormatException e){
            System.out.println("Ugyldigt input");
            return;
        }
        if(customerController.updatePin(customerId, pin)){
            System.out.println("Pinkode opdateret");
        } else {
            System.out.println("Pinkode kunne ikke opdateres");
        }
    }
}
