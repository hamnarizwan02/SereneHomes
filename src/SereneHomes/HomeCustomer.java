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
import javax.swing.SwingConstants;

public class HomeCustomer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeCustomer frame = new HomeCustomer();
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
	public HomeCustomer() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 580);
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
		button.setBounds(575, 10, 35, 35);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("SERENEHOMES");
		lblNewLabel.setForeground(new Color(11, 0, 0));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel.setBounds(20, 10, 216, 50);
		contentPane.add(lblNewLabel);
		
		JLabel opt = new JLabel("Please select option:");
		opt.setForeground(new Color(11, 0, 0));
		opt.setFont(new Font("Century Gothic", Font.BOLD, 18));
		opt.setBounds(22, 84, 192, 23);
		contentPane.add(opt);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				 setVisible(false);	
                 dispose(); // Close the current frame
                 new ManageBooking().setVisible(true);		
			}
		});
		btnNewButton.setFont(new Font("Century Gothic", Font.ITALIC, 14));
		btnNewButton.setIcon(new ImageIcon(HomeCustomer.class.getResource("/images/icons8-checklist-100.png")));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(117, 137, 109, 110);
		contentPane.add(btnNewButton);
		
		JLabel label_1 = new JLabel("Manage Booking");
		label_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1.setForeground(new Color(11, 0, 0));
		label_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		label_1.setBounds(103, 257, 143, 23);
		contentPane.add(label_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{				
	            setVisible(false);
                dispose(); // Close the current frame	
	            new SearchProperty().setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(HomeCustomer.class.getResource("/images/manageBooking.jpg")));
		btnNewButton_1.setFont(new Font("Century Gothic", Font.ITALIC, 14));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(382, 137, 102, 110);
		contentPane.add(btnNewButton_1);
		
		JLabel label_1_1 = new JLabel("Book Property");
		label_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1_1.setForeground(new Color(11, 0, 0));
		label_1_1.setFont(new Font("Century Gothic", Font.BOLD, 15));
		label_1_1.setBounds(382, 257, 109, 23);
		contentPane.add(label_1_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new Givefeedback().setVisible(true);	
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(HomeCustomer.class.getResource("/images/review.jpg")));
		btnNewButton_2.setFont(new Font("Century Gothic", Font.ITALIC, 14));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(117, 351, 109, 110);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(HomeCustomer.class.getResource("/images/chat1.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);	
                dispose(); // Close the current frame
                new ViewFeedback().setVisible(true);	
			}
		});
		btnNewButton_3.setFont(new Font("Century Gothic", Font.ITALIC, 14));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(382, 351, 109, 110);
		contentPane.add(btnNewButton_3);
		
		JLabel label_1_2 = new JLabel("Give Feedback");
		label_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1_2.setForeground(new Color(11, 0, 0));
		label_1_2.setFont(new Font("Century Gothic", Font.BOLD, 15));
		label_1_2.setBounds(113, 471, 123, 23);
		contentPane.add(label_1_2);
		
		JLabel label_1_3 = new JLabel("View Reviews");
		label_1_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_1_3.setForeground(new Color(11, 0, 0));
		label_1_3.setFont(new Font("Century Gothic", Font.BOLD, 15));
		label_1_3.setBounds(382, 471, 109, 23);
		contentPane.add(label_1_3);
		
		JButton btnNewButton_4 = new JButton("LOG OUT");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int exit = JOptionPane.showConfirmDialog(null, "Are you sure you wish to log out?","Select", JOptionPane.YES_NO_OPTION);
				if(exit==0) 
				{
					System.exit(0);
				}
			}
		});
		btnNewButton_4.setFont(new Font("Century Gothic", Font.BOLD, 14));
		btnNewButton_4.setBounds(508, 511, 102, 21);
		contentPane.add(btnNewButton_4);
		
		
	}
}
