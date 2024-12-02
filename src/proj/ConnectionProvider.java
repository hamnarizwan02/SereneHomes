package proj;
import java.sql.*;

public class ConnectionProvider {

	public static Connection getConnection() 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SereneHomes", "root", "poiuy12345");
			return con;
			
		}
		catch(Exception ee)
    	{
			System.out.println(ee);
		}
		return null;
	}
}
