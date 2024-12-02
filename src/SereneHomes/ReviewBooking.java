package SereneHomes;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogicLayer.BookingRepository;
import proj.SelectQuery;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class ReviewBooking extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	 private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewBooking frame = new ReviewBooking();
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
	public ReviewBooking() 
	{		
		 table_1 = new JTable();
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 442);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(ReviewBooking.class.getResource("/images/icons8-close-35.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?","Select", JOptionPane.YES_NO_OPTION);
				if(exit==0) 
				{
					System.exit(0);
				}
			}
		});
		btnNewButton_3.setBounds(793, 10, 38, 21);
		contentPane.add(btnNewButton_3);
				
		
		JLabel lblReviewBooking = new JLabel("REVIEW BOOKING");
		lblReviewBooking.setToolTipText("");
		lblReviewBooking.setForeground(new Color(11, 0, 0));
		lblReviewBooking.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblReviewBooking.setBackground(Color.WHITE);
		lblReviewBooking.setBounds(10, 10, 295, 50);
		getContentPane().add(lblReviewBooking);
		
		JTable table_1 = new JTable();
		table_1.setBounds(67, 257, 321, -100);
		contentPane.add(table_1);
		
		JScrollPane scrollPane = new JScrollPane(table_1);
	    scrollPane.setBounds(30, 106, 742, 70);
	    contentPane.add(scrollPane);
	    
	    JButton btnNewButton_2 = new JButton("SHOW BOOKING");
	    btnNewButton_2.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{   DefaultTableModel model = new DefaultTableModel();

	            model.addColumn("Booking ID");	           
	            model.addColumn("Property ID");
	            model.addColumn("Property name");
	            model.addColumn("Location");
	            model.addColumn("Price per night");
	            model.addColumn("Start date availability");
	            model.addColumn("End date availability");
	            model.addColumn("Availability");
	            
	            String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Property Owner' ORDER BY id DESC LIMIT 1;";
	            ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
	            try
	            {
	                if (userIdResultSet.next())
	                {
	                     int userId1 = userIdResultSet.getInt("userID"); 		        	
	                     
			            String query = "SELECT * FROM Booking b "
			            		+ "INNER JOIN Property p ON b.property_id = p.property_id where p.owner_id = '"+ userId1 +"' ";
	
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
		btnNewButton_2.setBounds(30, 196, 143, 23);
		getContentPane().add(btnNewButton_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(198, 293, 109, 19);
		getContentPane().add(textField_2);
		
		JLabel lblEnterPropertyId_1_1 = new JLabel("Enter booking id:");
		lblEnterPropertyId_1_1.setForeground(new Color(11, 0, 0));
		lblEnterPropertyId_1_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblEnterPropertyId_1_1.setBounds(30, 287, 175, 23);
		getContentPane().add(lblEnterPropertyId_1_1);
		
		JLabel lblEnterDetailsTo = new JLabel("Enter details to cancel booking :");
		lblEnterDetailsTo.setForeground(new Color(11, 0, 0));
		lblEnterDetailsTo.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblEnterDetailsTo.setBounds(23, 254, 346, 23);
		getContentPane().add(lblEnterDetailsTo);
		
		JLabel opt = new JLabel("Bookings:");
		opt.setForeground(new Color(11, 0, 0));
		opt.setFont(new Font("Century Gothic", Font.BOLD, 18));
		opt.setBounds(20, 70, 102, 23);
		getContentPane().add(opt);
		
		JButton btnNewButton = new JButton("DELETE");
		 btnNewButton.addActionListener(new ActionListener() 
		 {
			 public void actionPerformed(ActionEvent e) 
		    	{
		    		String booking_id = textField_2.getText();
		    	    try {
		    
		    	        int bookingId = Integer.parseInt(booking_id);

		    	        // Get the user ID after registration
		    	        String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Property Owner' ORDER BY id DESC LIMIT 1;";
		    	        ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);

		    	        try 
		    	        {
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

		    	                        boolean deleteResult = bookingRepository.deleteBooking(bookingId);

		    	                            if (deleteResult) {
		    	                                JOptionPane.showMessageDialog(null, "Booking deleted successfully!");

		    	                                // Refresh the JTable after deletion
		    	                                refreshTable();
		    	                                
		    	                            } else {
		    	                                JOptionPane.showMessageDialog(null, "Failed to delete the booking.");
		    	                            }
		    	                        } 
		    	                    else {
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
		btnNewButton.setBounds(379, 290, 85, 21);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("GO BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);	
                dispose(); // Close the current frame
                new HomePropertyOwner().setVisible(true);
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton_1.setBounds(30, 360, 92, 21);
		contentPane.add(btnNewButton_1);
	}

	private void refreshTable() 
	{
        DefaultTableModel updatedModel = SelectQuery.getBookingDataModel();
        table_1.setModel(updatedModel);  
    }

}