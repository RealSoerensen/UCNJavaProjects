package gui.menus.ordermenus;

import controller.OrderController;
import controller.ProductController;
import gui.menus.FindCustomerMenu;
import gui.menus.MainMenu;
import model.ordermodel.Order;
import model.productmodel.Product;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class OrderMenu {

	private JFrame frame;
	private final OrderController orderController;
	private final ProductController productController;
	private JTextField tfCustomerNo;
	private JTextField tfOrderDate;
	private JTextField tfTotalPrice;
	private final int role;

	/**
	 * Create the application.
	 */
	public OrderMenu(int role) {
		this.role = role;
		orderController = new OrderController();
		productController = new ProductController();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Order Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBack = new JButton("Tilbage");
		btnBack.addActionListener(e -> {
			frame.dispose();
			new MainMenu(role);
		});
		panel.add(btnBack);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.CENTER);
		
		JButton btnAddOrder = new JButton("Tilføj Order");
		btnAddOrder.addActionListener(e ->
		{
			frame.dispose();
			new FindCustomerMenu(role);
		});

		panel_5.add(btnAddOrder);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Orders");

		List<Order> orders = orderController.getOrders();
		for (Order order : orders) {
			DefaultMutableTreeNode orderNode = new DefaultMutableTreeNode(order.getOrderNo());
			for(Product product : order.getProducts()) {
				DefaultMutableTreeNode productNode = new DefaultMutableTreeNode(product.getName());
				orderNode.add(productNode);
			}
			root.add(orderNode);
		}

		JTree tree = new JTree(root);
		tree.getSelectionModel().addTreeSelectionListener(e -> {
			String[] pathArray = e.getPath().toString().split(", ");
			if (pathArray.length == 2) {
				String orderNo = pathArray[1].substring(0, pathArray[1].length() - 1);
				Order order = orderController.getOrder(Long.parseLong(orderNo));
				tfCustomerNo.setText(String.valueOf(order.getCustomer().getCustomerId()));
				Date date = order.getDate();
				tfOrderDate.setText(date.getDate() + "/" + date.getMonth() + "/" + date.getYear());
				tfTotalPrice.setText(String.valueOf(getTotalPrice(order)));
			}
		});
		panel_1.add(new JScrollPane(tree));

		JButton btnRemoveOrder = new JButton("Fjern Order");
		btnRemoveOrder.addActionListener(e -> {
			TreePath path = tree.getSelectionPath();
			if (path == null) {
				return;
			}
			String[] pathArray = path.toString().split(", ");
			if (pathArray.length == 2) {
				String orderNo = pathArray[1].substring(0, pathArray[1].length() - 1);
				int response = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil fjerne ordren?", "Bekræft venligst", JOptionPane.YES_NO_OPTION);
				if(response == 0) {
					orderController.removeOrder(Long.parseLong(orderNo));
					frame.dispose();
					new OrderMenu(role);
				}
			}
		});

		panel_5.add(btnRemoveOrder);
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Order details");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_4.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("Kunde nr.");
		panel_3.add(lblNewLabel_2);
		
		tfCustomerNo = new JTextField();
		tfCustomerNo.setEditable(false);
		panel_3.add(tfCustomerNo);
		tfCustomerNo.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Order dato");
		panel_3.add(lblNewLabel_3);
		
		tfOrderDate = new JTextField();
		tfOrderDate.setEditable(false);
		panel_3.add(tfOrderDate);
		tfOrderDate.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Total pris");
		panel_3.add(lblNewLabel_4);
		
		tfTotalPrice = new JTextField();
		tfTotalPrice.setEditable(false);
		panel_3.add(tfTotalPrice);
		tfTotalPrice.setColumns(10);
	}

	private double getTotalPrice(Order order){
		HashMap<Long, Integer> productlist = new HashMap<>();
		for(int i = 0; i < order.getProducts().size(); i++) {
			Product product = order.getProducts().get(i);
			long barcode = product.getBarcode();
			if(productlist.containsKey(barcode)){
				productlist.put(barcode, productlist.get(barcode) + 1);
			} else {
				productlist.put(barcode, 1);
			}
		}
		double totalPrice = 0;
		for (Long barcode : productlist.keySet()) {
			Product product = productController.getProduct(barcode);
			double price = product.getPrice();
			totalPrice += price * productlist.get(barcode);
		}
		return totalPrice;
	}
}
