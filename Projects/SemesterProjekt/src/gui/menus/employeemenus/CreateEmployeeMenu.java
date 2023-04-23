package gui.menus.employeemenus;

import controller.EmployeeController;
import gui.menus.MainMenu;

import javax.swing.*;
import java.awt.*;

public class CreateEmployeeMenu {
    private JFrame frame;
    private JTextField tfName;
    private JTextField tfPassword;
    private JTextField tfConfirmPassword;
    private JTextField tfUsername;
    private JTextField tfTempRole;
    private final EmployeeController employeeController;
    private final int role;

    public CreateEmployeeMenu(int role) {
        this.role = role;
        employeeController = new EmployeeController();
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 240);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Tilføj Medarbejder");
        label.setFont(new Font("Tahoma", Font.PLAIN, 28));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        JButton buttonBack = new JButton("Tilbage");
        buttonBack.addActionListener(e -> {
            frame.dispose();
            new MainMenu(role);
        });
        panel.add(buttonBack);

        JButton buttonAddEmployee = new JButton("Tilføj Medarbejder");
        buttonAddEmployee.addActionListener(e -> {
            String name;
            String username;
            String password;
            String confirmPassword;
            int tempRole;
            try {
                name = tfName.getText();
                username = tfUsername.getText();
                password = tfPassword.getText();
                confirmPassword = tfConfirmPassword.getText();
                tempRole = Integer.parseInt(tfTempRole.getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Ugyldig indstastning");
                return;
            }
            if(!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Adgangskoder matcher ikke");
            } else if(role > 3 || role < 1) {
                JOptionPane.showMessageDialog(null, "Ugyldig rolle");
            } else {
                employeeController.addEmployee(name, username, password, tempRole);
                JOptionPane.showMessageDialog(null, "Medarbejder tilføjet");
                frame.dispose();
                new MainMenu(role);
            }
        });
        panel.add(buttonAddEmployee);

        JPanel panel_2 = new JPanel();
        frame.getContentPane().add(panel_2, BorderLayout.WEST);

        JPanel panel_3 = new JPanel();
        frame.getContentPane().add(panel_3, BorderLayout.EAST);

        JPanel panel_4 = new JPanel();
        frame.getContentPane().add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel label_2 = new JLabel("Navn");
        panel_4.add(label_2);

        tfName = new JTextField();
        panel_4.add(tfName);
        tfName.setColumns(10);

        JLabel label_3 = new JLabel("Brugernavn");
        panel_4.add(label_3);

        tfUsername = new JTextField();
        panel_4.add(tfUsername);
        tfUsername.setColumns(10);

        JLabel label_4 = new JLabel("Adgangskode");
        panel_4.add(label_4);

        tfPassword = new JTextField();
        panel_4.add(tfPassword);
        tfPassword.setColumns(10);

        JLabel label_6 = new JLabel("Bekræft Adgangskode");
        panel_4.add(label_6);

        tfConfirmPassword = new JTextField();
        panel_4.add(tfConfirmPassword);
        tfConfirmPassword.setColumns(10);

        JLabel label_5 = new JLabel("Rolle");
        panel_4.add(label_5);

        tfTempRole = new JTextField();
        panel_4.add(tfTempRole);
        tfTempRole.setColumns(10);
    }
}
