package gui.menus.employeemenus;

import controller.EmployeeController;
import model.employeemodel.Employee;
import gui.menus.MainMenu;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.List;

public class EmployeeMenu {
    private final int role;
    private JFrame frame;
    private JTree tree;
    private JTextField tfName;
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfRole;
    private JButton btnConfirmChanges;
    private final EmployeeController employeeController;

    public EmployeeMenu(int role) {
        this.role = role;
        employeeController = new EmployeeController();
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel label = new JLabel("Medarbejder Menu");
        label.setFont(new Font("Tahoma", Font.PLAIN, 28));
        panel.add(label);

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

        JButton btnCreateProduct = new JButton("Tilføj Medarbejder");
        btnCreateProduct.addActionListener(e -> {
            frame.dispose();
            new CreateEmployeeMenu(role);
        });
        panel_2.add(btnCreateProduct);

        JButton btnRemoveEmployee = new JButton("Fjern Medarbejder");
        btnRemoveEmployee.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if(path == null || path.getPathCount() != 2) {
                return;
            }
            Employee employee = employeeController.getEmployee((path.getPathComponent(1).toString()));
            if(employee.getRole() == 3) {
                JOptionPane.showMessageDialog(null, "Du kan ikke fjerne en direktør\nfra systemet.");
                return;
            }
            int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil fjerne " + employee.getName() + "?", "Bekræft venligst", JOptionPane.YES_NO_OPTION);
            if(response == 0) {
                employeeController.removeEmployee(employee.getUsername());
                frame.dispose();
                new EmployeeMenu(role);
            }
        });
        panel_2.add(btnRemoveEmployee);

        JButton btnUpdateEmployee = new JButton("Ændre Medarbejder");
        btnUpdateEmployee.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if(path == null || path.getPathCount() != 2) {
                return;
            }
            Employee employee = employeeController.getEmployee((path.getPathComponent(1).toString()));
            int numberOfRoleThree = 0;
            if(employee.getRole() == 3) {
                int i = 0;
                while (i < employeeController.getEmployees().size()) {
                    if ((employeeController.getEmployees().get(i).getRole() == 3)) {
                        numberOfRoleThree++;
                    }
                    i++;
                }
            }

            tfName.setEditable(true);
            tfUsername.setEditable(true);
            tfPassword.setEditable(true);
            if(numberOfRoleThree != 1) {
                tfRole.setEditable(true);
            }
            btnConfirmChanges.setVisible(true);
        });
        panel_2.add(btnUpdateEmployee);

        btnConfirmChanges = new JButton("Bekræft ændringer");
        btnConfirmChanges.addActionListener(e -> {
            TreePath path = tree.getSelectionPath();
            if (path == null || path.getPathCount() != 2) {
                return;
            }
            Employee employee = employeeController.getEmployee((path.getPathComponent(1).toString()));


            if((Integer.parseInt(tfRole.getText()) < 1 || (Integer.parseInt(tfRole.getText()) > 3))) {
                JOptionPane.showMessageDialog(null, "Rolle ikke opdateret\nSkriv venligst et tal mellem 1 og 3");
            } else {
                employee.setName(tfName.getText());
                employee.setUsername(tfUsername.getText());
                employee.setPassword(tfPassword.getText());
                employee.setRole(Integer.parseInt(tfRole.getText()));
                tfName.setEditable(false);
                tfUsername.setEditable(false);
                tfPassword.setEditable(false);
                tfRole.setEditable(false);
                btnConfirmChanges.setVisible(false);
            }
        });

        btnConfirmChanges.setVisible(false);
        panel_2.add(btnConfirmChanges);

        JPanel panel_3 = new JPanel();
        panel_4.add(panel_3);
        panel_3.setLayout(new BorderLayout(0, 0));

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Medarbejdere");
        List<Employee> employees = employeeController.getEmployees();
        for (Employee element : employees) {
            DefaultMutableTreeNode employeeNode = new DefaultMutableTreeNode(element.getUsername());
            root.add(employeeNode);
        }

        tree = new JTree(root);
        tree.getSelectionModel().addTreeSelectionListener(e -> {
            TreeNode selectedNode = (TreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null && selectedNode.getChildCount() == 0) {
                Employee employee = employeeController.getEmployee(selectedNode.toString());
                tfName.setText(employee.getName());
                tfUsername.setText(employee.getUsername());
                tfPassword.setText(employee.getPassword());
                String stringRole = "" + employee.getRole();
                tfRole.setText(stringRole);
            }
        });
        panel_3.add(new JScrollPane(tree));

        JPanel panel_5 = new JPanel();
        panel_4.add(panel_5);
        panel_5.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel_1 = new JLabel("Medarbejder detaljer");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_5.add(lblNewLabel_1, BorderLayout.NORTH);

        JPanel panel_6 = new JPanel();
        panel_5.add(panel_6);
        panel_6.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblNewLabel_2 = new JLabel("Navn");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        panel_6.add(lblNewLabel_2);

        tfName = new JTextField();
        tfName.setEditable(false);
        tfName.setColumns(10);
        panel_6.add(tfName);

        JLabel lblNewLabel_6 = new JLabel("Brugernavn");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        panel_6.add(lblNewLabel_6);

        tfUsername = new JTextField();
        tfUsername.setEditable(false);
        panel_6.add(tfUsername);
        tfUsername.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Adgangskode");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        panel_6.add(lblNewLabel_3);

        tfPassword = new JTextField();
        tfPassword.setEditable(false);
        panel_6.add(tfPassword);
        tfPassword.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Rolle");
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        panel_6.add(lblNewLabel_4);

        tfRole = new JTextField();
        tfRole.setEditable(false);
        tfRole.setColumns(10);
        panel_6.add(tfRole);
    }
}
