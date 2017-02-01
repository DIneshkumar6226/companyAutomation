package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

import utility.InputBean;
import Excelsheet.DBdetails;



public class JdbcConnection {
static InputBean input = new InputBean();
	static Connection connection = null;
	public static Connection jdbcorcaleconnection()
	{
		try {	 
			
			input=	DBdetails.DBinputdetails();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("jdbc:oracle:thin:@"+input.getDbUrl()+":"+input.getPortnummber());


		//System.out.println("Oracle JDBC Driver Registered!");
		
	

		//connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:PRE_ATLANTIS","testuser","testuser");
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.13:1521/cp06", "testuser","testuser");
			
//System.out.println("jdbc:oracle:thin:@"+input.getDbUrl()+":"+input.getPortnummber()+"/"+input.getSsid());
			
			connection = DriverManager.getConnection("jdbc:oracle:thin:@"+input.getDbUrl()+":"+input.getPortnummber()+"/"+input.getSsid(),input.getUsername(),input.getPassword());
		
	}
		catch(Exception e)
		{
			System.out.println("ur database is cony connected");
		}
		return  connection;
}
	
}
