package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcelTestDriver {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;		
		try
		{
			//register driver
			Class.forName("com.hxtt.sql.excel.ExcelDriver");
			//establish Connection
			con=DriverManager.getConnection("jdbc:Excel:///F:\\SOFTWARE\\NTAJ413");
			//createStatement obj
			if(con!=null)
				st=con.createStatement();   
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM COLLEGE1.SHEET1");
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
		}
		
	}//main

}//class
