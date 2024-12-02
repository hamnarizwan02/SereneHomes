package businessLogicLayer;
import java.util.*;

public class Property
{
    private int propertyID;
    private int ownerID;
    private String name;
    private String location;
    private double price_per_night;
    private String start_date_availability;  // year-month-date
    private String end_date_availability;
    private boolean availability;

    // Constructor
    public Property(int propertyID, int ownerID, String name, String location,
                    double price_per_night, String start_date_availability,
                    String end_date_availability, boolean availability)
    {
        this.propertyID = propertyID;
        this.ownerID = ownerID;
        this.name = name;
        this.location = location;
        this.price_per_night = price_per_night;
        this.start_date_availability = start_date_availability;
        this.end_date_availability = end_date_availability;
        this.availability = availability;
    }

    // Getters
    public int getPropertyID() {
        return propertyID;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice_per_night() {
        return price_per_night;
    }

    public String getStart_date_availability() {
        return start_date_availability;
    }

    public String getEnd_date_availability() {
        return end_date_availability;
    }

    public boolean isAvailability() {
        return availability;
    }

    // Setters
    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice_per_night(double price_per_night) {
        this.price_per_night = price_per_night;
    }

    public void setStart_date_availability(String start_date_availability) {
        this.start_date_availability = start_date_availability;
    }

    public void setEnd_date_availability(String end_date_availability) {
        this.end_date_availability = end_date_availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}

