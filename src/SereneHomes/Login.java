package SereneHomes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import proj.InsertUpdateDelete;
import proj.SelectQuery;
import java.sql.*;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField emailtextField;
	private JTextField passwordtextField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() 
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 702, 495);
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
		
		JLabel lblWelcomeToSerenehomes = new JLabel("WELCOME TO SERENEHOMES");
		lblWelcomeToSerenehomes.setToolTipText("");
		lblWelcomeToSerenehomes.setForeground(Color.WHITE);
		lblWelcomeToSerenehomes.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblWelcomeToSerenehomes.setBackground(Color.WHITE);
		lblWelcomeToSerenehomes.setBounds(153, 21, 407, 54);
		contentPane.add(lblWelcomeToSerenehomes);
		
		passwordtextField_1 = new JTextField();
		passwordtextField_1.setBounds(314, 254, 141, 19);
		contentPane.add(passwordtextField_1);
		passwordtextField_1.setColumns(10);
		
		emailtextField = new JTextField();
		emailtextField.setBounds(314, 207, 141, 19);
		contentPane.add(emailtextField);
		emailtextField.setColumns(10);
		button.setIcon(new ImageIcon(Registration.class.getResource("/images/icons8-close-35.png")));
		button.setBounds(647, 10, 35, 35);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(307, 124, 92, 35);
		contentPane.add(lblNewLabel);
		
		JLabel email = new JLabel("Email");
		email.setForeground(new Color(255, 255, 255));
		email.setFont(new Font("Century Gothic", Font.BOLD, 15));
		email.setBounds(248, 208, 46, 13);
		contentPane.add(email);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(new Color(255, 255, 255));
		password_label.setFont(new Font("Century Gothic", Font.BOLD, 15));
		password_label.setBounds(225, 255, 69, 13);
		contentPane.add(password_label);
		
		
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int check = 0;
				
				String eemail = emailtextField.getText();
				String password = passwordtextField_1.getText();
												
				if(eemail.equals("") || password.equals(""))
				{
					check = 1;
					JOptionPane.showMessageDialog(null, "Every Field Is Required");
			    }
				else 
				{
					ResultSet rs = SelectQuery.getData("Select email, usertype from users where email = '"+ eemail +"' and password = '"+password+"'");
					try	
					{
						if(rs.next()) 
						{
						 String retrievedEmail = rs.getString("email");
						 String selectedUserType = rs.getString("usertype");

					        // Comparing retrieved email with input email
					        if (retrievedEmail.equals(eemail)) 
					        {
					        	  // Get the user's ID after registration
			                    String getUserIdQuery = "SELECT userID FROM users WHERE email = '" + eemail + "'";
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
				                            InsertUpdateDelete.setData(insertCurrentUserQuery, "Welcome to SereneHomes!");
		
				                            setVisible(false);	
				                            dispose(); // Close the current frame
				                            new HomeCustomer().setVisible(true);
			                            
			                            }
			                            if(selectedUserType.equals("Property Owner")) 
			                            {	                            	 
				                            String insertCurrentUserQuery = "INSERT INTO curr_user (userID, user_type) VALUES (" + userId + ", '" + selectedUserType + "')";
				                            InsertUpdateDelete.setData(insertCurrentUserQuery, "Welcome to SereneHomes!");
		
				                            setVisible(false);	
				                            dispose(); // Close the current frame
				                            new HomePropertyOwner().setVisible(true);		                            	
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
					        else 
					        {
					            JOptionPane.showMessageDialog(null, "Invalid email or password");
					        }
					    } 
						else
						{
					        JOptionPane.showMessageDialog(null, "NO data read from db");
					    }
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, ex);
					}
					
				}
			}
			
		});
 
		btnNewButton.setBounds(318, 311, 81, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/images/login3.jpg")));
		lblNewLabel_2.setBounds(0, 0, 692, 461);
		contentPane.add(lblNewLabel_2);
	
	}
}
