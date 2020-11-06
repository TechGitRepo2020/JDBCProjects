package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertExcelTest {
	private static final String INSERT_EXCEL_QUERY="INSERT INTO COLLEGE1.SHEET1 VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Connection con=null;
		Scanner sc=null;
		int no=0;
		String name=null,addr=null;
		float avg=0.0f;
		PreparedStatement ps=null;
		int res=0;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter student no::");
				no=sc.nextInt();
				System.out.println("enter student name::");
				name=sc.next();
				System.out.println("enter student Address::");
			     addr=sc.next();
			    System.out.println("enter student average::");
				avg=sc.nextFloat();
			}
			//register driver
			Class.forName("com.hxtt.sql.excel.ExcelDriver");
			//establish Connection
			con=DriverManager.getConnection("jdbc:Excel:///F:\\SOFTWARE\\NTAJ413");
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(INSERT_EXCEL_QUERY);
			if(ps!=null){
				ps.setInt(1, no);
				ps.setString(2, name);
				ps.setString(3, addr);
				ps.setFloat(4, avg);
			}
			if(ps!=null)
				res=ps.executeUpdate();
			if(res==0){
					System.out.println("record not inserted");
			}
			else {
				System.out.println("record inserted successfully..");
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
