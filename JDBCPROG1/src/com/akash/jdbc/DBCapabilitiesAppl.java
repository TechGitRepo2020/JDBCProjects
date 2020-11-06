package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCapabilitiesAppl {

	public static void main(String[] args) {
		Connection con=null;
		DatabaseMetaData dbmd=null;
		try
		{
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//getMetaData
			dbmd=con.getMetaData();
			//database details are
			if(dbmd!=null) {
				System.out.println("db name::"+dbmd.getDatabaseProductName());
				System.out.println("db version::"+dbmd.getDatabaseProductVersion());
				System.out.println("db major version::"+dbmd.getDatabaseMajorVersion());
				System.out.println("db minor version::"+dbmd.getDatabaseMinorVersion());
				System.out.println("all system function::"+dbmd.getSystemFunctions());
				System.out.println("all numeric function::"+dbmd.getNumericFunctions());
				System.out.println("all String funtion::"+dbmd.getStringFunctions());
				System.out.println("max rowSize::"+dbmd.getMaxRowSize());
			}	
				
		}catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf)	{
			cnf.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			
			
			try {
				if(con!=null)
				   con.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
		}


	}

}
