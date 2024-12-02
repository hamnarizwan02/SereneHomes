package businessLogicLayer;
import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import proj.SelectQuery;
import proj.ConnectionProvider;

public class BookingRepository
{
    private static BookingRepository instance;
    private BookingRepository() {  }    // private constructor for singleton 
        
   
    public static BookingRepository getInstance() {
        if (instance == null) {
            instance = new BookingRepository();
        }
        return instance;
    }
   
    public boolean createBooking(Booking booking)
    {
        try (Connection connection = ConnectionProvider.getConnection()) 
        {
            String query = "INSERT INTO Booking (customer_id, property_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) 
            {
                preparedStatement.setInt(1, booking.getUserId());
                preparedStatement.setInt(2, booking.getPropertyId());
                preparedStatement.setString(3, booking.getCheckInDate());
                preparedStatement.setString(4, booking.getCheckOutDate());

                // Execute insertion
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0)
                {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys())
                    {
                        if (generatedKeys.next()) 
                        {
                            // Set bookingId
                            int generatedBookingId = generatedKeys.getInt(1);
                            booking.setBookingId(generatedBookingId);
                        } else 
                        {
                            throw new SQLException("Failed to retrieve generated key.");
                        }
                    }
                    
                    return true;
                } 
                else
                {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Booking> getAllBookings() 
    {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = ConnectionProvider.getConnection()) 
        {
            String query = "SELECT * FROM Booking";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query))
            {
                try (ResultSet resultSet = preparedStatement.executeQuery())
                {
                    while (resultSet.next()) 
                    {
                        Booking booking = new Booking(
                                resultSet.getInt("booking_id"),
                                resultSet.getInt("customer_id"),
                                resultSet.getInt("property_id"),
                                resultSet.getString("check_in_date"),
                                resultSet.getString("check_out_date")
                        );
                        bookings.add(booking);
                    }
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return bookings;
    }
    
    public List<Booking> getBookingsByCustomerId() 
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Booking> bookings = new ArrayList<>();

        connection = ConnectionProvider.getConnection();
		ResultSet rs = SelectQuery.getData("SELECT userID FROM curr_user WHERE user_type = 'Customer' ORDER BY id DESC LIMIT 1;");
		try 
		{
		    if (rs.next()) 
		    {
		        String userID = rs.getString("userID");
		        String query = "SELECT * FROM Booking WHERE customer_id = ?";
		        preparedStatement = connection.prepareStatement(query);
		        preparedStatement.setString(1, userID);
		        resultSet = preparedStatement.executeQuery();

		        while (resultSet.next()) {
		            Booking booking = new Booking(
		                    resultSet.getInt("booking_id"),
		                    resultSet.getInt("customer_id"),
		                    resultSet.getInt("property_id"),
		                    resultSet.getString("check_in_date"),
		                    resultSet.getString("check_out_date")
		            );
		            bookings.add(booking);
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		} 
		finally {
		    try {
		        if (resultSet != null) resultSet.close();
		        if (preparedStatement != null) preparedStatement.close();
		        if (connection != null) connection.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

        return bookings;
    }
    
    public List<Integer> reset() 	//get bookingIds of customer when they confirm payment
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Integer> bookingIds = new ArrayList<>();

        try {
            connection = ConnectionProvider.getConnection();
            ResultSet rs = SelectQuery.getData("SELECT userID FROM curr_user WHERE user_type = 'Customer' ORDER BY id DESC LIMIT 1;");

            if (rs.next()) {
                String userID = rs.getString("userID");
                String query = "SELECT booking_id FROM Booking WHERE customer_id = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userID);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int bookingId = resultSet.getInt("booking_id");
                    bookingIds.add(bookingId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookingIds;
    }


    public boolean hasCustomerBookedProperty(int userId, int propertyId)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try 
        {
            connection = ConnectionProvider.getConnection();
            String query = "SELECT booking_id FROM Booking WHERE customer_id = ? AND property_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, propertyId);
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public boolean deleteBooking(int bookingId)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try 
        {
            connection = ConnectionProvider.getConnection();
            String query = "DELETE FROM Booking WHERE booking_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookingId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; 

        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        finally
        {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean updateBooking(int propertyId, String newcheckIndate, String newcheckOutdate) 
    {
    	 Connection connection = null;
         PreparedStatement preparedStatement = null;
         
        try {
        	connection = ConnectionProvider.getConnection();
            String updateQuery = "UPDATE Booking SET check_in_date = ?, check_out_date = ? WHERE property_id = ?";
            try (PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery)) {
                preparedStatement1.setString(1, newcheckIndate);
                preparedStatement1.setString(2, newcheckOutdate);
                preparedStatement1.setInt(3, propertyId);

                // Execute query
                int rowsUpdated = preparedStatement1.executeUpdate();

                // Check if the update was successful
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static double calculateBillForBooking(int propertyId, String checkInDate, String checkOutDate) 
    {
        try (Connection connection = ConnectionProvider.getConnection()) 
        {
            String query = "SELECT price_per_night FROM Property WHERE property_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) 
            {
                preparedStatement.setInt(1, propertyId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) 
                {
                    if (resultSet.next())
                    {
                        double pricePerNight = resultSet.getDouble("price_per_night");
                        int numberOfNights = calculateNumberOfNights(checkInDate, checkOutDate);
                        
                        double bill = pricePerNight * numberOfNights;
                        System.out.println("The bill is " + bill);

                        return bill;
                    }
                    else 
                    {
                    	System.out.println("Property not found");
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static int calculateNumberOfNights(String checkInDate, String checkOutDate)
    {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = dateFormat.parse(checkInDate);
            Date endDate = dateFormat.parse(checkOutDate);

            long differenceInMillis = endDate.getTime() - startDate.getTime();
            return (int) (differenceInMillis / (24 * 60 * 60 * 1000)); // Convert milliseconds to days

        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Or throw an exception
        }
    }

}

