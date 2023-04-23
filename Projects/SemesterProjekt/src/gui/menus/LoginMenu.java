package gui.menus;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.EmployeeController;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginMenu {

	private JFrame frame;
	private JTextField tfUsername;
	private final EmployeeController employeeController;
	private JPasswordField pfPassword;


	public void run() {
		try {
			LoginMenu window = new LoginMenu();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public LoginMenu() {
		employeeController = new EmployeeController();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Login Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Brugernavn:");
		lblNewLabel_1.setBounds(10, 10, 99, 15);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Adgangskode:");
		lblNewLabel_2.setBounds(10, 33, 99, 15);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Forkert brugernavn/adgangskode");
		lblNewLabel_3.setBounds(10, 56, 214, 15);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setVisible(false);

		tfUsername = new JTextField();
		tfUsername.setBounds(119, 7, 96, 19);
		panel_1.add(tfUsername);
		tfUsername.setColumns(10);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(119, 31, 96, 19);
		panel_1.add(pfPassword);

		JButton btnExit = new JButton("Luk");
		btnExit.addActionListener(e -> frame.dispose());
		panel.add(btnExit);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
			String username = tfUsername.getText();
			char[] password = pfPassword.getPassword();
			String passwordString = new String(password);
			boolean loggedIn = login(username, passwordString);
			if(!loggedIn) {
				lblNewLabel_3.setVisible(true);
			}
		});
		panel.add(btnLogin);
		frame.getRootPane().setDefaultButton(btnLogin);
	}

	private boolean login(String username, String password) {
		boolean result = false;
		int employeeId = employeeController.login(username, password);
		int role = employeeController.getEmployeeRole(employeeId);
		if (employeeId != 0) {
			frame.dispose();
			new MainMenu(role);
			result = true;
		}
		return result;
	}
}
