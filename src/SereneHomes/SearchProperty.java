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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import businessLogicLayer.PropertyRepository;
import proj.SelectQuery;
import businessLogicLayer.Property;
import java.util.*;
import javax.swing.JTable;
import businessLogicLayer.Booking;
import businessLogicLayer.BookingRepository;

public class SearchProperty extends JFrame 
{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField txtYyyymmdd;
	private JTextField txtYyyymmdd_1;
	private JTable resultTable;
	private JTextField property_ID_textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchProperty frame = new SearchProperty();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	String checkInDate = "";
	String checkOutDate = "";

	/**
	 * Create the frame.
	 */
	public SearchProperty() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 643);
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
		button.setBounds(640, 10, 35, 35);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("SEARCH PROPERTY");
		lblNewLabel.setForeground(new Color(11, 0, 0));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(20, 10, 233, 50);
		contentPane.add(lblNewLabel);
		
		JLabel opt = new JLabel("Budget:");
		opt.setForeground(new Color(11, 0, 0));
		opt.setFont(new Font("Century Gothic", Font.BOLD, 20));
		opt.setBounds(41, 84, 96, 23);
		contentPane.add(opt);
		
		textField = new JTextField();
		textField.setBounds(129, 90, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setForeground(new Color(11, 0, 0));
		lblTo.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblTo.setBounds(246, 84, 25, 23);
		contentPane.add(lblTo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(293, 90, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setForeground(new Color(11, 0, 0));
		lblCity.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblCity.setBounds(41, 132, 96, 23);
		contentPane.add(lblCity);
		
		textField_2 = new JTextField();
		textField_2.setBounds(129, 138, 96, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblCheckInDate = new JLabel("Check in date:");
		lblCheckInDate.setForeground(new Color(11, 0, 0));
		lblCheckInDate.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblCheckInDate.setBounds(41, 178, 154, 23);
		contentPane.add(lblCheckInDate);
		
		txtYyyymmdd = new JTextField();
		txtYyyymmdd.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtYyyymmdd.setBackground(new Color(255, 255, 255));
		txtYyyymmdd.setText("YYYY-MM-DD");
		txtYyyymmdd.setBounds(215, 184, 116, 19);
		contentPane.add(txtYyyymmdd);
		txtYyyymmdd.setColumns(10);
		
		JLabel lblCheckOutDate = new JLabel("Check out date:");
		lblCheckOutDate.setForeground(new Color(11, 0, 0));
		lblCheckOutDate.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblCheckOutDate.setBounds(41, 224, 165, 23);
		contentPane.add(lblCheckOutDate);
		
		txtYyyymmdd_1 = new JTextField();
		txtYyyymmdd_1.setText("YYYY-MM-DD");
		txtYyyymmdd_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		txtYyyymmdd_1.setColumns(10);
		txtYyyymmdd_1.setBackground(Color.WHITE);
		txtYyyymmdd_1.setBounds(215, 228, 116, 19);
		contentPane.add(txtYyyymmdd_1);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				  // Get search criteria from text fields
		        double minBudget = Double.parseDouble(textField.getText());
		        double maxBudget = Double.parseDouble(textField_1.getText());
		        String city = textField_2.getText();
		        checkInDate = txtYyyymmdd.getText();
		        checkOutDate = txtYyyymmdd_1.getText(); 
		       
		       /* double minBudget = 70;
		        double maxBudget = 120;
		        String city = "Lahore";
		        checkInDate = "2023-12-08";
		        checkOutDate = "2023-12-17"; */
		        
				/*double minBudget = 80.0;  // Set the minimum budget
				double maxBudget = 100.0;  // Set the maximum budget
				String city = "Islamabad";  // Set the city
				checkInDate = "2023-12-15";  // Set the check-in date
				checkOutDate = "2023-12-20";  // Set the check-out date
				*/

		        
		        if (minBudget == 0|| maxBudget == 0 || city.equals("") || checkInDate.equals("") || checkOutDate.equals(""))
				{
		        	JOptionPane.showMessageDialog(null, "Every Field Is Required");
		        } 
		        else 
		        {		        
			        PropertyRepository propertyRepository = PropertyRepository.getInstance();
			        List<Property> properties = propertyRepository.getProperties();
			        
			        // Search for properties based on criteria
			        List<Property> searchResults = PropertyRepository.searchProperties(minBudget, maxBudget, city, checkInDate, checkOutDate);
			        
			        // Display search results
			        displaySearchResults(searchResults);
			        System.out.println("done");
		        }
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton.setBounds(394, 227, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblResults = new JLabel("RESULTS:");
		lblResults.setForeground(new Color(11, 0, 0));
		lblResults.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblResults.setBounds(41, 281, 107, 23);
		contentPane.add(lblResults);
		
		 resultTable = new JTable();
	     JScrollPane scrollPane = new JScrollPane(resultTable);
	     scrollPane.setBounds(39, 325, 596, 86);
	     contentPane.add(scrollPane);
	     
	     JLabel lblSelectPropertyTo = new JLabel("Select property to book:");
	     lblSelectPropertyTo.setForeground(new Color(11, 0, 0));
	     lblSelectPropertyTo.setFont(new Font("Century Gothic", Font.BOLD, 22));
	     lblSelectPropertyTo.setBounds(30, 480, 267, 23);
	     contentPane.add(lblSelectPropertyTo);
	     
	     JLabel lblEnterPropertyId = new JLabel("Enter property id:");
	     lblEnterPropertyId.setForeground(new Color(11, 0, 0));
	     lblEnterPropertyId.setFont(new Font("Century Gothic", Font.BOLD, 20));
	     lblEnterPropertyId.setBounds(50, 524, 175, 23);
	     contentPane.add(lblEnterPropertyId);
	     
	     property_ID_textField_3 = new JTextField();
	     property_ID_textField_3.setBounds(239, 530, 116, 19);
	     contentPane.add(property_ID_textField_3);
	     property_ID_textField_3.setColumns(10);
	     
	     JButton btnNewButton_1 = new JButton("BOOK");
	     btnNewButton_1.addActionListener(new ActionListener()
	     {
	     	public void actionPerformed(ActionEvent e) 
	     	{
	     		String property_id = property_ID_textField_3.getText();
	
        	// Get the user's ID after registration
            String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Customer' ORDER BY id DESC LIMIT 1;";
            ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);
            try
            {
                if (userIdResultSet.next())
                {
                     int userId1 = userIdResultSet.getInt("userID"); 		        	               
                     try
                     {
                         int propertyId = Integer.parseInt(property_id);
                         PropertyRepository propertyRepository = PropertyRepository.getInstance();
                         Property selectedProperty = propertyRepository.getPropertyById(propertyId);

                         if (selectedProperty == null )
                         {
                        	 System.out.println("null");
                         }                                                     
                        
         		        String checkInDate1 = checkInDate;
         		        String checkOutDate1 = checkOutDate;

                         if (!BookingRepository.getInstance().hasCustomerBookedProperty(userId1, propertyId)) 
                         {                                                            
                             if (selectedProperty != null && propertyRepository.checkDateAvailability(selectedProperty.getStart_date_availability(), selectedProperty.getEnd_date_availability(), checkInDate1, checkOutDate1))
                             {
                            	 System.out.println("reached");
                                 // create booking
                                 Booking newBooking = new Booking();
                                 newBooking.setUserId(userId1);
                                 newBooking.setPropertyId(selectedProperty.getPropertyID());
                                 newBooking.setCheckInDate(checkInDate1);
                                 newBooking.setCheckOutDate(checkOutDate1);

                                 boolean bookingResult = BookingRepository.getInstance().createBooking(newBooking);

                                 if (bookingResult)
                                 {
                                     JOptionPane.showMessageDialog(null, "Booking successful! Booking ID: " + newBooking.getBookingId());                               
			                         
                                 } 
                                 else 
                                 {
                                     JOptionPane.showMessageDialog(null, "Failed to book the property.");
                                 }
                             }
                             else {
                                 JOptionPane.showMessageDialog(null, "Selected property is not available for the specified dates.");
                             }
                         }
                         else
                         {
                             JOptionPane.showMessageDialog(null, "You have already booked this property.");
                         }
                     } catch (NumberFormatException ex) {
                         JOptionPane.showMessageDialog(null, "Invalid property ID.");
                     }         
                 }
             } 
            catch (SQLException e1)
            {
                 e1.printStackTrace();
            }          	     	                    
	     	}
	     });
	     
	     btnNewButton_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
	     btnNewButton_1.setBounds(394, 527, 85, 21);
	     contentPane.add(btnNewButton_1);
	     
	     JLabel lblBookProperty = new JLabel("BOOK PROPERTY");
	     lblBookProperty.setToolTipText("");
	     lblBookProperty.setForeground(new Color(11, 0, 0));
	     lblBookProperty.setFont(new Font("Century Gothic", Font.BOLD, 25));
	     lblBookProperty.setBackground(Color.WHITE);
	     lblBookProperty.setBounds(20, 431, 233, 50);
	     contentPane.add(lblBookProperty);
	     
	     JButton btnNewButton_2 = new JButton("GO BACK");
	     btnNewButton_2.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e) 
	     	{
	     		 setVisible(false);	
                 dispose(); // Close the current frame
                 new HomeCustomer().setVisible(true);
	     	}
	     });
	     btnNewButton_2.setFont(new Font("Century Gothic", Font.BOLD, 13));
	     btnNewButton_2.setBounds(20, 575, 96, 21);
	     contentPane.add(btnNewButton_2);
	     
	     JButton btnNewButton_1_1 = new JButton("CHECKOUT");
	     btnNewButton_1_1.addActionListener(new ActionListener() {
	     	public void actionPerformed(ActionEvent e)
	     	{
	     		 setVisible(false);	
                 dispose(); // Close the current frame
                 new Payment().setVisible(true);
	     	}
	     });
	     btnNewButton_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
	     btnNewButton_1_1.setBounds(556, 575, 107, 21);
	     contentPane.add(btnNewButton_1_1);		
	}
	
	private Property getPropertyById(int propertyId) {
	    return PropertyRepository.getInstance().getPropertyById(propertyId);
	}
	
	private void displaySearchResults(List<Property> searchResults)
	{
	    // Check if searchResults is empty
	    if (searchResults.isEmpty()) 
	    {
	        System.out.println("No properties found.");
	    }
	    else 
	    {
	        // Iterate through the list and display each property
	        for (Property property : searchResults) 
	        {
	            System.out.println("Property ID: " + property.getPropertyID());
	            System.out.println("Owner ID: " + property.getOwnerID());
	            System.out.println("Name: " + property.getName());
	            System.out.println("Location: " + property.getLocation());
	            System.out.println("Price per night: " + property.getPrice_per_night());
	            System.out.println("Start date availability: " + property.getStart_date_availability());
	            System.out.println("End date availability: " + property.getEnd_date_availability());
	            System.out.println("Availability: " + property.isAvailability());
	            System.out.println("------------------------------------");
	        }
	        
	            DefaultTableModel model = new DefaultTableModel();

	            model.addColumn("Property ID");
	            model.addColumn("Owner ID");
	            model.addColumn("Name");
	            model.addColumn("Location");
	            model.addColumn("Price per night");
	            model.addColumn("Start date availability");
	            model.addColumn("End date availability");
	            model.addColumn("Availability");

	            for (Property property : searchResults) {
	                model.addRow(new Object[]{
	                        property.getPropertyID(),
	                        property.getOwnerID(),
	                        property.getName(),
	                        property.getLocation(),
	                        property.getPrice_per_night(),
	                        property.getStart_date_availability(),
	                        property.getEnd_date_availability(),
	                        property.isAvailability()
	                });
	            }

	            resultTable.setModel(model);
	        }

	  }
}

