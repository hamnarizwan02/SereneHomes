package businessLogicLayer;
import java.util.*;
import java.util.Date;

import javax.swing.JOptionPane;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import proj.SelectQuery;
import proj.ConnectionProvider;

public class PropertyRepository 
{
	 private static PropertyRepository instance;
	 private static List<Property> properties;

	    private PropertyRepository() 
	    {
	        this.properties = new ArrayList<>();
	        loadPropertiesFromDatabase();
	    }

	    public static PropertyRepository getInstance() {
	        if (instance == null) {
	            instance = new PropertyRepository();
	        }
	        return instance;
	    }	    

	    public Property getPropertyById(int propertyId)
	    {
	        for (Property property : properties) 
	        {
	            System.out.println("Property ID in list: " + property.getPropertyID());
	            if (property.getPropertyID() == propertyId)
	            {
	            	 System.out.println("match found");
	                return property;
	            }
	        }
	        return null; // Property not found
	    }

	
    public void addProperty(Property property)
    {
        properties.add(property);
    }

    // Get the list of properties
    public static List<Property> getProperties() 
    {    	 
		return properties;
    }
	
    private void loadPropertiesFromDatabase()   // get properties from the database and add them to the list
    {   
        try (Connection connection = ConnectionProvider.getConnection()) 
        {
            String query = "SELECT property_id, owner_id, property_name, location, price_per_night, start_date_availability, "
            		+ "end_date_availability, available FROM Property";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) 
            {
                try (ResultSet resultSet = preparedStatement.executeQuery())
                {
                    while (resultSet.next())
                    {
                        //add to the list
                        Property property = new Property(
                                resultSet.getInt("property_id"),
                                resultSet.getInt("owner_id"),
                                resultSet.getString("property_name"),
                                resultSet.getString("location"),
                                resultSet.getDouble("price_per_night"),
                                resultSet.getString("start_date_availability"),
                                resultSet.getString("end_date_availability"),
                                resultSet.getBoolean("available")
                        );
                        properties.add(property);
                    }
                }
            } 
        } 
        catch (SQLException e) 
        {
                e.printStackTrace(); 
            }
    }
    
    public static List<Property> getPropertiesByOwner(int ownerID) throws SQLException {
        List<Property> ownerProperties = new ArrayList<>();
        String query = "SELECT * FROM Property WHERE owner_id = ?";

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ownerID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Property property = new Property(
                            resultSet.getInt("property_id"),
                            resultSet.getInt("owner_id"),
                            resultSet.getString("property_name"),
                            resultSet.getString("location"),
                            resultSet.getDouble("price_per_night"),
                            null, null, resultSet.getBoolean("available")
                    );
                    ownerProperties.add(property);
                }
            }
        } 
        catch (SQLException e) 
        {         
            e.printStackTrace();
            throw e;
        }

        return ownerProperties;
    }
    
    public static void displayProperties() 
    {
        List<Property> properties = PropertyRepository.getProperties();

        // Iterate through the list and display each property
        for (Property property : properties) {
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
    }
    
    public static List<Property> searchProperties(double minBudget, double maxBudget, String city, String checkInDate, String checkOutDate) 
    {    	
        List<Property> allProperties = PropertyRepository.getProperties();

        if (allProperties == null) 
        {
        	System.out.println("No properties exist");
            return new ArrayList<>(); 
        }
        

        List<Property> searchResults = new ArrayList<>();

        for (Property property : allProperties) 
        {
            if (property.getPrice_per_night() >= minBudget
                    && property.getPrice_per_night() <= maxBudget
                    && property.getLocation().equals(city)
                    && checkDateAvailability(property.getStart_date_availability(), property.getEnd_date_availability(), checkInDate, checkOutDate)
                    && property.isAvailability()) 
            {
                searchResults.add(property);                         
            }
            
            if(searchResults == null) 
            {
	        	JOptionPane.showMessageDialog(null, "No properties found.");
	        }
        }
              
        System.out.println("Search Results:");
        for (Property result : searchResults) {
            System.out.println("Property ID: " + result.getPropertyID());
            System.out.println("Owner ID: " + result.getOwnerID());
            System.out.println("Name: " + result.getName());
            System.out.println("Location: " + result.getLocation());
            System.out.println("Price per night: " + result.getPrice_per_night());
            System.out.println("Start date availability: " + result.getStart_date_availability());
            System.out.println("End date availability: " + result.getEnd_date_availability());
            System.out.println("Availability: " + result.isAvailability());
            System.out.println("------------------------------------");
        }
        
        return searchResults;
    }
    

    public static boolean checkDateAvailability(String startAvailability, String endAvailability, String checkInDate, String checkOutDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date startDateAvailability = dateFormat.parse(startAvailability);
            Date endDateAvailability = dateFormat.parse(endAvailability);
            Date startDate = dateFormat.parse(checkInDate);
            Date endDate = dateFormat.parse(checkOutDate);

            return startDateAvailability.before(startDate) && endDateAvailability.after(endDate);

        } 
        catch (ParseException e) 
        {
            e.printStackTrace(); 
            return false; 
        }
    }

}
