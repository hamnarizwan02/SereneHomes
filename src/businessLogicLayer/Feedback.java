package businessLogicLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import proj.ConnectionProvider;

public class Feedback
{

    private String feedback;
    private int customer;
    private int property; 

    public Feedback(String feedback, int customerId, int propertyId) {
        this.feedback = feedback;
        this.customer = customerId;
        this.property = propertyId;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getProperty() { 
        return property;
    }

    public void setProperty(int property) {  
        this.property = property;
    }

    public void saveFeedbackToDatabase() 
    {
        try (Connection connection = ConnectionProvider.getConnection()) 
        {
            String query = "INSERT INTO feedback (feedback, property_id, userID) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, feedback);
                preparedStatement.setInt(2, property);  
                preparedStatement.setInt(3, customer);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

