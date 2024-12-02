package SereneHomes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogicLayer.BookingRepository;
import businessLogicLayer.Property;
import proj.SelectQuery;

public class ManageBooking extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageBooking frame = new ManageBooking();
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
	public ManageBooking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 699);
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
		button.setBounds(790, 10, 35, 35);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("MANAGE BOOKING");
		lblNewLabel.setForeground(new Color(11, 0, 0));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel.setBounds(20, 10, 295, 50);
		contentPane.add(lblNewLabel);
		
		JLabel opt = new JLabel("Bookings:");
		opt.setForeground(new Color(11, 0, 0));
		opt.setFont(new Font("Century Gothic", Font.BOLD, 18));
		opt.setBounds(31, 81, 102, 23);
		contentPane.add(opt);
		
		table_1 = new JTable();
		table_1.setBounds(67, 257, 321, -100);
		contentPane.add(table_1);
		
		JScrollPane scrollPane = new JScrollPane(table_1);
	    scrollPane.setBounds(41, 114, 742, 99);
	    contentPane.add(scrollPane);
	    
	    JButton btnNewButton_2 = new JButton("SHOW BOOKING");
	    btnNewButton_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{    		
	    		DefaultTableModel model = new DefaultTableModel();

	            model.addColumn("Booking ID");	           
	            model.addColumn("Property ID");
	            model.addColumn("Property name");
	            model.addColumn("Location");
	            model.addColumn("Price per night");
	            model.addColumn("Start date availability");
	            model.addColumn("End date availability");
	            model.addColumn("Availability");
	            
	            String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Customer' ORDER BY id DESC LIMIT 1;";
                ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
                try
                {
                    if (userIdResultSet.next())
                    {
                         int userId1 = userIdResultSet.getInt("userID"); 		        	
                         
			            String query = "SELECT * FROM Booking b "
			            		+ "INNER JOIN Property p ON b.property_id = p.property_id where customer_id = '"+ userId1 +"' ";

			            ResultSet resultSet = SelectQuery.getData(query);
		
			            try
			            {
			                while (resultSet.next()) 
			                {
			                    model.addRow(new Object[]{
			                            resultSet.getInt("booking_id"),
			                            resultSet.getInt("property_id"),
			                            resultSet.getString("property_name"),
			                            resultSet.getString("location"),
			                            resultSet.getDouble("price_per_night"),
			                            resultSet.getDate("start_date_availability"),
			                            resultSet.getDate("end_date_availability"),
			                            resultSet.getBoolean("available")
			                    });
			                }
			            } catch (SQLException e1) {
			                e1.printStackTrace();
			            }
                    }
                }
                catch (SQLException e12) {
	                e12.printStackTrace();
	            }

	            table_1.setModel(model);
	    	}
	    });
	    btnNewButton_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnNewButton_2.setBounds(48, 237, 143, 23);
	    contentPane.add(btnNewButton_2);
	    
	    JButton btnNewButton_3 = new JButton("GO BACK");
	    btnNewButton_3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		 setVisible(false);	
                 dispose(); // Close the current frame
                 new HomeCustomer().setVisible(true);
	    	}
	    });
	    btnNewButton_3.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnNewButton_3.setBounds(20, 631, 102, 21);
	    contentPane.add(btnNewButton_3);
	    
	    JLabel lblNewLabel_1 = new JLabel("UPDATE BOOKING");
	    lblNewLabel_1.setToolTipText("");
	    lblNewLabel_1.setForeground(new Color(11, 0, 0));
	    lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
	    lblNewLabel_1.setBackground(Color.WHITE);
	    lblNewLabel_1.setBounds(31, 290, 233, 50);
	    contentPane.add(lblNewLabel_1);
	    
	    JLabel lblEnterPropertyId = new JLabel("Enter property id:");
	    lblEnterPropertyId.setForeground(new Color(11, 0, 0));
	    lblEnterPropertyId.setFont(new Font("Century Gothic", Font.BOLD, 20));
	    lblEnterPropertyId.setBounds(41, 350, 175, 23);
	    contentPane.add(lblEnterPropertyId);
	    
	    textField = new JTextField();
	    textField.setBounds(219, 350, 120, 19);
	    contentPane.add(textField);
	    textField.setColumns(10);
	    
	    JLabel lblCheckInDate = new JLabel("Check in date:");
	    lblCheckInDate.setForeground(new Color(11, 0, 0));
	    lblCheckInDate.setFont(new Font("Century Gothic", Font.BOLD, 18));
	    lblCheckInDate.setBounds(36, 396, 143, 23);
	    contentPane.add(lblCheckInDate);
	    
	    textField_1 = new JTextField();
	    textField_1.setHorizontalAlignment(SwingConstants.CENTER);
	    textField_1.setText("YYYY-MM-DD");
	    textField_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
	    textField_1.setColumns(10);
	    textField_1.setBackground(Color.WHITE);
	    textField_1.setBounds(196, 400, 143, 19);
	    contentPane.add(textField_1);
	    
	    JLabel lblCheckOutDate = new JLabel("Check out date:");
	    lblCheckOutDate.setForeground(new Color(11, 0, 0));
	    lblCheckOutDate.setFont(new Font("Century Gothic", Font.BOLD, 18));
	    lblCheckOutDate.setBounds(28, 429, 151, 23);
	    contentPane.add(lblCheckOutDate);
	    
	    textField_2 = new JTextField();
	    textField_2.setText("YYYY-MM-DD");
	    textField_2.setHorizontalAlignment(SwingConstants.CENTER);
	    textField_2.setFont(new Font("Century Gothic", Font.PLAIN, 14));
	    textField_2.setColumns(10);
	    textField_2.setBackground(Color.WHITE);
	    textField_2.setBounds(196, 433, 143, 19);
	    contentPane.add(textField_2);
	    
	    JButton btnNewButton_4 = new JButton("UPDATE");
	    btnNewButton_4.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		String property_id = textField.getText();
	    		String newcheckIndate = textField_1.getText();
	    	    String newcheckOutdate = textField_2.getText();
	    	    
	    	    int propertyId = Integer.parseInt(property_id);
	    	    BookingRepository bookingRepository = BookingRepository.getInstance();

	    	    boolean updateResult = bookingRepository.updateBooking(propertyId, newcheckIndate, newcheckOutdate);

	    	    if (updateResult)
	    	    {
	    	        JOptionPane.showMessageDialog(null, "Booking updated successfully!");

	    	        refreshTable();
	    	    } 
	    	    else 
	    	    {
	    	        JOptionPane.showMessageDialog(null, "Failed to update the booking.");
	    	    }
	    	}
	    });
	    btnNewButton_4.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnNewButton_4.setBounds(379, 432, 85, 21);
	    contentPane.add(btnNewButton_4);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("DELETE BOOKING");
	    lblNewLabel_1_1.setToolTipText("");
	    lblNewLabel_1_1.setForeground(new Color(11, 0, 0));
	    lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
	    lblNewLabel_1_1.setBackground(Color.WHITE);
	    lblNewLabel_1_1.setBounds(31, 503, 233, 50);
	    contentPane.add(lblNewLabel_1_1);
	    
	    JLabel lblEnterPropertyId_1 = new JLabel("Enter booking id:");
	    lblEnterPropertyId_1.setForeground(new Color(11, 0, 0));
	    lblEnterPropertyId_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
	    lblEnterPropertyId_1.setBounds(48, 563, 175, 23);
	    contentPane.add(lblEnterPropertyId_1);
	    
	    textField_3 = new JTextField();
	    textField_3.setBounds(233, 563, 109, 19);
	    contentPane.add(textField_3);
	    textField_3.setColumns(10);
	    
	    JButton btnNewButton = new JButton("DELETE");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		String booking_id = textField_3.getText();

	    	    try {
	    	        int bookingId = Integer.parseInt(booking_id);

	    	        // Get the user ID after registration
	    	        String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Customer' ORDER BY id DESC LIMIT 1;";
	    	        ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);

	    	        try {
	    	            if (userIdResultSet.next()) 
	    	            {
	    	                int userId = userIdResultSet.getInt("userID");

	    	                String getUserIdQuery1 = "SELECT property_id FROM Booking WHERE booking_id = '" + bookingId + "';";
	    	                ResultSet propertyIdResultSet1 = SelectQuery.getData(getUserIdQuery1); 

	    	                try 
	    	                {
	    	                    if (propertyIdResultSet1.next()) 
	    	                    { 
	    	                        int propertyId = propertyIdResultSet1.getInt("property_id");

	    	                       
	    	                        BookingRepository bookingRepository = BookingRepository.getInstance();

	    	                        // Check if the customer has booked the chosen property
	    	                        if (bookingRepository.hasCustomerBookedProperty(userId, propertyId)) 
	    	                        {
	    	                            // Delete the booking
	    	                            boolean deleteResult = bookingRepository.deleteBooking(bookingId);

	    	                            if (deleteResult) {
	    	                                JOptionPane.showMessageDialog(null, "Booking deleted successfully!");

	    	                                // Refresh the JTable after deletion
	    	                                refreshTable();
	    	                                
	    	                            } else {
	    	                                JOptionPane.showMessageDialog(null, "Failed to delete the booking.");
	    	                            }
	    	                        } else {
	    	                            JOptionPane.showMessageDialog(null, "Customer has not booked the chosen property.");
	    	                        }
	    	                    } else {
	    	                        JOptionPane.showMessageDialog(null, "Unable to get userID");
	    	                    }

	    	                } catch (SQLException ex) {
	    	                    ex.printStackTrace();
	    	                }
	    	            }
	    	        } catch (SQLException ex) {
	    	            ex.printStackTrace();
	    	        }
	    	    } catch (NumberFormatException ex) {
	    	        JOptionPane.showMessageDialog(null, "Invalid booking ID.");
	    	    }
	    	}
	    });

	    btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnNewButton.setBounds(379, 563, 85, 21);
	    contentPane.add(btnNewButton);
	}
		
	    private void refreshTable()
	    {
	        DefaultTableModel updatedModel = SelectQuery.getBookingDataModel();
	        table_1.setModel(updatedModel);
	    }
}
