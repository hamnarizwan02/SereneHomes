package businessLogicLayer;

public class Users 
{
	private String name;
    private String email;
    private String password;
    private String phoneNumber;
    
    public Users(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    
    //setters
    public void setName(String n) {
    	name = n;
    }
    public void setEmail(String e) {
    	email = e;
    }
    public void setPassword(String p) {
    	password = p;
    }
    public void setNumber(String phone) {
    	phoneNumber = phone;
    }
    
    //getters
    public String getName() {
    	return name;
    }
    public String getEmail() {
    	return email;
    }
    public String getPassword() {
    	return password;
    }
    public String getNumber() {
    	return phoneNumber;
    }
}
