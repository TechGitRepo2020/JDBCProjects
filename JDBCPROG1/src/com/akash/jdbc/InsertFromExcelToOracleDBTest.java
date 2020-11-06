package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertFromExcelToOracleDBTest {
	private static final String INSERT_ORACLE_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Connection oracon=null;
		Connection xlscon=null;
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		int res=0;
			try
			{
				//register driver
				Class.forName("com.hxtt.sql.excel.ExcelDriver");
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//establish Connection
				xlscon=DriverManager.getConnection("jdbc:Excel:///F:\\SOFTWARE\\NTAJ413");
				oracon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				//createStatement obj
				if(xlscon!=null)
					st=xlscon.createStatement();   
				//
				if(st!=null)
					rs=st.executeQuery("SELECT * FROM COLLEGE1.SHEET1");				
				//create PreparedStatement obj
				if(oracon!=null)
					ps=oracon.prepareStatement(INSERT_ORACLE_QUERY);
				//get record from resultset
				if(rs!=null &&ps!=null){
					while(rs.next()) {
						ps.setInt(1,rs.getInt(1));
						ps.setString(2, rs.getString(2));
						ps.setString(3, rs.getString(3));
						ps.setFloat(4, rs.getFloat(4));
					}
				}
			if(ps!=null)
				res=ps.executeUpdate();
			if(res==0){
					System.out.println("record not inserted");
			}
			else {
				System.out.println("record inserted successfully..");
			}
			
		}//try
			catch(SQLException se)
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
					if(st!=null)
					   st.close();
				}catch(SQLException se)
				{
					se.printStackTrace();
				}
				
			
			try {
				if(oracon!=null)
				   oracon.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			
			try {
				if(xlscon!=null)
				   xlscon.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}//catch
		
	}//finally
		
	}//main

}//class
