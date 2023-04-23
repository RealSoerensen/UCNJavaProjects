package model.employeemodel;

import java.util.ArrayList;
import java.util.List;

public class EmployeeContainer {

    private final List<Employee> employeeContainer;

    private static EmployeeContainer instance;

    private EmployeeContainer() {
        employeeContainer = new ArrayList<>();
    }

    /**
     * If the instance is null, create a new instance of the class and return it. If the instance is not null, return the
     * existing instance
     *
     * @return The instance of the EmployeeContainer class.
     */
    public static EmployeeContainer getInstance() {
        if (instance == null) {
            instance = new EmployeeContainer();
        }
        return instance;
    }

    /**
     * > This function adds an employee to the employeeContainer if the username is not already taken
     *
     * @param name The name of the employee
     * @param username The username of the employee
     * @param password String
     * @param role 1 = employee, 2 = manager, 3 = admin
     * @return A boolean value.
     */
    public boolean addEmployee(String name, String username, String password, int role) {
        boolean result = false;
        if (checkIfUsernameUnique(username)) {
            int employeeId = generateEmployeeId();
            Employee employee = new Employee(name, employeeId, username, password, role);
            employeeContainer.add(employee);
            result = true;
        }
        return result;
    }

    private int generateEmployeeId(){
        if (employeeContainer.size() == 0){
            return 1;
        }
        return employeeContainer.get(employeeContainer.size() - 1).getEmployeeId() + 1;
    }

    /**
     * > This function checks if the username is unique
     *
     * @param username The username of the employee
     * @return A boolean value.
     */
    private boolean checkIfUsernameUnique(String username) {
        boolean unique = true;
        for (Employee employee : employeeContainer) {
            if (employee.getUsername().equals(username)) {
                unique = false;
                break;
            }
        }
        return unique;
    }

    /**
     * Get the employee container.
     *
     * @return The employee container.
     */
    public List<Employee> getEmployees() {
        return employeeContainer;
    }

    public boolean removeEmployee(String username) {
        boolean result = false;
        for(int i = 0; i < employeeContainer.size() && !result; i++) {
            Employee e = employeeContainer.get(i);
            if (e.getUsername().equals(username)) {
                employeeContainer.remove(e);
                result = true;
            }
        }
        return result;
    }

    public int login(String username, String password) {
        int employeeId = 0;
        for(int i = 0; i < employeeContainer.size() && employeeId == 0; i++) {
            Employee e = employeeContainer.get(i);
            if (e.getUsername().equals(username) && e.getPassword().equals(password)) {
                employeeId = e.getEmployeeId();
            }
        }
        return employeeId;
    }
}
