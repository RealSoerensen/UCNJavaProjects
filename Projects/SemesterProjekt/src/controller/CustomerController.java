package controller;

import model.customermodel.BusinessCustomer;
import model.customermodel.Customer;
import model.customermodel.CustomerContainer;
import model.customermodel.PrivateCustomer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    CustomerContainer customerContainer;

    public CustomerController() {
        customerContainer = CustomerContainer.getInstance();
    }

    /**
     * Adds a business customer to the customer container.
     *
     * @param name The name of the customer
     * @param address The address of the customer
     * @param phone String
     * @param email The email of the customer.
     * @param pincode The pincode of the customer's address.
     * @param cvr The CVR number of the business customer.
     * @return A boolean value.
     */
    public boolean addBusinessCustomer(String name, String address, String phone, String email, int pincode, String cvr) {
        return customerContainer.addBusinessCustomer(name, address, phone, email, pincode, cvr);
    }

    /**
     * Adds a private customer to the customer container.
     *
     * @param name The name of the customer
     * @param address The address of the customer
     * @param phone String
     * @param email String
     * @param pincode The pincode of the customer's address.
     * @param cpr CPR number
     * @return A boolean value.
     */
    public boolean addPrivateCustomer(String name, String address, String phone, String email, int pincode, String cpr) {
        return customerContainer.addPrivateCustomer(name, address, phone, email, pincode, cpr);
    }

    // It removes a customer from the customer container.
    public boolean removeCustomer(String phone) {
        return customerContainer.removeCustomer(phone);
    }

    // It returns a customer from the customer container.
    public Customer getCustomer(String phone) {
        return customerContainer.getCustomer(phone);
    }

    public List<Customer> getAllCustomers() {
        return customerContainer.getAllCustomers();
    }

    /**
     * Get all customers, then loop through them until you find the one with the matching phone number, then return the
     * customer ID.
     *
     * @param phone The phone number of the customer
     * @return The customerId of the customer with the given phone number.
     */
    public int getCustomerId(String phone) {
        int customerId = -1;
        List<Customer> customers = getAllCustomers();
        for(int i = 0; i < customers.size() && customerId == -1; i++) {
            if(customers.get(i).getPhone().equals(phone)) {
                customerId = customers.get(i).getCustomerId();
            }
        }
        return customerId;
    }

    /**
     * This function returns a list of strings that contain the information of all customers.
     *
     * @return A list of strings containing the information of all customers.
     */
    public List<String> printAllCustomers() {
        List<String> customerInfo = new ArrayList<>();
        for(Customer customer : customerContainer.getAllCustomers()) {
            customerInfo.add(customer.toString());
        }
        return customerInfo;
    }

    /**
     * "Update the name of the customer with the given customerId."
     *
     * @param customerId The id of the customer to update
     * @param name The name of the method.
     * @return A boolean value.
     */
    public boolean updateName(int customerId, String name) {
        boolean result = false;
        List<Customer> customers = customerContainer.getAllCustomers();
        for(Customer customer : customers) {
            if(customer.getCustomerId() == customerId) {
                customer.setName(name);
                result = true;
            }
        }
        return result;
    }

    /**
     * "Update the address of a customer with a given customerId."
     *
     * @param customerId The id of the customer to update
     * @param address The new address of the customer.
     * @return A boolean value.
     */
    public boolean updateAddress(int customerId, String address) {
        boolean result = false;
        List<Customer> customers = customerContainer.getAllCustomers();
        for(Customer customer : customers) {
            if(customer.getCustomerId() == customerId) {
                customer.setAddress(address);
                result = true;
            }
        }
        return result;
    }

    /**
     * "Update the phone number of a customer with a given customer ID."
     * The function is called updatePhone, and it takes two parameters: customerId and phone. The function returns a
     * boolean value, which is true if the update was successful, and false if it was not
     *
     * @param customerId The id of the customer to update
     * @param phone The new phone number for the customer.
     * @return A boolean value.
     */
    public boolean updatePhone(int customerId, String phone) {
        boolean result = false;
        List<Customer> customers = customerContainer.getAllCustomers();
        for(Customer customer : customers) {
            if(customer.getCustomerId() == customerId) {
                customer.setPhone(phone);
                result = true;
            }
        }
        return result;
    }

    /**
     * "If the customer exists, update the email."
     *
     * @param customerId The id of the customer to update
     * @param email The new email address for the customer.
     * @return A boolean value.
     */
    public boolean updateEmail(int customerId, String email) {
        boolean result = false;
        List<Customer> customers = customerContainer.getAllCustomers();
        for(Customer customer : customers) {
            if(customer.getCustomerId() == customerId) {
                customer.setEmail(email);
                result = true;
            }
        }
        return result;
    }

    /**
     * "If the customer exists, update the pin."
     *
     * @param customerId The customer's ID
     * @param pin The new pin code that the customer wants to change to.
     * @return A boolean value.
     */
    public boolean updatePin(int customerId, int pin) {
        boolean result = false;
        List<Customer> customers = customerContainer.getAllCustomers();
        for(Customer customer : customers) {
            if(customer.getCustomerId() == customerId) {
                customer.setPincode(pin);
                result = true;
            }
        }
        return result;
    }

    public Customer getCustomerById(int customerId) {
        List<Customer> customers = customerContainer.getAllCustomers();
        Customer finalCustomer = null;
        for(int i = 0; i < customers.size() && finalCustomer == null; i++) {
            if(customers.get(i).getCustomerId() == customerId) {
                finalCustomer = customers.get(i);
            }
        }
        return finalCustomer;
    }

    public boolean updateUniqueIdentifier(int customerId, String uniqueIdentifier) {
        boolean result = false;
        Customer customer = getCustomerById(customerId);
        if(customer instanceof BusinessCustomer) {
            ((BusinessCustomer) customer).setCvr(uniqueIdentifier);
            result = true;
        } else if(customer instanceof PrivateCustomer) {
            ((PrivateCustomer) customer).setCpr(uniqueIdentifier);
            result = true;
        }
        return result;
    }

    public boolean updateCredit(int customerId, double credit) {
        boolean result = false;
        Customer customer = getCustomerById(customerId);
        try{
            ((BusinessCustomer)customer).setCredit(credit);
            result = true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean updateDiscount(int customerId, double discount) {
        boolean result = false;
        Customer customer = getCustomerById(customerId);
        try{
            ((BusinessCustomer) customer).setDiscountRate(discount);
            result = true;
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
