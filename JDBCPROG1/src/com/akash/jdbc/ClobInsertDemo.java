package com.akash.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//
//CREATE TABLE "SCOTT"."TEXT_INSERT_TAB" 
//(	"NO" NUMBER NOT NULL ENABLE, 
//	"NAME" VARCHAR2(20 BYTE), 
//	"ADDRS" VARCHAR2(20 BYTE), 
//	"COLUMN1" CLOB, 
//	 CONSTRAINT "TEXT_INSERT_TAB_PK" PRIMARY KEY ("NO")


import java.util.Scanner;

public class ClobInsertDemo {

	
	private static final String  CLOB_INSERT_QUERY="INSERT INTO TEXT_INSERT_TAB VALUES(?,?,?,?) ";
	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		Reader reader=null;
		int result=0,no=0;
		String name=null,addr=null,path=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter no::");
			     no=sc.nextInt();
				System.out.println("Enter name::");
			     name=sc.next();
			     System.out.println("Enter address::");
			     addr=sc.next();
			     System.out.println("Enter resumePath::");
			     path=sc.next();
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			if(con!=null)
			   ps=con.prepareCall(CLOB_INSERT_QUERY);
			
			file=new File(path);
			reader=new FileReader(file);
			if(ps!=null)
			{
				ps.setInt(1, no);
			   ps.setString(2, name);
			   ps.setString(3, addr);
			   ps.setCharacterStream(4,reader,(int)file.length());
			}
			if(ps!=null)
				result=ps.executeUpdate();
			
			if(result==0)
				System.out.println("record not inserted");
			else
				System.out.println("record inserted");
					
		}catch(SQLException se)
		{
			
			se.printStackTrace();
			System.out.println("record Insertion failed");
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
			System.out.println("record Insertion failed");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("record Insertion failed");
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
			
			try {
				if(reader!=null)
				{
					reader.close();
				}
			}
			catch(Exception ioe) {
				ioe.printStackTrace();
			}
			
		}
	}
}
		
		 
		
		
		
		
		
	