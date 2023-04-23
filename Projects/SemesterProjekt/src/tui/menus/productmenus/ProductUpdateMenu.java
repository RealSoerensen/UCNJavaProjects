package tui.menus.productmenus;

import controller.ProductController;
import model.productmodel.RentProduct;
import tui.TextInput;
import tui.TextOptions;

public class ProductUpdateMenu {
    private final TextInput textInput;
    private final TextOptions textOptions;
    private final ProductController productController;
    private final int barcode;

    public ProductUpdateMenu(int barcode) {
        textInput = new TextInput();
        textOptions = new TextOptions("Produkt opdater Menu", "Tilbage");
       this.barcode = barcode;
        productController = new ProductController();
    }

    private void updateOptions(){
        textOptions.addOption("Opdatere navn");
        textOptions.addOption("Opdatere pris");
        textOptions.addOption("Opdatere antal");
        textOptions.addOption("Opdatere beskrivelsen");
        if(productController.getProduct(barcode) instanceof RentProduct) {
            textOptions.addOption("Opdatere låneperiode");
        }
    }

    public void show(){
        updateOptions();
        while(true){
            textOptions.prompt();
            int choice;
            try{
                choice = textInput.getIntInput();
            } catch (NumberFormatException e){
                System.out.println("Prøv lige at skriv en tal istedet for. Det kan ikke være så svært :)");
                continue;
            }
            switch (choice) {
                case 1 -> updateName();
                case 2 -> updatePrice();
                case 3 -> updateQuantity();
                case 4 -> updateDescription();
                case 5 -> {
                    if(productController.getProduct(barcode) instanceof RentProduct) {
                        updateRentPeriod();
                    } else {
                        System.out.println("Invalid input");
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void updateRentPeriod() {
        System.out.println("********** Opdatere låneperiode **********");
        System.out.println("Ny låneperiode: ");
        int rentPeriod = textInput.getIntInput();
        if(productController.updateRentPeriod(barcode, rentPeriod)){
            System.out.println("Låneperiode opdateret");
        } else {
            System.out.println("Låneperiode kunne ikke opdateres");
        }
    }

    private void updateName() {
        System.out.println("********** Opdater navn **********");
        System.out.println("Indtast nyt navn");
        String name = textInput.getStringInput();
        if(productController.updateName(barcode, name)){
            System.out.println("Navn opdateret");
        } else {
            System.out.println("Navn kunne ikke opdateres");
        }
    }

    private void updatePrice() {
        System.out.println("********** Opdater pris **********");
        System.out.println("Indtast ny pris: ");
        double price;
        try {
           price = textInput.getDoubleInput();
        } catch (NumberFormatException e){
            System.out.println("Prøv lige at skriv en tal istedet for. Det kan ikke være så svært :)");
            return;
        }
        textInput.getStringInput();
        if(productController.updatePrice(barcode, price)){
            System.out.println("Pris opdateret");
        } else {
            System.out.println("Pris kunne ikke opdateres");
        }
    }

    private void updateQuantity() {
        System.out.println("********** Opdater antal  **********");
        System.out.println("Indtast nyt antal: ");
        int amount = textInput.getIntInput();
        if(productController.updateQuantity(barcode, amount)){
            System.out.println("Antal opdateret");
        } else {
            System.out.println("Antal kunne ikke opdateres");
        }
    }

    private void updateDescription() {
        System.out.println("********** Opdatere beskrivelsen **********");
        System.out.println("Indtast ny beskrivelse: ");
        String description = textInput.getStringInput();
        if(productController.updateDescription(barcode, description)){
            System.out.println("Beskrivelse opdateret");
        } else {
            System.out.println("Beskrivelse kunne ikke opdateres");
        }
    }
}
