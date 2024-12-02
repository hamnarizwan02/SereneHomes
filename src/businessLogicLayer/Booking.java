package businessLogicLayer;

public class Booking 
{
    private int bookingId;
    private int userId;
    private int propertyId;
    private String checkInDate;
    private String checkOutDate;

    // Constructors
    public Booking() {
        // Default constructor
    }

    public Booking(int bookingId, int userId, int propertyId, String checkInDate, String checkOutDate) 
    {
        this.bookingId = bookingId;
        this.userId = userId;
        this.propertyId = propertyId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public int getBookingId()
    {
        return bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    // Setters
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}


