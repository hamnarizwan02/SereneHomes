package SereneHomes;
import java.util.*;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import proj.InsertUpdateDelete;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JOptionPane;

import businessLogicLayer.Customer;
import proj.SelectQuery;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField name_textField;
	private JTextField email_textField_1;
	private JTextField phone_numtextField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?","Select", JOptionPane.YES_NO_OPTION);
				if(exit==0) 
				{
					System.exit(0);
				}
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				 setVisible(false);
		         new Login().setVisible(true);
			}
		});
		
		JLabel lblWelcomeToSerenehomes = new JLabel("WELCOME TO SERENEHOMES");
		lblWelcomeToSerenehomes.setToolTipText("");
		lblWelcomeToSerenehomes.setForeground(Color.WHITE);
		lblWelcomeToSerenehomes.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblWelcomeToSerenehomes.setBackground(Color.WHITE);
		lblWelcomeToSerenehomes.setBounds(144, 10, 407, 54);
		contentPane.add(lblWelcomeToSerenehomes);
		btnLogin.setBounds(383, 364, 69, 21);
		contentPane.add(btnLogin);
		button.setIcon(new ImageIcon(Registration.class.getResource("/images/icons8-close-35.png")));
		button.setBounds(647, 10, 35, 35);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("REGISTER");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(271, 96, 117, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(203, 174, 46, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(208, 213, 41, 13);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(180, 253, 69, 13);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Phone no");
		lblNewLabel_1_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_1_1.setBounds(175, 292, 74, 13);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		name_textField = new JTextField();
		name_textField.setBounds(274, 173, 176, 19);
		contentPane.add(name_textField);
		name_textField.setColumns(10);
		
		email_textField_1 = new JTextField();
		email_textField_1.setColumns(10);
		email_textField_1.setBounds(274, 212, 176, 19);
		contentPane.add(email_textField_1);
		
		phone_numtextField_2 = new JTextField();
		phone_numtextField_2.setColumns(10);
		phone_numtextField_2.setBounds(273, 291, 177, 19);
		contentPane.add(phone_numtextField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(274, 252, 176, 19);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("User type");
		lblNewLabel_1_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1_2.setFont(new Font("Century Gothic", Font.BOLD, 15));
		lblNewLabel_1_1_1_2.setBounds(180, 323, 69, 23);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		JComboBox usertype = new JComboBox();
		usertype.setModel(new DefaultComboBoxModel(new String[] {"Customer", "Property Owner"}));
		usertype.setMaximumRowCount(2);
		usertype.setFont(new Font("Century Gothic", Font.PLAIN, 10));
		usertype.setBounds(274, 326, 176, 21);
		contentPane.add(usertype);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String name = name_textField.getText();
				String email = email_textField_1.getText();
				String password = passwordField.getText();
				String phonenum = phone_numtextField_2.getText();
				String selectedUserType = usertype.getSelectedItem().toString();


			 if (name.equals("") || email.equals("") || password.equals("") || phonenum.equals(""))
			 {
	                JOptionPane.showMessageDialog(null, "Every Field Is Required");
	         } 
			 else 
			 {       
	                String query = "Select email, password from users where email ='" + email + "'and  password = '"+password+"' ";
	                ResultSet rs = SelectQuery.getData(query);
	                try 
	                {
						if(rs.next()) 
						{
							JOptionPane.showMessageDialog(null, "Customer is already logged in!");
							
						    setVisible(false);
						    new Login().setVisible(true);
						}				
						else 
						{
		                    String Query = "insert into users (name, email, password, phone_number, usertype) "
		                            + "values ('" + name + "','" + email + "','" + password + "','" + phonenum + "','"
		                            + selectedUserType + "')";
		                    InsertUpdateDelete.setData(Query, "Registered Successfully!");                  
		                    
		                    // Get the user's ID after registration
		                    String getUserIdQuery = "SELECT userID FROM users WHERE email = '" + email + "'";
		                    ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);

		                    try
		                    {
		                        if (userIdResultSet.next())
		                        {
		                            int userId = userIdResultSet.getInt("userID");
		                            
		                            if(selectedUserType.equals("Customer"))
		                            {
		                            	// Insert the user into the curr_user table 
			                            String insertCurrentUserQuery = "INSERT INTO curr_user (userID, user_type) VALUES (" + userId + ", '" + selectedUserType + "')";
			                            InsertUpdateDelete.setData(insertCurrentUserQuery, "Current customer Inserted Successfully!");
	
			                            setVisible(false);	
			                            dispose(); // Close the current frame
			                            new HomeCustomer().setVisible(true);
		                            
		                            }
		                            if(selectedUserType.equals("Property Owner")) 
		                            {
		                            	// Insert the user into the curr_user table 
			                            String insertCurrentUserQuery = "INSERT INTO curr_user (userID, user_type) VALUES (" + userId + ", '" + selectedUserType + "')";
			                            InsertUpdateDelete.setData(insertCurrentUserQuery, "Current property owner Inserted Successfully!");
	
			                            setVisible(false);	
			                            dispose(); // Close the current frame
			                            new HomeCustomer().setVisible(true);		                            	
		                            }
		                        } 
		                        else
		                        {
		                            JOptionPane.showMessageDialog(null, "Error retrieving user ID.");
		                        }
		                    } 
		                    catch (SQLException e1) 
		                    {
		                        e1.printStackTrace();
		                    }
		                   
						}
	                } 
	                catch (SQLException e1) 
	                {
				
	                	e1.printStackTrace();
	                }
	            }
			}
	
		});
		btnNewButton.setBounds(271, 364, 69, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Registration.class.getResource("/images/sign3.jpg")));
		lblNewLabel_2.setBounds(0, 0, 692, 453);
		contentPane.add(lblNewLabel_2);
	}
}
