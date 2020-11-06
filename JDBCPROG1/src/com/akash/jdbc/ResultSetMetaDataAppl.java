package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataAppl {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;		
		ResultSetMetaData rsmd=null;
		int colcount=0;
		try
		{
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//createStatement obj
			if(con!=null)
		       	st=con.createStatement();
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM STUDENT");
			//create ResultSetMetadata obj
			rsmd=rs.getMetaData();
			//get column count
			colcount=rsmd.getColumnCount();
			
			//display col names
			for(int i=1;i<=colcount;i++) {
				System.out.print(rsmd.getColumnLabel(i)+"     ");
			}
			System.out.println();
				//display col types
			for(int i=1;i<=colcount;i++) {
				System.out.print(rsmd.getColumnTypeName(i)+"     ");
			}
			System.out.println();
					
			//procees resultset
			while(rs.next()) {
				for(int i=1;i<=colcount;i++) {
					System.out.print(rs.getString(i)+"     ");
				}
				System.out.println();
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
				if(rs!=null)
				   rs.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
				   st.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
				   con.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
		}//finally


	}//main

}//class
