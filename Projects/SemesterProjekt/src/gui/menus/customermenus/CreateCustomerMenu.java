package gui.menus.customermenus;

import controller.CustomerController;

import javax.swing.*;
import java.awt.*;

public class CreateCustomerMenu {
    private JFrame frame;
    private JTextField tfName;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JTextField tfEmail;
    private JTextField tfPin;
    private JTextField tfCPRCVR;
    private JRadioButton rdbtnPrivateCustomer;
    private JRadioButton rdbtnBusinessCustomer;
    private final int role;
    private final CustomerController customerController;

    public CreateCustomerMenu(int role) {
        this.role = role;
        customerController = new CustomerController();
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.setResizable(false);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel("Opret Kunde");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnBack = new JButton("Tilbage");
        btnBack.addActionListener(e -> {
            frame.dispose();
            new CustomerMenu(role);
        });

        JButton btnCreateCustomer = new JButton("Opret Kunde");
        btnCreateCustomer.addActionListener(e -> {
            String name;
            String phone;
            String address;
            String email;
            int pin;
            String cprCvr;
            try{
                name = tfName.getText();
                phone = tfPhone.getText();
                address = tfAddress.getText();
                email = tfEmail.getText();
                pin = Integer.parseInt(tfPin.getText());
                cprCvr = tfCPRCVR.getText();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ugyldig input");
                return;
            }

            if (rdbtnPrivateCustomer.isSelected()) {
                customerController.addPrivateCustomer(name, phone, address, email, pin, cprCvr);
            } else if (rdbtnBusinessCustomer.isSelected()) {
                customerController.addBusinessCustomer(name, phone, address, email, pin, cprCvr);
            } else {
                JOptionPane.showMessageDialog(null, "Vælg venligst om kunden er en privat eller en virksomhed");
                return;
            }
            JOptionPane.showMessageDialog(null, "Kunde oprettet");
            frame.dispose();
            new CustomerMenu(role);
        });
        panel_1.add(btnBack);
        panel_1.add(btnCreateCustomer);

        JPanel panel_2 = new JPanel();
        frame.getContentPane().add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new GridLayout(0, 2, 0, 0));

        JPanel panel_3 = new JPanel();
        panel_2.add(panel_3);
        panel_3.setLayout(new GridLayout(0, 1, 0, 0));

        ButtonGroup btnGroup = new ButtonGroup();

        rdbtnPrivateCustomer = new JRadioButton("Privat Kunde");
        rdbtnPrivateCustomer.setSelected(true);
        panel_3.add(rdbtnPrivateCustomer);
        btnGroup.add(rdbtnPrivateCustomer);

        rdbtnBusinessCustomer = new JRadioButton("Erhverskunde");
        panel_3.add(rdbtnBusinessCustomer);
        btnGroup.add(rdbtnBusinessCustomer);

        JLabel lblName = new JLabel("Navn");
        panel_3.add(lblName);

        JLabel lblPhone = new JLabel("Telefon");
        panel_3.add(lblPhone);

        JLabel lblAdress = new JLabel("Adresse");
        panel_3.add(lblAdress);

        JLabel lblEmail = new JLabel("Email");
        panel_3.add(lblEmail);

        JLabel lblPin = new JLabel("Pin");
        panel_3.add(lblPin);

        JLabel lblCPRCVR = new JLabel("CPR/CVR");
        panel_3.add(lblCPRCVR);

        JPanel panel_5 = new JPanel();
        panel_2.add(panel_5);
        panel_5.setLayout(new GridLayout(0, 1, 0, 0));

        // empty label to make the layout look better
        JLabel lblNewLabel_1 = new JLabel("");
        panel_5.add(lblNewLabel_1);
        JLabel lblNewLabel_2 = new JLabel("");
        panel_5.add(lblNewLabel_2);

        tfName = new JTextField();
        panel_5.add(tfName);
        tfName.setColumns(10);

        tfPhone = new JTextField();
        panel_5.add(tfPhone);
        tfPhone.setColumns(10);

        tfAddress = new JTextField();
        panel_5.add(tfAddress);
        tfAddress.setColumns(10);

        tfEmail = new JTextField();
        panel_5.add(tfEmail);
        tfEmail.setColumns(10);

        tfPin = new JTextField();
        panel_5.add(tfPin);
        tfPin.setColumns(10);

        tfCPRCVR = new JTextField();
        panel_5.add(tfCPRCVR);
        tfCPRCVR.setColumns(10);
    }
}
