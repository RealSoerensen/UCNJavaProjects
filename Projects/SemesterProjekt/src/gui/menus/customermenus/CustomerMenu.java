package gui.menus.customermenus;

import controller.CustomerController;
import gui.menus.MainMenu;
import gui.menus.FindCustomerMenu;
import gui.menus.ordermenus.OrderMenu;
import gui.menus.productmenus.CreateProductMenu;
import gui.menus.productmenus.ProductMenu;
import model.customermodel.Customer;
import model.productmodel.Product;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CustomerMenu {
    private JFrame frame;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfPhone;
    private JTextField tfEmail;
    private JTextField tfCustomerId;
    private JTextField tfPin;
    private JButton btnConfirmChanges;
    private JTree tree;
    private int role;
    private final CustomerController customerController;

    public CustomerMenu(int role) {
        this.role = role;
        customerController = new CustomerController();
        initialize();
        frame.setVisible(true);
    }

    public void initialize(){
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.setResizable(false);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblNewLabel = new JLabel("Kunde Menu");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton btnNewButton = new JButton("Tilbage");
        btnNewButton.addActionListener(e -> {
            frame.dispose();
            new MainMenu(role);
        });
        panel_1.add(btnNewButton);

        JPanel panel_4 = new JPanel();
        frame.getContentPane().add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new GridLayout(0, 3, 0, 0));

        JPanel panel_2 = new JPanel();
        panel_4.add(panel_2);

        JButton btnCreateProduct = new JButton("Opret Kunde");
        btnCreateProduct.addActionListener(e -> {
            frame.dispose();
            new CreateCustomerMenu(role);
        });
        panel_2.add(btnCreateProduct);

        JButton btnRemoveProduct = new JButton("Fjern Kunde");
        btnRemoveProduct.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if(path == null || path.getPathCount() != 2) {
                return;
            }
            Customer customer = customerController.getCustomerById(Integer.parseInt(tfCustomerId.getText()));
            int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil fjerne "
                    + customer.getName() + "?", "Bekræft venligst", JOptionPane.YES_NO_OPTION);
            if(response == 0) {
                customerController.removeCustomer(customer.getPhone());
                frame.dispose();
                new CustomerMenu(role);
            }
        });
        panel_2.add(btnRemoveProduct);

        JButton btnUpdateProduct = new JButton("Ændre Kunde");
        btnUpdateProduct.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if(path == null || path.getPathCount() != 2) {
                return;
            }

            tfName.setEditable(true);
            tfAddress.setEditable(true);
            tfPhone.setEditable(true);
            tfEmail.setEditable(true);
            tfPin.setEditable(true);
            btnConfirmChanges.setVisible(true);
        });
        panel_2.add(btnUpdateProduct);


        btnConfirmChanges = new JButton("Bekræft ændringer");
        btnConfirmChanges.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if(path == null || path.getPathCount() != 2) {
                return;
            }
            Customer customer = customerController.getCustomerById(Integer.parseInt(tfCustomerId.getText()));

            customer.setName(tfName.getText());
            customer.setAddress(tfAddress.getText());
            customer.setPhone(tfPhone.getText());
            customer.setEmail(tfEmail.getText());
            customer.setPincode(Integer.parseInt(tfPin.getText()));

            tfName.setEditable(false);
            tfAddress.setEditable(false);
            tfPhone.setEditable(false);
            tfEmail.setEditable(false);
            tfPin.setEditable(false);
            btnConfirmChanges.setVisible(false);
        });
        btnConfirmChanges.setVisible(false);
        panel_2.add(btnConfirmChanges);


        JPanel panel_3 = new JPanel();
        panel_4.add(panel_3);
        panel_3.setLayout(new BorderLayout(0, 0));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Kunder");
        List<Customer> customers = customerController.getAllCustomers();
        for (Customer customer : customers) {
            DefaultMutableTreeNode customerNode = new DefaultMutableTreeNode(customer.getCustomerId());
            root.add(customerNode);
        }

        tree = new JTree(root);
        tree.getSelectionModel().addTreeSelectionListener(e -> {
            String[] pathArray = e.getPath().toString().split(", ");
            if (pathArray.length == 2) {
                String customerId = pathArray[1].substring(0, pathArray[1].length() - 1);
                Customer customer = customerController.getCustomerById(Integer.parseInt(customerId));
                tfName.setText(customer.getName());
                tfAddress.setText(customer.getAddress());
                tfPhone.setText(customer.getPhone());
                tfEmail.setText(customer.getEmail());
                tfCustomerId.setText(String.valueOf(customer.getCustomerId()));
                tfPin.setText(String.valueOf(customer.getPincode()));
            }
        });
        panel_3.add(new JScrollPane(tree));

        JPanel panel_5 = new JPanel();
        panel_4.add(panel_5);
        panel_5.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_1 = new JLabel("Kunde detaljer");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_5.add(lblNewLabel_1, BorderLayout.NORTH);

        JPanel panel_6 = new JPanel();
        panel_5.add(panel_6);
        panel_6.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblNewLabel_2 = new JLabel("Kunde nr.");
        panel_6.add(lblNewLabel_2);

        tfCustomerId = new JTextField();
        tfCustomerId.setEditable(false);
        panel_6.add(tfCustomerId);
        tfCustomerId.setColumns(15);

        JLabel lblNewLabel_3 = new JLabel("Navn");
        panel_6.add(lblNewLabel_3);

        tfName = new JTextField();
        tfName.setEditable(false);
        panel_6.add(tfName);
        tfName.setColumns(15);

        JLabel lblNewLabel_4 = new JLabel("Adresse");
        panel_6.add(lblNewLabel_4);

        tfAddress = new JTextField();
        tfAddress.setEditable(false);
        panel_6.add(tfAddress);
        tfAddress.setColumns(15);

        JLabel lblNewLabel_5 = new JLabel("tlf. nummer");
        panel_6.add(lblNewLabel_5);

        tfPhone = new JTextField();
        tfPhone.setEditable(false);
        panel_6.add(tfPhone);
        tfPhone.setColumns(15);

        JLabel lblNewLabel_6 = new JLabel("Email");
        panel_6.add(lblNewLabel_6);

        tfEmail = new JTextField();
        tfEmail.setEditable(false);
        panel_6.add(tfEmail);
        tfEmail.setColumns(15);

        JLabel lblNewLabel_8 = new JLabel("Pinkode");
        panel_6.add(lblNewLabel_8);

        tfPin = new JTextField();
        tfPin.setEditable(false);
        panel_6.add(tfPin);
        tfPin.setColumns(15);
    }
}
