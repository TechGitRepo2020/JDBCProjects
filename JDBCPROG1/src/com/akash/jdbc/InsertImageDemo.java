package com.akash.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertImageDemo {
	private static final String INSERT_QUERY="INSERT INTO IMAGE_TAB VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,gender=null,path=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		InputStream is=null;
		int result=0;
		int age=0;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("enter name:");
				name=sc.next();
				System.out.println("enter age:");
				age=sc.nextInt();
				System.out.println("enter gender:");
				gender=sc.next();
				System.out.println("enter path:");
				path=sc.next();
							
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			file=new File(path);
			is=new FileInputStream(file);				
			if(ps!=null)
			{
				ps.setString(1, name);
				ps.setInt(2, age);
				ps.setString(3, gender);
				ps.setBlob(4,is);
							
			}
			result=ps.executeUpdate();
			
			if(result==0)
			{
				System.out.println("record not inserted...");
			}
			else
			{
				System.out.println("record inserted");
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
		finally
		{
			try {
				if(ps!=null)
				{
					ps.close();
				}
			}
			catch(SQLException se) 
			{
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
				{
					sc.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
	}

}
