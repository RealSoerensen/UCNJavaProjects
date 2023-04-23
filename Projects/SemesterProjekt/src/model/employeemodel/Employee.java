package model.employeemodel;

public class Employee {
    private String name;
    private int employeeId;
    private String username;
    private String password;
    private int role;

    public Employee(String name, int employeeId, String username, String password, int role) {
        this.name = name;
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Navn: " + name +
                "\nMedarbejder ID: " + employeeId +
                "\nBrugernavn: " + username +
                "\nAdgangskode: " + password +
                "\nStilling: " + role;
    }
}
