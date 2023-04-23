package controller;

import model.employeemodel.Employee;
import model.employeemodel.EmployeeContainer;

import java.util.List;

// A controller class that handles the employee container.
public class EmployeeController {
    EmployeeContainer employeeContainer;

    public EmployeeController() {
        employeeContainer = EmployeeContainer.getInstance();
    }

    // Adding an employee to the employee container.
    public boolean addEmployee(String name, String username, String password, int role) {
        return employeeContainer.addEmployee(name, username, password, role);
    }


    // Getting the employee container.
    public List<Employee> getEmployees() {
        return employeeContainer.getEmployees();
    }

    // Removing an employee from the employee container.
    public boolean removeEmployee(String username) {
        return employeeContainer.removeEmployee(username);
    }

    /**
     * "Find an employee by username."
     * The function finds an employee by its username.
     *
     * @param username The username of the employee to be found.
     * @return The employee object that matches the username.
     */
    public Employee getEmployee(String username) {
        Employee employee = null;
        for (Employee e : employeeContainer.getEmployees()) {
            if (e.getUsername().equals(username)) {
                employee = e;
            }
        }
        return employee;
    }

    public int login(String username, String password) {
        return employeeContainer.login(username, password);
    }

    /**
     * "Loop through the list of employees and return the role of the employee with the given employeeId."
     *
     * @param employeeId The employee id of the employee you want to get the role of.
     * @return The role of the employee with the given employeeId.
     */
    public int getEmployeeRole(int employeeId) {
        int role = 0;
        List<Employee> employees = employeeContainer.getEmployees();
        for(int i = 0; i < employees.size() && role == 0; i++) {
            Employee e = employees.get(i);
            if (e.getEmployeeId() == employeeId) {
                role = e.getRole();
            }
        }
        return role;
    }

    public boolean updateName(String username, String name) {
        boolean result = false;
        List<Employee> employees = employeeContainer.getEmployees();
        for(Employee employee : employees) {
            if(employee.getUsername().equals(username)) {
                employee.setName(name);
                result = true;
            }
        }
        return result;
    }

    public boolean updateUsername(String username, String newUsername) {
        boolean result = false;
        List<Employee> employees = employeeContainer.getEmployees();
        for(Employee employee : employees) {
            if(employee.getUsername().equals(username)) {
                employee.setUsername(newUsername);
                result = true;
            }
        }
        return result;
    }

    public boolean updatePassword(String username, String password) {
        boolean result = false;
        List<Employee> employees = employeeContainer.getEmployees();
        for(int i = 0; i < employees.size() && !result; i++) {
            Employee employee = employees.get(i);
            if(employee.getUsername().equals(username)) {
                employee.setPassword(password);
                result = true;
            }
        }
        return result;
    }

    public boolean updateRole(String username, int role) {
        boolean result = false;
        List<Employee> employees = employeeContainer.getEmployees();
        for(Employee employee : employees) {
            if(employee.getUsername().equals(username)) {
                result = true;
                if(role < 4 && role > 0) {
                    employee.setRole(role);
                }
                else {
                    System.out.println("Skriv venligst en rolle mellem 1 og 3.");
                }
            }
        }
        return result;
    }
}
