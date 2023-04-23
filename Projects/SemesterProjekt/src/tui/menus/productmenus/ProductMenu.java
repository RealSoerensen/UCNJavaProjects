package tui.menus.productmenus;

import controller.ProductController;
import tui.TextInput;
import tui.TextOptions;

import java.util.List;
import java.util.Objects;

public class ProductMenu {
    private final TextInput textInput;
    private final TextOptions textOptions;
    private final ProductController productController;

    public ProductMenu() {
        textInput = new TextInput();
        textOptions = new TextOptions("Produkt menu", "Tilbage");
        productController = new ProductController();
    }

    private void addOptions(){
        textOptions.addOption("Opret salgs produkt");
        textOptions.addOption("Opret låne produkt");
        textOptions.addOption("Fjern produkt");
        textOptions.addOption("Opdater produkt");
        textOptions.addOption("Vis bestemt Product");
        textOptions.addOption("Vis alle produkter");
    }

    public void show() {
        addOptions();
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
                case 1 -> addSaleProduct();
                case 2 -> addRentProduct();
                case 3 -> removeProduct();
                case 4 -> updateProduct();
                case 5 -> showProduct();
                case 6 -> showAllProducts();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void showAllProducts() {
        System.out.println("********** Vis alle produkter **********");
        List<String> productList = productController.printAllProducts();
        for (String product : productList) {
            System.out.println(product + "\n");
        }
        System.out.println("Try på enter for at komme tilbage");
        textInput.getStringInput();
    }

    private void showProduct() {
        System.out.println("********** Vis produkt **********");
        System.out.println("Indtast '0' for at gå tilbage");
        System.out.println("Indtast stregkoden: ");
        try {
            int barcode = textInput.getIntInput();
            if(productController.getProduct(barcode) != null){
                String product = productController.printProduct(barcode);
                System.out.println(Objects.requireNonNullElse(product, "Produktet findes ikke"));
                System.out.println("\nTryk på enter for at komme tilbage");
                textInput.getStringInput();
            }else{
                System.out.println("Produktet findes ikke!");
            }
        } catch (Exception e) {
            System.out.println("Produktet findes ikke!");
        }
    }

    private void updateProduct() {
        System.out.println("********** Opdatere Produkt **********");
        System.out.println("Indtast '0' for at gå tilbage");
        System.out.println("Indtast stregkoden: ");
        try {
            int barcode = textInput.getIntInput();
            if(productController.getProduct(barcode) != null){
                new ProductUpdateMenu(barcode).show();
            } else {
                System.out.println("Produktet findes ikke");
            }
        } catch (Exception e) {
            System.out.println("Produktet findes ikke");

        }
    }

    private void removeProduct() {
        System.out.println("********** Fjern produkt **********");
        System.out.println("Indtast stregkoden: ");
        try {
            int barcode = textInput.getIntInput();
            if(productController.getProduct(barcode) != null){
                productController.removeProduct(barcode);
                System.out.println("Produktet er blevet fjernet");
            } else {
                System.out.println("Produktet findes ikke");
            }
        } catch (Exception e) {
            System.out.println("Produktet findes ikke");
        }
    }

    private void addSaleProduct() {
        while (true) {
            System.out.println("********** Opret produkt **********");
            addProduct(false);
            System.out.println("Vil du tilføje et nyt produkt? (y/n)");
            String choice = textInput.getStringInput();
            if(choice.equalsIgnoreCase("n")){
                break;
            }
        }
    }

    private void addRentProduct() {
        while(true){
            System.out.println("********** Opret udlejnings produkt **********");
            addProduct(true);
            System.out.println("Vil du tilføje et nyt produkt? (y/n)");
            String choice = textInput.getStringInput();
            if(choice.equalsIgnoreCase("n")){
                break;
            }
        }
    }

    private void addProduct(boolean isRentProduct) {
        try {
            System.out.println("Indtast '0' for at gå tilbage");
            System.out.println("Indtast stregkoden: ");
            System.out.print("> ");
            long barcode;
            try{
                barcode = textInput.getLongInput();
            } catch (NumberFormatException e){
                System.out.println("Prøv lige at skriv en tal istedet for. Det kan ikke være så svært :)");
                return;
            }
            if (barcode == 0) {
                textInput.getStringInput();
                return;
            }
            System.out.println("Indtast navn: ");
            String name = textInput.getStringInput();
            if (name.equals("0")) {
                return;
            }

            System.out.println("Indtast pris: ");
            double price;
            try{
                price = textInput.getDoubleInput();
            } catch (NumberFormatException e){
                System.out.println("Prøv lige at skriv en tal istedet for. Det kan ikke være så svært :)");
                return;
            }
            if (price == 0) {
                textInput.getStringInput();
                return;
            }

            System.out.println("Indtast mængde: ");
            int quantity;
            try{
                quantity = textInput.getIntInput();
            } catch (NumberFormatException e){
                System.out.println("Prøv lige at skriv en tal istedet for. Det kan ikke være så svært :)");
                return;
            }

            if (quantity == 0) {
                return;
            }

            System.out.println("Indtast beskrivelse: ");
            String description = textInput.getStringInput();
            if (description.equals("0")) {
                return;
            }

            System.out.println("Indtast lokation: ");
            String location = textInput.getStringInput();
            if (location.equals("0")) {
                return;
            }

            System.out.println("Indtast lån perioden: ");
            int rentPeriod;
            try{
                rentPeriod = textInput.getIntInput();
            } catch (NumberFormatException e){
                System.out.println("Prøv lige at skriv en tal istedet for. Det kan ikke være så svært :)");
                return;
            }
            if (rentPeriod == 0) {
                return;
            }

            if(isRentProduct) {
                productController.addRentProduct(name, description, price, quantity, barcode, location, rentPeriod);
            } else {
                productController.addSaleProduct(name, description, price, quantity, barcode, location);
            }
            System.out.println("Produktet er blevet oprettet");

        } catch (Exception e){
                System.out.println("Ikke gyldigt input. du skrev: " + e.getMessage());
        }
    }
}
