package gui.menus.productmenus;

import controller.ProductController;
import gui.menus.MainMenu;
import model.productmodel.Product;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.List;
import javax.swing.tree.TreePath;

public class ProductMenu {
	private JFrame frame;
	private JTree tree;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfQuantity;
	private JTextField tfBarcode;
	private JTextField tfLocation;
	private final ProductController productController;
	private final int role;
	private JTextField tfDescription;
	private JButton btnConfirmChanges;

	/**
	 * Create the application.
	 */
	public ProductMenu(int role) {
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
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("Produkt Menu");
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
		
		JButton btnCreateProduct = new JButton("Opret produkt");
		btnCreateProduct.addActionListener(e -> {
			frame.dispose();
			new CreateProductMenu(role);
		});
		panel_2.add(btnCreateProduct);
		
		JButton btnUpdateProduct = new JButton("Ændre produkt");
		btnUpdateProduct.addActionListener(e -> {
			TreePath path = tree.getSelectionPath();
			if(path == null || path.getPathCount() != 2) {
				return;
			}

			tfName.setEditable(true);
			tfDescription.setEditable(true);
			tfPrice.setEditable(true);
			tfQuantity.setEditable(true);
			tfLocation.setEditable(true);
			btnConfirmChanges.setVisible(true);
		});
		panel_2.add(btnUpdateProduct);
		
		JButton btnRemoveProduct = new JButton("Fjern produkt");
		btnRemoveProduct.addActionListener(e -> {
			TreePath path = tree.getSelectionPath();
			if(path == null || path.getPathCount() != 2) {
				return;
			}
			int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil fjerne stregkode " + path.getPathComponent(1).toString() + "?", "Bekræft venligst", JOptionPane.YES_NO_OPTION);
			if(response == 0) {
				productController.removeProduct(Long.parseLong(path.getPathComponent(1).toString()));
				frame.dispose();
				new ProductMenu(role);
			}
		});
		panel_2.add(btnRemoveProduct);
		
		btnConfirmChanges = new JButton("Bekræft ændringer");
		btnConfirmChanges.addActionListener(e -> {
			TreePath path = tree.getSelectionPath();
			if(path == null || path.getPathCount() != 2) {
				return;
			}
			Product product = productController.getProduct(Long.parseLong(path.getPathComponent(1).toString()));

			product.setName(tfName.getText());
			product.setDescription(tfDescription.getText());
			product.setPrice(Double.parseDouble(tfPrice.getText()));
			product.setQuantity(Integer.parseInt(tfQuantity.getText()));
			product.setBarcode(Long.parseLong(tfBarcode.getText()));
			product.setLocation(tfLocation.getText());

			tfName.setEditable(false);
			tfDescription.setEditable(false);
			tfPrice.setEditable(false);
			tfQuantity.setEditable(false);
			tfLocation.setEditable(false);
			btnConfirmChanges.setVisible(false);
		});

		btnConfirmChanges.setVisible(false);
		panel_2.add(btnConfirmChanges);
		
		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Produkter");
		List<Product> products = productController.getAllProducts();
		for (Product product : products) {
			DefaultMutableTreeNode productNode = new DefaultMutableTreeNode(product.getBarcode());
			root.add(productNode);
		}

		tree = new JTree(root);
		tree.getSelectionModel().addTreeSelectionListener(e -> {
			TreeNode selectedNode = (TreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode != null && selectedNode.getChildCount() == 0) {
				Product product = productController.getProduct(Long.parseLong(selectedNode.toString()));
				tfName.setText(product.getName());
				tfDescription.setText(product.getDescription());
				tfPrice.setText(String.valueOf(product.getPrice()));
				tfQuantity.setText(String.valueOf(product.getQuantity()));
				tfBarcode.setText(String.valueOf(product.getBarcode()));
				tfLocation.setText(product.getLocation());
			}
		});
		panel_3.add(new JScrollPane(tree));

		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Produkt detaljer");
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
		
		JLabel lblNewLabel_6 = new JLabel("Stregkode");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_6);
		
		tfBarcode = new JTextField();
		tfBarcode.setEditable(false);
		panel_6.add(tfBarcode);
		tfBarcode.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Beskrivelse");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_3);
		
		tfDescription = new JTextField();
		tfDescription.setEditable(false);
		panel_6.add(tfDescription);
		tfDescription.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Pris per stk.");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_4);
		
		tfPrice = new JTextField();
		tfPrice.setEditable(false);
		tfPrice.setColumns(10);
		panel_6.add(tfPrice);
		
		JLabel lblNewLabel_5 = new JLabel("Antal");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_5);
		
		tfQuantity = new JTextField();
		tfQuantity.setEditable(false);
		panel_6.add(tfQuantity);
		tfQuantity.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Lokation");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_6.add(lblNewLabel_7);
		
		tfLocation = new JTextField();
		tfLocation.setEditable(false);
		panel_6.add(tfLocation);
		tfLocation.setColumns(10);
	}
}
