package com.akash.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class FlexibleTest {

	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		
		InputStream is=null;
		Properties props=null;
		try
		{
			//locate properties file
			is=new FileInputStream("src/com/nt/commons/jdbc.properties");
			//load properties file data into java.utils.properties file
			props=new Properties();
			props.load(is);
			//register driver
			Class.forName(props.getProperty("jdbc.driver"));
			//establish Connection
			con=DriverManager.getConnection(props.getProperty("jdbc.url"),
																  props.getProperty("jdbc.user"),
																  props.getProperty("jdbc.pwd"));
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement("SELECT * FROM STUDENT");
			if(ps!=null)
				rs=ps.executeQuery();
			if(rs!=null){
					while(rs.next()){
						  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
						}
			}
			
		}catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
		}
		catch(Exception e)
		{
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
				if(ps!=null)
				   ps.close();
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
		}
		
	}//main

}//class
