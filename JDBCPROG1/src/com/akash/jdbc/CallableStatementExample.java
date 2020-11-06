/*CREATE OR REPLACE PROCEDURE PRO_CALL 
(
  X IN NUMBER 
, Y IN NUMBER 
, Z IN OUT NUMBER 
) AS 
BEGIN
z:=x*y;

END ;*/



package com.akash.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementExample {

	private static final String CALL_QUERY=" {CALL PRO_CALL(?,?,?)}"; 
	public static void main(String[] args) {
		
		Scanner sc=null;
		int first=0,second=0,z=0;
		Connection con=null;
		CallableStatement cs=null;
		int res=0;
		
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("enter first value:");
				first=sc.nextInt();
				System.out.println("enter second value:");
				second=sc.nextInt();
			}
			
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				
				if(con!=null)
						cs=con.prepareCall(CALL_QUERY);
			
				if(cs!=null)
						cs.registerOutParameter(3, Types.INTEGER);
				
				if(cs!=null)
				{
						cs.setInt(1, first);
						cs.setInt(2, second);
				}
				if(cs!=null)
					cs.execute();
				
				res=cs.getInt(3);
				System.out.println("result::"+res);
						
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
				if(cs!=null)
				{
					cs.close();
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
