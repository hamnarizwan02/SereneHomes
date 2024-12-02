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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import businessLogicLayer.Feedback;
import proj.SelectQuery;
import javax.swing.JTextField;

public class Givefeedback extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Givefeedback frame = new Givefeedback();
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
	public Givefeedback() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 591);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGiveFeedback = new JLabel("Give Feedback");
		lblGiveFeedback.setToolTipText("");
		lblGiveFeedback.setForeground(Color.BLACK);
		lblGiveFeedback.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblGiveFeedback.setBackground(Color.WHITE);
		lblGiveFeedback.setBounds(35, 42, 314, 40);
		contentPane.add(lblGiveFeedback);
		
		JLabel lblEnterYourProperty_1 = new JLabel("Enter your property for which you want to give feedbacks :");
		lblEnterYourProperty_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterYourProperty_1.setBounds(35, 90, 477, 31);
		contentPane.add(lblEnterYourProperty_1);
		
		JLabel lblNewLabel = new JLabel("Property name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 143, 87, 19);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(135, 145, 96, 19);
		contentPane.add(textField);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(245, 245, 220));
		textArea.setBounds(135, 218, 517, 223);
		contentPane.add(textArea);
		
		JButton btnConfirm = new JButton("Confirm");
        btnConfirm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        	    String feedbackText = textArea.getText();
        	    String propertyname = textField.getText();

        	    String getUserIdQuery = "SELECT userID FROM curr_user WHERE user_type = 'Customer' ORDER BY id DESC LIMIT 1;";

        	    try (ResultSet userIdResultSet = SelectQuery.getData(getUserIdQuery))
        	    {
        	        if (userIdResultSet.next()) 
        	        {
        	            int userId = userIdResultSet.getInt("userID");
        	            System.out.println(userId);
        	            
                	    String propertyIdQuery = "SELECT property_id FROM Property WHERE property_name = '"+propertyname+"'";

        	            try (ResultSet propertyIdResultSet = SelectQuery.getData(propertyIdQuery)) 
        	            {
        	                if (propertyIdResultSet.next())
        	                {
        	                	int propertyId = propertyIdResultSet.getInt("property_id");

        	                    System.out.println(propertyId);

        	                    if (userId != -1 && propertyId != -1)
        	                    {
        	                        Feedback feedback = new Feedback(feedbackText, userId, propertyId);
        	                        feedback.saveFeedbackToDatabase();
        	                        JOptionPane.showMessageDialog(null, "Feedback saved successfully.");
        	                    } 
        	                    else 
        	                    {
        	                        JOptionPane.showMessageDialog(null, "Error retrieving user ID or property ID.");
        	                    }
        	                }
        	            }
        	        }
        	    } catch (SQLException | NumberFormatException ex) {
        	        ex.printStackTrace();
        	        JOptionPane.showMessageDialog(null, "Error processing feedback: " + ex.getMessage());
        	    }
        	}

        	private int getPropertyIdFromResultSet(ResultSet resultSet) throws SQLException {
        	    if (resultSet.next()) {
        	        return resultSet.getInt("property_id");
        	    }
        	    return -1; 
        	}
        });

		 btnConfirm.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 13));
	        btnConfirm.setBounds(308, 500, 159, 31);
	        contentPane.add(btnConfirm);

		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
	            new HomeCustomer().setVisible(true);
			}
		});
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGoBack.setBounds(24, 500, 159, 31);
		contentPane.add(btnGoBack);
				
		JLabel lblFeedback = new JLabel("Feedback:");
        lblFeedback.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 14));
        lblFeedback.setBounds(37, 207, 87, 19);
        contentPane.add(lblFeedback);
	}
}
