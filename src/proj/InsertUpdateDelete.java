package proj;
import java.sql.*;

import javax.swing.JOptionPane;

public class InsertUpdateDelete
{
	public static void setData(String Query, String msg)
	{
		Connection sqlcon = null;
		Statement st = null;
		
		try 
		{
			sqlcon = ConnectionProvider.getConnection();
			st = sqlcon.createStatement();
			st.executeUpdate(Query);
			if(!msg.equals("")) 
			{
				JOptionPane.showMessageDialog(null, msg);
			}
			
		}
		catch(Exception ex) 
		{
			JOptionPane.showMessageDialog(null, ex);
		}
		finally
		{
			try {}
			catch(Exception ee) {}
		}
		
	}

}
