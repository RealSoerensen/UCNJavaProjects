package gui.menus.productmenus;


import controller.ProductController;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class CreateProductMenu {

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfDescription;
	private JTextField tfPrice;
	private JTextField tfQuantity;
	private JTextField tfLocation;
	private JTextField tfDaysofRent;
	private JRadioButton rdbtnSale;
	private JRadioButton rdbtnRent;
	private final ProductController productController;
	private final int role;
	private JTextField tfBarcode;

	/**
	 * Create the application.
	 */
	public CreateProductMenu(int role) {
		this.role = role;
		productController = new ProductController();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Opret produkt");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			frame.dispose();
			new ProductMenu(role);
		});
		panel_1.add(btnBack);
		
		JButton btnCreateProduct = new JButton("Opret produkt");
		btnCreateProduct.addActionListener(e -> {
			String name;
			long barcode;
			String description;
			double price;
			int quantity;
			String location;
			try {
				name = tfName.getText();
				barcode = Long.parseLong(tfBarcode.getText());
				description = tfDescription.getText();
				price = Double.parseDouble(tfPrice.getText());
				quantity = Integer.parseInt(tfQuantity.getText());
				location = tfLocation.getText();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Ugyldig indtastning");
				return;
			}
			if(rdbtnSale.isSelected()) {
				productController.addSaleProduct(name, description, price, quantity, barcode, location);
			} else if(rdbtnRent.isSelected()) {
				try{
					int daysOfRent = Integer.parseInt(tfDaysofRent.getText());
					productController.addRentProduct(name, description, price, quantity, barcode, location, daysOfRent);
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Ugyldig indtastning");
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Produkt oprettet");
			frame.dispose();
			new ProductMenu(role);
		});
		panel_1.add(btnCreateProduct);
		
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		frame.getContentPane().add(panel_3, BorderLayout.EAST);
		
		JPanel panel_4 = new JPanel();
		frame.getContentPane().add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));

		ButtonGroup bg = new ButtonGroup();
		rdbtnSale = new JRadioButton("Salg");
		rdbtnSale.setSelected(true);
		panel_4.add(rdbtnSale);
		
		rdbtnRent = new JRadioButton("Lån");
		panel_4.add(rdbtnRent);

		bg.add(rdbtnSale);
		bg.add(rdbtnRent);
		
		JLabel lblNewLabel_1 = new JLabel("Navn");
		panel_4.add(lblNewLabel_1);
		
		tfName = new JTextField();
		panel_4.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Stregkode");
		panel_4.add(lblNewLabel_7);
		
		tfBarcode = new JTextField();
		panel_4.add(tfBarcode);
		tfBarcode.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Beskrivelse");
		panel_4.add(lblNewLabel_2);
		
		tfDescription = new JTextField();
		panel_4.add(tfDescription);
		tfDescription.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Pris");
		panel_4.add(lblNewLabel_3);
		
		tfPrice = new JTextField();
		panel_4.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Antal");
		panel_4.add(lblNewLabel_4);
		
		tfQuantity = new JTextField();
		panel_4.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Lokation");
		panel_4.add(lblNewLabel_5);
		
		tfLocation = new JTextField();
		panel_4.add(tfLocation);
		tfLocation.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Antal dage lån");
		lblNewLabel_6.setVisible(false);
		panel_4.add(lblNewLabel_6);
		
		tfDaysofRent = new JTextField();
		tfDaysofRent.setVisible(false);
		panel_4.add(tfDaysofRent);
		tfDaysofRent.setColumns(10);

		rdbtnSale.addActionListener(e -> {
			lblNewLabel_6.setVisible(false);
			tfDaysofRent.setVisible(false);
		});

		rdbtnRent.addActionListener(e -> {
			lblNewLabel_6.setVisible(true);
			tfDaysofRent.setVisible(true);
		});
	}

}
