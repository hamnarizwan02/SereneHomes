package SereneHomes;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import proj.SelectQuery;
import javax.swing.JTable;

public class ViewFeedback extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewFeedback frame = new ViewFeedback();
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
    public ViewFeedback() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 643, 430);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHome = new JLabel("View Feedbacks");
        lblHome.setToolTipText("");
        lblHome.setForeground(Color.BLACK);
        lblHome.setFont(new Font("Century Gothic", Font.BOLD, 28));
        lblHome.setBackground(Color.WHITE);
        lblHome.setBounds(26, 33, 314, 40);
        contentPane.add(lblHome);

        textField = new JTextField();
        textField.setBounds(147, 137, 96, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Property name:");
        lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        lblNewLabel.setBounds(24, 135, 113, 19);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("View feedbacks");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) 
        	{
        	    String propertyName = textField.getText();
                DefaultTableModel feedbackModel = getFeedbackDataModel(propertyName);
                table.setModel(feedbackModel);
        	}

        });

        btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 13));
        btnNewButton.setBounds(292, 129, 159, 31);
        contentPane.add(btnNewButton);

        JLabel lblEnterYourProperty = new JLabel("Enter your property name for which you want to view feedbacks :");
        lblEnterYourProperty.setFont(new Font("Century Gothic", Font.PLAIN, 14));
        lblEnterYourProperty.setBounds(26, 96, 477, 31);
        contentPane.add(lblEnterYourProperty);

        JButton btnNewButton_1 = new JButton("GO BACK");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose(); // Close the current frame
                new HomeCustomer().setVisible(true);
            }
        });
        btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_1.setFont(new Font("Century Gothic", Font.BOLD, 13));
        btnNewButton_1.setBounds(26, 353, 96, 21);
        contentPane.add(btnNewButton_1);
        
        table = new JTable();
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(37, 205, 547, 86);
	    contentPane.add(scrollPane);		
    }

    private DefaultTableModel getFeedbackDataModel(String propertyName)
    {
        return SelectQuery.getFeedbackDataModel(propertyName);
    }
}
