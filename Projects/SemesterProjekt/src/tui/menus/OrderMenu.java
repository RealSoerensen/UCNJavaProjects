package tui.menus;

import controller.OrderController;
import controller.ProductController;
import model.customermodel.BusinessCustomer;
import model.customermodel.Customer;
import model.ordermodel.Order;
import model.productmodel.Product;
import tui.TextInput;
import tui.TextOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OrderMenu {
    private final TextInput textInput;
    private final TextOptions textOptions;
    private final OrderController orderController;
    private final ProductController productController;

    public OrderMenu() {
        textInput = new TextInput();
        textOptions = new TextOptions("Order Menu", "Tilbage");
        orderController = new OrderController();
        productController = new ProductController();
    }

    private void addOptions(){
        textOptions.addOption("Tilføj Ordre");
        textOptions.addOption("Fjern Ordre");
        textOptions.addOption("Vis Ordre");
        textOptions.addOption("Vis alle Ordre");
    }

    public void show() {
        addOptions();
        while (true){
            textOptions.prompt();
            int choice;
            try{
                choice = textInput.getIntInput();
            } catch(NumberFormatException e){
                System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
                continue;
            }
            switch (choice) {
                case 1 -> addOrder();
                case 2 -> removeOrder();
                case 3 -> showOrder();
                case 4 -> showAllOrders();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }

    private void showAllOrders() {
        System.out.println("********** Vis alle Ordre **********");
        List<Order> orders = orderController.getOrders();
        if (orders.size() == 0) {
            System.out.println("Ingen ordre at vise");
        }
        //Loop through all orders
        for (Order order : orders) {
            long orderNo = order.getOrderNo();
            Customer customer = orderController.getOrder(orderNo).getCustomer();
            System.out.println("Kunde:\n" + customer);
            System.out.println("Date: " + order.getDate());
            System.out.println();
            System.out.println("***Ordre Nummer: " + orderNo + "***");
            if(order.getProducts().size() != 0){
                formatOrderOutput(order);
            } else{
                System.out.println("Denne ordre har ingen tilknyttede produkter");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Tryk på enter for at komme tilbage");
        textInput.getStringInput();
    }

    private void showOrder() {
        System.out.println("********** Vis Ordre **********");
        System.out.println("Ordre Nummer: ");
        int orderNo = getOrderNo();
        if(orderNo == -1){
            System.out.println("Ordre findes ikke");
            return;
        }
        if(orderController.getOrders().size() < orderNo){
            System.out.println("Ordre nummeret findes ikke");
            return;
        }

        System.out.println("Ordre for ordre nummer " + orderNo);
        Order order = orderController.getOrder(orderNo);

        Customer customer = order.getCustomer();
        if(customer != null){
            System.out.println(customer);
        }

        if(order.getProducts().size() != 0){
            formatOrderOutput(order);
        } else{
            System.out.println("Denne ordre har ingen tilknyttede produkter");
        }

        System.out.println("\nTryk på enter for at komme tilbage");
        textInput.getStringInput();
    }

    private void addOrder(){
        System.out.println("********** Tilføj Ordre **********");
        orderController.addOrder(new Date());
        addCustomerToOrder();
    }

    private void removeOrder(){
        System.out.println("********** Fjern Ordre **********");
        System.out.println("Indtast Ordre Nummer: ");
        int orderNo = getOrderNo();
        if (orderNo == -1) {
            System.out.println("Ordre findes ikke");
            return;
        }
        if(orderController.removeOrder(orderNo)){
            System.out.println("Ordre fjernet");
        } else{
            System.out.println("Ordre kunne ikke fjernes");
        }
    }

    private void addCustomerToOrder() {
        long orderNo = orderController.getOrderSize();
        System.out.println("Indskriv kundes telefonnummer: ");
        String phone = textInput.getStringInput();
        if(orderController.getOrder(orderNo).addCustomer(phone)){
            System.out.println("Kunde tilføjet til ordre");
        } else{
            System.out.println("Kunde kunne ikke tilføjes til ordre");
        }
        if(orderController.getOrder(orderNo).getCustomer() != null) {
            addProductToOrder();
        }
        else {
            System.out.println("Der er ikke en kunde med det telefonnummer.");
            System.out.println("Vil du gerne prøve igen?");
            if(yesNoPrompt() == 1) {
                addCustomerToOrder();
            }
            else {
                System.out.println("Ordren blev ikke oprettet.");
                orderController.removeOrder(orderNo);
            }

        }
    }

    private void addProductToOrder() {
        long orderNo = orderController.getOrderSize();
        System.out.println("Indskriv produktets stregkode: ");
        long barcode;
        try {
            barcode = textInput.getLongInput();
        } catch (NumberFormatException e) {
            System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
            return;
        }
        if (productController.getProduct(barcode) == null) {
            System.out.println("Der er ikke et produkt med denne stregkode.");
            addProductPrompt();
            return;
        }

        System.out.println("Indskriv mængden af det produkt: ");
        int quantity;
        try {
            quantity = textInput.getIntInput();
        } catch (NumberFormatException e) {
            System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
            return;
        }
        Product product = productController.getProduct(barcode);
        if(product == null) {
            System.out.println("Der er ikke et produkt med denne stregkode.");
            addProductPrompt();
            return;
        }
        if(quantity <= product.getQuantity()) {
            if (orderController.getOrder(orderNo).addProduct(barcode, quantity)) {
                product.setQuantity(product.getQuantity() - quantity);
                System.out.println("Produktet blev tilføjet til ordren.");
            } else {
                System.out.println("Produkt kunne ikke tilføjes til ordre");
            }

        } else {
            System.out.println("Der er ikke nok af det produkt på lager.");
        }
        addProductPrompt();
    }

    private void addProductPrompt() {
        System.out.println("Vil du gerne tilføje endnu et produkt?");
        int choice = yesNoPrompt();
        switch (choice) {
            case 1 -> addProductToOrder();
            case 2 -> addDiscountPromt();
            default -> System.out.println("Invalid input");
        }
    }

    private void formatOrderOutput(Order order){
        //Looping through all products associated with the current order
        HashMap<Long, Integer> productlist = new HashMap<>();
        for(int i = 0; i < order.getProducts().size(); i++) {
            Product product = order.getProducts().get(i);
            long barcode = product.getBarcode();
            if(productlist.containsKey(barcode)){
                productlist.put(barcode, productlist.get(barcode) + 1);
            } else {
                productlist.put(barcode, 1);
            }
        }
        double totalPrice = 0;
        for (Long barcode : productlist.keySet()) {
            Product product = productController.getProduct(barcode);
            System.out.println(product.getName());
            System.out.println("Antal: " + productlist.get(barcode));
            double price = product.getPrice();
            System.out.println("Pris per stk.: " + price + " kr.");
            totalPrice += price * productlist.get(barcode);
        }

        System.out.println("\nTotal pris: " + totalPrice + " kr.");

        double orderDiscount = order.getDiscount();
        if(order.getCustomer() instanceof BusinessCustomer businessCustomer) {
            double businessDiscount = businessCustomer.getDiscountRate();
            if (orderDiscount > 0) {
                //Checks if the customer is a business customer, and if a discount is applied. If both are true, two discounts will be applied to the total price.
                double newTotal = calculateDiscount(totalPrice, orderDiscount);
                newTotal = calculateDiscount(newTotal, businessDiscount);
                System.out.println("Total pris med business discount(" + businessDiscount + "%) og normal discount("
                        + orderDiscount  + "%): " + newTotal + " kr.");
            } else {
                //Checks if the customer is a business customer without a discount. If they are they will get their normal business discount rate.
                System.out.println("Total pris med business discount(" + businessDiscount + "%): " + calculateDiscount(totalPrice, orderDiscount) + " kr.");
            }
        } else if(orderDiscount > 0) {
            //Checks if a discount is applied. If it there is then a percentage will be deducted from the total price.
            System.out.println("Discount: " + orderDiscount + "%.");
            System.out.println("Total pris med discount(" + orderDiscount + "%): " + calculateDiscount(totalPrice, orderDiscount) + " kr.");
        }
    }

    private double calculateDiscount(double totalPrice, double discount) {
        return ((totalPrice / 100) * (100 - discount));
    }

    private void addDiscountPromt() {
        System.out.println("Vil du gerne give kunden en discount?");
        int choice = yesNoPrompt();
        switch (choice) {
            case 1 -> addDiscountToOrder();
            case 2 -> System.out.println("Ordren blev oprettet.");
            default -> System.out.println("Invalid input");
        }
    }

    private void addDiscountToOrder() {
        long orderNo = orderController.getOrderSize();
        int discountPercentage = 0;
        System.out.println("Skriv en procent mellem 0 og 100.");
        try {
            discountPercentage = textInput.getIntInput();
        } catch (NumberFormatException e) {
            System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
            return;
        }
        if(discountPercentage < 0 || discountPercentage > 100) {
            System.out.println("Skriv venligst en procent mellem 0 og 100.");
            addDiscountToOrder();
        } else {
            orderController.getOrder(orderNo).setDiscount(discountPercentage);
            System.out.println("\nPrisen blev reduceret med " + orderController.getOrder(orderNo).getDiscount() + "%.");
        }
    }

    private int yesNoPrompt() {
        System.out.println("[1] Ja");
        System.out.println("[2] Nej");
        int choice;
        try {
            choice = textInput.getIntInput();
        } catch (NumberFormatException e) {
            System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
            return 0;
        }
        return choice;
    }

    private int getOrderNo() {
        int orderNo = -1;
        try {
            orderNo = textInput.getIntInput();
        } catch (NumberFormatException e){
            System.out.println("Skriv venligst et tal. Du skrev: " + e.getMessage());
        }

        if(orderNo < 1 || orderNo > orderController.getOrders().size()){
            System.out.println("Invalid input");
        }
        return orderNo;
    }
}
