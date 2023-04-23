package model.customermodel;

import java.util.List;
import java.util.ArrayList;

public class CustomerContainer {
    private static CustomerContainer instance;
    List<Customer> customerContainer;

    private CustomerContainer() {
        customerContainer = new ArrayList<>();
    }

    public static CustomerContainer getInstance() {
        if (instance == null) {
            instance = new CustomerContainer();
        }
        return instance;
    }

    public boolean addBusinessCustomer(String name, String address, String phone, String email, int pincode, String cvr) {
        boolean result = false;
        if(checkIfUniqueCustomer(phone, email, cvr)){
            int customerId = generateCustomerId();
            BusinessCustomer businessCustomer = new BusinessCustomer(name, address, phone, email, customerId, pincode, cvr);
            result = customerContainer.add(businessCustomer);
        }
        return result;
    }

    public boolean addPrivateCustomer(String name, String address, String phone, String email, int pincode, String cpr) {
        boolean result = false;
        if(checkIfUniqueCustomer(phone, email, cpr)) {
            int customerId = generateCustomerId();
            PrivateCustomer privateCustomer = new PrivateCustomer(name, address, phone, email, customerId, pincode, cpr);
            result = customerContainer.add(privateCustomer);
        }
        return result;
    }
    
    public boolean removeCustomer(String phone) {
        boolean found = false;
        for(int i = 0; i < customerContainer.size() && !found; i++) {
            if(customerContainer.get(i).getPhone().equals(phone)) {
                customerContainer.remove(i);
                found = true;
            }
        }
        return found;

    }

    /**
     * If the customerContainer is empty, return 1, otherwise return the last customer's id + 1
     *
     * @return The last customer id in the customerContainer plus 1.
     */
    private int generateCustomerId() {
        if(customerContainer.size() == 0) {
            return 1;
        } else {
            return customerContainer.get(customerContainer.size() - 1).getCustomerId() + 1;
        }
    }

    // Checking if the customer is unique.
    /**
     * "Check if the customer is unique by checking if the phone number, email or unique id is already in use."
     * The function is called from the `createCustomer` function, which is called from the `createCustomer` function in the
     * `CustomerController` class
     *
     * @param phone The phone number of the customer.
     * @param email The email of the customer.
     * @param uniqueId The unique ID of the customer. This can be either a CPR number or a CVR number.
     * @return A boolean value.
     */
    private boolean checkIfUniqueCustomer(String phone, String email, String uniqueId) {
        boolean unique = true;
        for(int i = 0; i < customerContainer.size() && unique ; i++) {
            Customer customer = customerContainer.get(i);
            if (customer.getPhone().equals(phone) || customer.getEmail().equals(email)) {
                unique = false;
            } else if (customer instanceof BusinessCustomer) {
                if (((BusinessCustomer) customer).getCvr().equals(uniqueId)) {
                    unique = false;
                }
            } else if (customer instanceof PrivateCustomer) {
                if (((PrivateCustomer) customer).getCpr().equals(uniqueId)) {
                    unique = false;
                }
            }
        }
        return unique;
    }

    /**
     * If the customer's phone number is found in the customerContainer, return the customer object.
     *
     * @param phone The phone number of the customer.
     * @return A customer object.
     */
    public Customer getCustomer(String phone) {
        boolean found = false;
        Customer customer = null;
        for(int i = 0; i < customerContainer.size() && !found; i++) {
            if(customerContainer.get(i).getPhone().equals(phone)) {
                customer = customerContainer.get(i);
                found = true;
            }
        }
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return customerContainer;
    }

}
