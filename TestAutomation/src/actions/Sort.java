package actions;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Connection.JdbcConnection;

public class Sort {
	static boolean flag = true;
	static ResultSet rs = null;
	
/*public static void main(String args[])
{
	
	String query="Select JISYK_KB,GAITO_NINZU,PICKUP_NINZU  From jinji.pickup   WHERE GAITO_NINZU  >= 900 order by JISYK_KB,GAITO_NINZU";
	
	DBvaluecheck(query);
	
	
}*/
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static List DBvaluecheck(String query)
	{
		List data = null;
		try
		{
			
	System.out.println(query);	
		
		int i;
		List<String> li = new ArrayList<String>();	
	String ager = query.replaceAll("Select","").replaceAll("DISTINCT","");
	System.out.println(ager);
	String a[]=ager.split(":");
	
	for(i=0;i<a.length;i++)
	{
		if(i!=(a.length))
		{
		System.out.println("a["+i+"] is"+a[i].trim() );
		
		if(a[i].contains("From"))
		{
			String[] b=a[i].split("From");
			System.out.println(b[0]+"the last one");
			li.add(b[0]);
		}
		
		else
		{
			System.out.println(a[i]);
			if(a[i].contains("AND"))
			{
				
			}
			else
			{
				li.add(a[i]);
			}
		}
		}
		
	}
		System.out.println(li.size()+"the size is"+li);
		Statement stmt = JdbcConnection.jdbcorcaleconnection().createStatement();
	 
		
		System.out.println("achived");
	
		DatabaseMetaData meta = JdbcConnection.jdbcorcaleconnection().getMetaData();
		List<String> list = new ArrayList<String>();
		Map<String,String> map = new HashMap<String, String>();
		
		query =query.replaceAll(":",",");
		query =query.replaceAll("%",",");
		
		System.out.println("hosuton the QUERY IS  is"+query);
		 rs   =  stmt.executeQuery(query);
		 ResultSetMetaData metadata =rs.getMetaData(); 
		 int columnCount = metadata.getColumnCount();
				 for ( i=1; i<=columnCount; i++) {
					 
					  String columnName = metadata.getColumnName(i);
					 //System.out.println(columnName+"the coloun name");
				String fieldtype=	  metadata.getColumnTypeName(i);
			//System.out.println(fieldtype);

				
				if(columnName.equals(li.get(i-1).trim()))
					  map.put(columnName, fieldtype);	  

		    	  }
		     
				 data = new ArrayList();

					while(rs.next())
					{ 
						for(i=0;i<li.size();i++)
						{
						
							String coloumtype = map.get(li.get(i).trim());
							//System.out.println(coloumtype+"we hit");
							System.out.println(li.get(i).trim()+"houston"+coloumtype);
							
							if(coloumtype.equals("VARCHAR2") || coloumtype.equals("CHAR"))
							{
								String appender =rs.getString(li.get(i).trim());
								
								if(appender==null || appender.isEmpty())
								{
									
									data.add("");
								}
								else
								{
									
							data.add(appender.trim());
								}
								
							}
							else if(coloumtype.equals("NUMBER"))
							{
								
								data.add(rs.getLong(li.get(i).trim()));
								
							}
							
							else if(coloumtype.contains("DATE"))
							{
								
								
								if(li.get(i).equals("CREATE_DATE")||li.get(i).equals("PRC_DATE"))
								{
									
								}
								else
								{
									//System.out.println("data added");
									
									//System.out.println(i);
									
									
								data.add(rs.getDate(li.get(i).trim()));
								
								}
							}	     
		
						}
					}
			
		
		//String connString="jdbc:oracle:thin:@prodHost:1521:ORCL";
		
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.228:1521:yrlirls","YRLIRLS_PH2_TST2","YrlPh2014");
			
			System.out.println(data);
			System.out.println(data.size());

		} catch (Exception e) {
			 flag = false;
			System.out.println("Connection Failed! Check output consolem,Please check the query u have passed and the data base crendentials u have given houston");
			e.printStackTrace();
			

		//}
	
	
	}
		return data;
	}
}
