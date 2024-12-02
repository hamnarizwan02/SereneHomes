package SereneHomes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogicLayer.Booking;
import businessLogicLayer.BookingRepository;
import businessLogicLayer.Property;
import proj.SelectQuery;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.JTextField;

public class Payment extends JFrame {

	private JPanel contentPane;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
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
	public Payment()
	{
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener()
		{
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
		
		JLabel lblNewLabel = new JLabel("PAYMENT");
		lblNewLabel.setForeground(new Color(11, 0, 0));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(20, 10, 233, 50);
		contentPane.add(lblNewLabel);

		table_1 = new JTable();
	    JScrollPane scrollPane = new JScrollPane(table_1);
	    scrollPane.setBounds(37, 284, 596, 106);
	    contentPane.add(scrollPane);
	    
	    JLabel lblSelectPaymentMethod = new JLabel("Select payment method:");
	    lblSelectPaymentMethod.setForeground(new Color(11, 0, 0));
	    lblSelectPaymentMethod.setFont(new Font("Century Gothic", Font.BOLD, 20));
	    lblSelectPaymentMethod.setBounds(20, 81, 256, 23);
	    contentPane.add(lblSelectPaymentMethod);
	    
	    JButton btnNewButton = new JButton("");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		calculateBill();
	    	}
	    });
	    btnNewButton.setIcon(new ImageIcon(Payment.class.getResource("/images/icons8-debit-card-48.png")));
	    btnNewButton.setBounds(63, 135, 100, 50);
	    contentPane.add(btnNewButton);
	    
	    JButton btnNewButton_1 = new JButton("");
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		 calculateBill();
	    		 
	    	}
	    });
	    btnNewButton_1.setIcon(new ImageIcon(Payment.class.getResource("/images/icons8-credit-card-48.png")));
	    btnNewButton_1.setBounds(208, 135, 100, 50);
	    contentPane.add(btnNewButton_1);
	    
	    JLabel label_1 = new JLabel("Debit Card");
	    label_1.setVerticalAlignment(SwingConstants.BOTTOM);
	    label_1.setForeground(new Color(11, 0, 0));
	    label_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    label_1.setBounds(73, 191, 85, 23);
	    contentPane.add(label_1);
	    
	    JLabel label_1_1 = new JLabel("Credit Card");
	    label_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
	    label_1_1.setForeground(new Color(11, 0, 0));
	    label_1_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    label_1_1.setBounds(218, 191, 85, 23);
	    contentPane.add(label_1_1);
	    
	    JLabel lblBookingDetails = new JLabel("Booking Details:");
	    lblBookingDetails.setForeground(new Color(11, 0, 0));
	    lblBookingDetails.setFont(new Font("Century Gothic", Font.BOLD, 20));
	    lblBookingDetails.setBounds(20, 251, 167, 23);
	    contentPane.add(lblBookingDetails);
	    
	    JButton btnNewButton_2 = new JButton("CONFIRM PAYMENT");
	    btnNewButton_2.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		BookingRepository bookingRepository = BookingRepository.getInstance();
	    		List<Integer> bookingids = bookingRepository.reset();
	    		
    	        // Get the user ID after registration
    	        String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Customer' ORDER BY id DESC LIMIT 1;";
    	        ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery);

    	        try 
    	        {
    	            if (userIdResultSet.next()) 
    	            {
    	                int userId = userIdResultSet.getInt("userID");
    	             // Delete bookings for the customer
    	                for (Integer bookingIds : bookingids) 
    	                {
    	                    boolean deleteResult = bookingRepository.deleteBooking(bookingIds);

    	                    if (deleteResult) {
    	                        System.out.println("Booking with ID " + bookingIds + " deleted successfully.");
    	                    } else {
    	                        System.out.println("Failed to delete booking with ID " + bookingIds);
    	                    }
    	                }

    	            }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

    	    }
	    });
	    btnNewButton_2.setFont(new Font("Century Gothic", Font.BOLD, 14));
	    btnNewButton_2.setBounds(452, 413, 173, 21);
	    contentPane.add(btnNewButton_2);  
	    
	    JButton btnNewButton_4 = new JButton("GO BACK");
	    btnNewButton_4.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		 setVisible(false);	
                 dispose(); // Close the current frame
                 new SearchProperty().setVisible(true);
	    	}
	    });
	    btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
	    btnNewButton_4.setFont(new Font("Century Gothic", Font.BOLD, 13));
	    btnNewButton_4.setBounds(37, 413, 100, 21);
	    contentPane.add(btnNewButton_4);
	    
	  }

	private void calculateBill()
	{
	    BookingRepository bookingRepository = BookingRepository.getInstance();

	    // Assuming you have access to the user's ID
	    List<Booking> bookings = bookingRepository.getBookingsByCustomerId();

	    if (!bookings.isEmpty()) 
	    {
	    	System.out.println("hrllooo");
	    	
	        for (Booking booking : bookings) 
	        {
	        	System.out.println("hrllooo");
	            double billAmount = bookingRepository.calculateBillForBooking(booking.getPropertyId(),
	                    booking.getCheckInDate(), booking.getCheckOutDate());

	            displayBookingDetails(bookings);
	        }
	    } 
	    else
	    {
	    	System.out.println("hr");
	        JOptionPane.showMessageDialog(this, "No bookings found for the customer.", "Information", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	
	 public static void saveTableToFile(JTable table, String filePath) 
	 {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();

	        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(filePath))) 
	        {
	            for (int ii = 0; ii < model.getColumnCount(); ii++) 
	            {
	                writer1.write(model.getColumnName(ii));
	                if (ii < model.getColumnCount() - 1) 
	                {
	                    writer1.write("\t"); 
	                }
	            }
	            writer1.newLine();

	            // Write data
	            for (int row1 = 0; row1 < model.getRowCount(); row1++)
	            {
	                for (int col = 0; col < model.getColumnCount(); col++)
	                {
	                    writer1.write(model.getValueAt(row1, col).toString());
	                    if (col < model.getColumnCount() - 1)
	                    {
	                        writer1.write("\t"); // Use tab as a delimiter 
	                    }
	                }
	                writer1.newLine();
	            }

	            System.out.println("Table data saved to: " + filePath);
	            
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }

	private void displayBookingDetails(List<Booking> bookings)
	{
	    DefaultTableModel model = new DefaultTableModel();
	    model.setColumnIdentifiers(new Object[]{"Booking ID", "Customer ID", "Property ID", "Check-in Date", "Check-out Date", "Bill Amount"});
	    table_1.setModel(model);

	    for (Booking booking : bookings) {
	        double billAmount = BookingRepository.calculateBillForBooking(booking.getPropertyId(),
	                booking.getCheckInDate(), booking.getCheckOutDate());

	        model.addRow(new Object[]{booking.getBookingId(), booking.getUserId(), booking.getPropertyId(),
	                booking.getCheckInDate(), booking.getCheckOutDate(), billAmount});
	    }
	    
	    saveTableToFile(table_1, "table_data.txt");
	}
}