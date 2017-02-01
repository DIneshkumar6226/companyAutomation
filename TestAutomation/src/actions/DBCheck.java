package actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Connection.JdbcConnection;

public class DBCheck {

	
	static boolean flag = true;
	static ResultSet rs = null;
	public static boolean DBvaluecheck(String query,String value,String event)
	{
		
		try
		{
			
			
		
	
			
		String[] elements=query.split(":");
	
			String j;
			
		
				
					
					elements[1]=elements[1].replaceAll("\r", "").replace("Condition","").trim();
				if(elements[1].contains("\n"))
				{
					elements[1]=elements[1].replace("\n",",").trim();	
				}
				
				
					 j =elements[2].replaceAll("\r", "").replace("Condition","").replace("\n"," AND ");
					 elements[2] =j.substring(4,j.length()).trim();
			
			
//					elements[1]=elements[1].replaceAll("\r", "").replace("Condition","").replace("\n","").trim();*/
			
				
			
			
		 j="SELECT COUNT(*) from "+elements[1]+" WHERE "+elements[2];
			System.out.println(j);
		Statement stmt = JdbcConnection.jdbcorcaleconnection().createStatement();
	 
		
		System.out.println("achived");
		 rs   =  stmt.executeQuery(j);
		      
		 
		    	  if(rs.next())
		    	  {
	
		    		 int count = rs.getInt(1);
		    		
		    		 String countindb = String.valueOf(count);
		    		 if(countindb.matches(value))
		    		 {
		    			 flag = true;
		    		 }
		    		 else
		    		 {
		    			 flag = false;
		    			 if(event.equals("DeleteQuery") && countindb.equals("0"))
			    		  { System.out.println("infero");
			    			  flag = true; 
			    		  }
		    			 
		    		 }
		    		  
		    	  }
		    	  else
		    	  {
		    		  flag = false;
		    		  
		    		 
		    		 
		    	  }
		          
		     
			
		
		//String connString="jdbc:oracle:thin:@prodHost:1521:ORCL";
		
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.228:1521:yrlirls","YRLIRLS_PH2_TST2","YrlPh2014");
			
			

		} catch (SQLException e) {
			 flag = false;
			System.out.println("Connection Failed! Check output consolem,Please check the query u have passed and the data base crendentials u have given");
			
			

		}
		
		
		
		
		
		return flag;
	}
}
