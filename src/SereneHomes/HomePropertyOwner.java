package SereneHomes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import proj.SelectQuery;
import javax.swing.JTextField;

public class HomePropertyOwner extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePropertyOwner frame = new HomePropertyOwner();
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
	public HomePropertyOwner() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 369);
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
		button.setBounds(531, 10, 35, 35);
		contentPane.add(button);
		
		JLabel lblHome = new JLabel("SERENE HOMES ");
		lblHome.setBounds(10, 10, 212, 40);
		lblHome.setToolTipText("");
		lblHome.setForeground(new Color(0, 0, 0));
		lblHome.setFont(new Font("Century Gothic", Font.BOLD, 28));
		lblHome.setBackground(new Color(255, 255, 255));
		contentPane.add(lblHome);
		
		JLabel lblNewLabel = new JLabel("Manage Property");
		lblNewLabel.setBounds(113, 238, 122, 33);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JLabel lblReviewBookings = new JLabel("Review Bookings");
		lblReviewBookings.setBounds(327, 238, 135, 33);
		lblReviewBookings.setFont(new Font("Century Gothic", Font.BOLD, 14));
		contentPane.add(lblReviewBookings);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(323, 120, 109, 97);
		btnNewButton.setIcon(new ImageIcon(HomePropertyOwner.class.getResource("/images/reviewbooking.jpg")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				  setVisible(false);	
                  dispose(); // Close the current frame
                  new ReviewBooking().setVisible(true);	
				
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(255, 255, 255));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setIcon(new ImageIcon(HomePropertyOwner.class.getResource("/images/reviewbooking.jpg")));
		btnNewButton_2.setBounds(336, 184, 85, 21);	
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(323, 120, 109, 97);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 setVisible(false);	
                 dispose(); // Close the current frame
                 new ManageProperty().setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(HomePropertyOwner.class.getResource("/images/property.png")));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(113, 120, 109, 97);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("LOG OUT");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wish to exit?","Select", JOptionPane.YES_NO_OPTION);
				if(exit==0) 
				{
					System.exit(0);
				}
			}
		});
		btnNewButton_3.setFont(new Font("Century Gothic", Font.BOLD, 13));
		btnNewButton_3.setBounds(20, 301, 100, 21);
		contentPane.add(btnNewButton_3);
		
	}
}
