package proj;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SelectQuery 
{
	public static ResultSet getData(String q) 
	{
		Connection sqlcon = null;
		ResultSet rs = null;
		Statement st = null;
		
		try 
		{
			sqlcon = ConnectionProvider.getConnection();
			st = sqlcon.createStatement();
			rs = st.executeQuery(q);
			
			return rs;
		}
		catch(Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex);
			return null;
		}
	}
	
	
	public static DefaultTableModel getBookingDataModel()
	{
	    DefaultTableModel model = new DefaultTableModel();

	    model.addColumn("Booking ID");
	    model.addColumn("Property ID");
	    model.addColumn("Property Name");
	    model.addColumn("Location");
	    model.addColumn("Price per Night");
	    model.addColumn("Start Date Availability");
	    model.addColumn("End Date Availability");
	    model.addColumn("Availability");

	    // get data from the database and populate the model
	    String query = "SELECT Booking.booking_id, Booking.property_id, " +
	                   "Property.property_name, Property.location, Property.price_per_night, " +
	                   "Booking.check_in_date, Booking.check_out_date, Property.available, " +
	                   "Property.start_date_availability, Property.end_date_availability " +
	                   "FROM Booking " +
	                   "INNER JOIN Property ON Booking.property_id = Property.property_id";
	    ResultSet resultSet = getData(query);

	    try {
	        while (resultSet.next()) {
	            model.addRow(new Object[]{
	                    resultSet.getInt("booking_id"),
	                    resultSet.getInt("property_id"),
	                    resultSet.getString("property_name"),
	                    resultSet.getString("location"),
	                    resultSet.getDouble("price_per_night"),
	                    resultSet.getDate("check_in_date"),
	                    resultSet.getDate("check_out_date"),
	                    resultSet.getBoolean("available"),
	                    resultSet.getDate("start_date_availability"),
	                    resultSet.getDate("end_date_availability")
	            });
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return model;
	}	
	
	 private static void closeResultSet(ResultSet resultSet)
	 {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	 
	public static DefaultTableModel getFeedbackDataModel(String propertyName)
	{
	    DefaultTableModel model = new DefaultTableModel();

	    model.addColumn("Feedback ID");
	    model.addColumn("User ID");
	    model.addColumn("Feedback Text");

	    String propertyIdQuery = "select DISTINCT p.property_id from property p join feedback f on f.property_id = p.property_id;";
	    ResultSet propertyIdResultSet = getData(propertyIdQuery);

	    try {
	        while (propertyIdResultSet.next()) 
	        {
	            int propertyId = propertyIdResultSet.getInt("property_id");
	            String feedbackQuery = "SELECT feedbackID, userID, feedback FROM feedback WHERE property_id = '"+propertyId+"'";
	            ResultSet resultSet = getData(feedbackQuery);

	            try {
	                while (resultSet.next()) {
	                    model.addRow(new Object[]{
	                            resultSet.getInt("feedbackID"),
	                            resultSet.getInt("userID"),
	                            resultSet.getString("feedback")
	                    });
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	               
	            } finally {
	                closeResultSet(resultSet);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    } finally {
	        closeResultSet(propertyIdResultSet);
	    }

	    return model;
	}
}
