package gui.menus.ordermenus;

import controller.OrderController;
import controller.ProductController;
import model.ordermodel.Order;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.awt.FlowLayout;

public class CreateOrderMenu {
	private JFrame frame;
	private final String phone;
	private final Order order;
	private final int role;
	private final OrderController orderController;
	private final ProductController productController;
	private JTextField tfBarcode;
	private JTextField tfQuantity;
	private JTree tree;


	/**
	 * Create the application.
	 */
	public CreateOrderMenu(int role, String phone) {
		this.phone = phone;
		this.role = role;
		productController = new ProductController();
		orderController = new OrderController();
		Random random = new Random();
		long orderNo = random.nextInt(1000000);
		List<Order> orders = orderController.getOrders();
		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getOrderNo() == orderNo) {
				orderNo = random.nextInt(1000000);
				i = 0;
			}
		}
		order = new Order(orderNo, new Date());
		order.addCustomer(phone);
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Opret Order");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			frame.dispose();
			new OrderMenu(role);
		});
		panel.add(btnBack);
		
		JButton btnCreateOrder = new JButton("Opret order");
		btnCreateOrder.addActionListener(e -> {
			order.addCustomer(phone);
			orderController.addOrder(order);
			frame.dispose();
			new OrderMenu(role);
		});
		panel.add(btnCreateOrder);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 2, 0, 30));
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("Stregkode");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(lblNewLabel_2);
		
		tfBarcode = new JTextField();
		tfBarcode.setHorizontalAlignment(SwingConstants.CENTER);
		tfBarcode.setColumns(10);
		panel_4.add(tfBarcode);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5);
		
		JLabel lblNewLabel_3 = new JLabel("Antal     ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_3);
		
		tfQuantity = new JTextField();
		tfQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		tfQuantity.setColumns(10);
		panel_5.add(tfQuantity);
		
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Produkter");
		tree = new JTree(root);
		tree.setShowsRootHandles(true);

		JButton btnNewButton = new JButton("Tilføj til order");
		btnNewButton.addActionListener(e -> {
			long barcode;
			int quantity;
			try{
				barcode = Long.parseLong(tfBarcode.getText());
				quantity = Integer.parseInt(tfQuantity.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Stregkode og antal skal være tal");
				return;
			}
			int productQuantity = productController.getProduct(barcode).getQuantity();
			if(quantity >= productQuantity) {
				JOptionPane.showMessageDialog(null, "Der er ikke nok af dette produkt på lager");
				return;
			} else if(quantity <= 0) {
				JOptionPane.showMessageDialog(null, "Antal skal være større end 0");
				return;
			}
			order.addProduct(barcode, quantity);
			tfBarcode.setText("");
			tfQuantity.setText("");
			DefaultMutableTreeNode productNode = new DefaultMutableTreeNode(barcode + " x" + quantity + "stk.");
			root.add(productNode);
			tree.updateUI();

		});
		panel_6.add(btnNewButton);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		panel_3.add(new JScrollPane(tree));
	}
}
