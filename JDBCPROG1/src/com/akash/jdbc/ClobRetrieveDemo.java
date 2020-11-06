package com.akash.jdbc;

import java.io.FileWriter;

import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//
//CREATE TABLE "SCOTT"."TEXT_INSERT_TAB" 
//(	"NO" NUMBER NOT NULL ENABLE, 
//	"NAME" VARCHAR2(20 BYTE), 
//	"ADDRS" VARCHAR2(20 BYTE), 
//	"COLUMN1" CLOB, 
//	 CONSTRAINT "TEXT_INSERT_TAB_PK" PRIMARY KEY ("NO")


import java.util.Scanner;

public class ClobRetrieveDemo {

	
	private static final String  CLOB_RETRIEVE_QUERY=" SELECT * FROM TEXT_INSERT_TAB WHERE NO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Writer writer=null;
		Reader reader=null;
		char[] buffer=null;
		int charRead=0;
		int no=0;
		try {
		   sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("enter no::");
				no=sc.nextInt();
			}
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			if(con!=null)
				ps=con.prepareCall(CLOB_RETRIEVE_QUERY);
			if(ps!=null)
					ps.setInt(1, no);
			if(ps!=null)
				rs=ps.executeQuery();
			
			//process resultset and read BLOB value
			
			if(rs!=null)
		    {
				    if(rs.next())
				    {
				    	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				    	reader=rs.getCharacterStream(4);
				    }
		    	    else
		    	    	System.out.println("record not found");
		    
		    }
				
			writer=new FileWriter("new_text1.txt");
			
			//write buffer base logic..
			buffer=new char[2048];
		
			if(reader !=null && writer !=null)
			{
				while((charRead=reader.read(buffer))!=-1)
				{
				   writer.write(buffer,0,charRead);	
				}	
			}
			
			
		} catch (SQLException se) {
		
			se.printStackTrace();
			
		}
		catch(ClassNotFoundException cnf) {
			
			cnf.printStackTrace();
			
		}
		catch(Exception e) {
			
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
			
			try {
				if(reader!=null)
				{
					reader.close();
				}
			}
			catch(Exception ioe) {
				ioe.printStackTrace();
			}
			
			try {
				if(writer!=null)
				{
					writer.close();
				}
			}
			catch(Exception ioe) {
				ioe.printStackTrace();
			}
			
		}

		
		
		
		
		
		
		
	}
}
		
		 
		
		
		
		
		
	