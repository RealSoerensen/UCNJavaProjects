package gui.menus;

import controller.CustomerController;
import gui.menus.ordermenus.CreateOrderMenu;
import model.customermodel.Customer;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;

public class FindCustomerMenu {

	private JFrame frame;
	private JTextField tfPhone;
	private JLabel lblError;
	private final CustomerController customerController;
	private final int role;


	/**
	 * Create the application.
	 */
	public FindCustomerMenu(int role) {
		this.role = role;
		customerController = new CustomerController();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 225, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			frame.dispose();
			new MainMenu(role);
		});
		panel.add(btnBack);
		
		JButton btnFindCustomer = new JButton("Find kunde");
		btnFindCustomer.addActionListener(e -> {
			String phone = tfPhone.getText();
			Customer customer = customerController.getCustomer(phone);
			if (customer != null) {
				new CreateOrderMenu(role, phone);
				frame.dispose();
			} else{
				lblError.setVisible(true);
			}
		});
		panel.add(btnFindCustomer);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Find kunde");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("Indtast kunde tlf.");
		panel_2.add(lblNewLabel_1);
		
		tfPhone = new JTextField();
		panel_2.add(tfPhone);
		tfPhone.setColumns(10);
		
		lblError = new JLabel("Kunden findes ikke");
		lblError.setVisible(false);
		panel_2.add(lblError);
	}

}
