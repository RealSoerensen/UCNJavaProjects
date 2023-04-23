package gui.menus;


import gui.menus.employeemenus.EmployeeMenu;
import gui.menus.ordermenus.OrderMenu;
import gui.menus.customermenus.CustomerMenu;
import gui.menus.productmenus.ProductMenu;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class MainMenu {

	private JFrame frame;
	private final int role;
	/**
	 * Create the application.
	 */
	public MainMenu(int role) {
		this.role = role;
		initialize();
		frame.setVisible(true);
	}

	private int getRole() {
		return role;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 260, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Main menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnLogout = new JButton("Log ud");
		btnLogout.addActionListener(e -> {
			frame.dispose();
			LoginMenu loginMenu = new LoginMenu();
			loginMenu.run();
		});
		panel.add(btnLogout);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setHgap(25);
		frame.getContentPane().add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setHgap(25);
		frame.getContentPane().add(panel_2, BorderLayout.EAST);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCustomerMenu = new JButton("Kunde Menu");
		panel_3.add(btnCustomerMenu);
		btnCustomerMenu.addActionListener(e -> {
			frame.dispose();
			new CustomerMenu(role);
		});
		
		JButton btnOrderMenu = new JButton("Ordre Menu");
		panel_3.add(btnOrderMenu);
		btnOrderMenu.addActionListener(e -> {
			frame.dispose();
			new OrderMenu(role);
		});
		
		JButton btnProductMenu;
		if(getRole() > 1) {
			btnProductMenu = new JButton("Produkt Menu");
			panel_3.add(btnProductMenu);
			btnProductMenu.addActionListener(e -> {
				frame.dispose();
				new ProductMenu(role);
			});
		}

		JButton btnEmployeeMenu;
		if(getRole() > 2) {
			btnEmployeeMenu = new JButton("Medarbejder Menu");
			panel_3.add(btnEmployeeMenu);
			btnEmployeeMenu.addActionListener(e -> {
				frame.dispose();
				new EmployeeMenu(role);
			});
		}
	}
}
