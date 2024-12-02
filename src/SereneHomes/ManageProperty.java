package SereneHomes;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import proj.ConnectionProvider;
import proj.SelectQuery;
import javax.swing.JTable;

public class ManageProperty extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Component textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageProperty frame = new ManageProperty();
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
	public ManageProperty()
	{		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
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
		
		button.setIcon(new ImageIcon(Registration.class.getResource("/images/icons8-close-35.png")));
		button.setBounds(610, 10, 35, 35);
		contentPane.add(button);
		
		JLabel lblManageProperty = new JLabel("MANAGE PROPERTY");
		lblManageProperty.setBounds(10, 10, 310, 50);
		lblManageProperty.setToolTipText("");
		lblManageProperty.setForeground(new Color(11, 0, 0));
		lblManageProperty.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblManageProperty.setBackground(Color.WHITE);
		contentPane.add(lblManageProperty);
		
		JLabel lblSelectPropertyTo = new JLabel("Select property to edit:");
		lblSelectPropertyTo.setBounds(10, 69, 267, 23);
		lblSelectPropertyTo.setForeground(new Color(11, 0, 0));
		lblSelectPropertyTo.setFont(new Font("Century Gothic", Font.BOLD, 22));
		contentPane.add(lblSelectPropertyTo);
		
		JLabel lblEnterPropertyId = new JLabel("Enter property id:");
		lblEnterPropertyId.setBounds(10, 102, 175, 23);
		lblEnterPropertyId.setForeground(new Color(11, 0, 0));
		lblEnterPropertyId.setFont(new Font("Century Gothic", Font.BOLD, 20));
		contentPane.add(lblEnterPropertyId);
		
		textField = new JTextField();
		textField.setBounds(195, 108, 116, 19);
		JTextField jTextField = new JTextField();
		jTextField.setColumns(10);
		contentPane.add(textField);
		
		JButton btnNewButton_1 = new JButton("DELETE PROPERTY");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String propertyId = textField.getText();
				 String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Property Owner' ORDER BY id DESC LIMIT 1;";
			        ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
			        int ownerId = -1;  //intialize

			        try {
			            if (userIdResultSet.next()) {
			                ownerId = userIdResultSet.getInt("userID");
                        	System.out.println("Are you sure you want to delete your property ?");

			                try (Connection connection = ConnectionProvider.getConnection()) {
			                	String updateQuery = "DELETE FROM Property WHERE property_id = ? AND owner_id = ?";
			                	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			                	    preparedStatement.setString(1, propertyId);
			                	    preparedStatement.setInt(2, ownerId);
			                	    int rowsAffected = preparedStatement.executeUpdate();
			                        if (rowsAffected > 0) {
			                            System.out.println("Property deleted successfully.");
			                            JOptionPane.showMessageDialog(null, "Property deleted successfully");
			                        } else {
			                        	JOptionPane.showMessageDialog(null, "No property found with the given property ID or the owner ID does not match.");
			                        	System.out.println("No property found with the given property ID or the owner ID does not match.");
			                        }
			                    }
			                } catch (SQLException e1) {
			                    e1.printStackTrace();
			                }
			            }
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			    }
		});
		btnNewButton_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton_1.setBounds(487, 422, 158, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblEditLocation = new JLabel("Edit location:");
		lblEditLocation.setForeground(new Color(11, 0, 0));
		lblEditLocation.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblEditLocation.setBounds(10, 194, 158, 23);
		contentPane.add(lblEditLocation);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 200, 197, 19);
		contentPane.add(textField_1);
		
		JLabel lblEditPrice = new JLabel("Edit price:");
		lblEditPrice.setForeground(new Color(11, 0, 0));
		lblEditPrice.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblEditPrice.setBounds(10, 227, 158, 23);
		contentPane.add(lblEditPrice);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(150, 227, 197, 19);
		contentPane.add(textField_2);
		
		JLabel lblEditPropertyName = new JLabel("Edit property name:");
		lblEditPropertyName.setForeground(new Color(11, 0, 0));
		lblEditPropertyName.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblEditPropertyName.setBounds(10, 161, 216, 23);
		contentPane.add(lblEditPropertyName);
		
		textField_3 = new JTextField();
		((JTextField) textField_3).setColumns(10);
		textField_3.setBounds(226, 167, 116, 19);
		contentPane.add(textField_3);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String propertyId = textField.getText();
		        String newPropertyName = ((AbstractButton) textField_3).getText();
		        String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Property Owner' ORDER BY id DESC LIMIT 1;";
		        ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
		        int ownerId = -1; //intialize

		        try {
		            if (userIdResultSet.next()) {
		                ownerId = userIdResultSet.getInt("userID");

		                try (Connection connection = ConnectionProvider.getConnection()) {
		                    String updateQuery = "UPDATE Property SET property_name = ? WHERE property_id = ? AND owner_id = ?";
		                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
		                        preparedStatement.setString(1, newPropertyName);
		                        preparedStatement.setString(2, propertyId);
		                        preparedStatement.setInt(3, ownerId);
		                        int rowsAffected = preparedStatement.executeUpdate();

		                        if (rowsAffected > 0) {
		                            System.out.println("Property name updated successfully.");
		                        	JOptionPane.showMessageDialog(null, "Property name updated successfully.");

		                        } else {
		                            System.out.println("No property found with the given property ID or the owner ID does not match.");
		                        	JOptionPane.showMessageDialog(null, "No property found with the given property ID or the owner ID does not match.");

		                        }
		                    }
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                }
		            }
		        } catch (SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnUpdate.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnUpdate.setBackground(Color.WHITE);
		btnUpdate.setBounds(370, 166, 85, 21);
		contentPane.add(btnUpdate);
		
		JButton btnUpdate_1 = new JButton("UPDATE");
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String propertyId = textField.getText();
				String newlocation = textField_1.getText();

				 String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Property Owner' ORDER BY id DESC LIMIT 1;";
			        ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
			        int ownerId = -1;  

			        try {
			            if (userIdResultSet.next()) {
			                ownerId = userIdResultSet.getInt("userID");

			                try (Connection connection = ConnectionProvider.getConnection()) {
			                    String updateQuery = "UPDATE Property SET location = ? WHERE property_id = ? AND owner_id = ?";
			                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			                        preparedStatement.setString(1, newlocation);
			                        preparedStatement.setString(2, propertyId);
			                        preparedStatement.setInt(3, ownerId);
			                        int rowsAffected = preparedStatement.executeUpdate();

			                        if (rowsAffected > 0) {
			                            System.out.println("Property location updated successfully.");
			                        	JOptionPane.showMessageDialog(null, "Property location updated successfully.");

			                        } else {
			                        	JOptionPane.showMessageDialog(null, "No property found with the given property ID or the owner ID does not match.");

			                        	System.out.println("No property found with the given property ID or the owner ID does not match.");
			                        }
			                    }
			                } catch (SQLException e1) {
			                    e1.printStackTrace();
			                }
			            }
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			    }

		});
		btnUpdate_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnUpdate_1.setBackground(Color.WHITE);
		btnUpdate_1.setBounds(370, 199, 85, 21);
		contentPane.add(btnUpdate_1);
		
		JButton btnUpdate_2 = new JButton("UPDATE");
		btnUpdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String propertyId = textField.getText();
				String newprice = textField_2.getText();

				 String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Property Owner' ORDER BY id DESC LIMIT 1;";
			        ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
			        int ownerId = -1;  

			        try {
			            if (userIdResultSet.next()) {
			                ownerId = userIdResultSet.getInt("userID");

			                try (Connection connection = ConnectionProvider.getConnection()) {
			                    String updateQuery = "UPDATE Property SET price_per_night = ? WHERE property_id = ? AND owner_id = ?";
			                    try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			                        preparedStatement.setString(1, newprice);
			                        preparedStatement.setString(2, propertyId);
			                        preparedStatement.setInt(3, ownerId);
			                        int rowsAffected = preparedStatement.executeUpdate();

			                        if (rowsAffected > 0) {
			                            System.out.println("Property price updated successfully.");
			                        	JOptionPane.showMessageDialog(null, "Property price updated successfully.");

			                        } else {
			                            System.out.println("No property found with the given property ID or the owner ID does not match.");
			                        }
			                    }
			                } catch (SQLException e1) {
			                    e1.printStackTrace();
			                }
			            }
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			    }

		});
		btnUpdate_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnUpdate_2.setBackground(Color.WHITE);
		btnUpdate_2.setBounds(370, 232, 85, 21);
		contentPane.add(btnUpdate_2);
		
		JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setForeground(new Color(11, 0, 0));
        lblAvailability.setFont(new Font("Century Gothic", Font.BOLD, 20));
        lblAvailability.setBounds(10, 260, 158, 23);
        contentPane.add(lblAvailability);
	   	 Choice choice = new Choice();
		    choice.setBounds(150, 265, 147, 18);
		    choice.add("Available");
	     choice.add("Not Available");
	     contentPane.add(choice);
		
		JButton btnUpdate_2_1 = new JButton("UPDATE");
		btnUpdate_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String propertyId = textField.getText();
				boolean newAvailability = choice.getSelectedItem().equals("Available");
                String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Property Owner' ORDER BY id DESC LIMIT 1;";
                ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
                int ownerId = -1;

                try {
                    if (userIdResultSet.next()) {
                        ownerId = userIdResultSet.getInt("userID");

                        try (Connection connection = ConnectionProvider.getConnection()) {
                        	String updateQuery = "UPDATE Property SET available = ? WHERE property_id = ? AND owner_id = ?";
                        	try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                        	    preparedStatement.setBoolean(1, newAvailability);
                        	    preparedStatement.setString(2, propertyId);
                        	    preparedStatement.setInt(3, ownerId);
                        	    int rowsAffected = preparedStatement.executeUpdate();

                        	    if (rowsAffected > 0) {
                        	        System.out.println("Property availability updated successfully.");
                        	        JOptionPane.showMessageDialog(null, "Property availability updated successfully.");
                        	    } else {
                        	        System.out.println("No property found with the given property ID or the owner ID does not match.");
                        	        JOptionPane.showMessageDialog(null, "No property found with the given property ID or the owner ID does not match.");
                        	    }
                        	} catch (SQLException e1) {
                        	    e1.printStackTrace();
                        	}
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
			
			}});
		btnUpdate_2_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnUpdate_2_1.setBackground(Color.WHITE);
		btnUpdate_2_1.setBounds(370, 265, 85, 21);
		contentPane.add(btnUpdate_2_1);
		
		
		JButton btnNewButton_1_1 = new JButton("GO BACK");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new HomePropertyOwner().setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton_1_1.setBounds(24, 425, 158, 23);
		contentPane.add(btnNewButton_1_1);
				
		table = new JTable();
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(24, 308, 621, 86);
	    contentPane.add(scrollPane);
		
	

		
		
	}
}
