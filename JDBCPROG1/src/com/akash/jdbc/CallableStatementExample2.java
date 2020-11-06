/*CREATE OR REPLACE PROCEDURE PROCEDURE_SQUARE 
(
  X IN NUMBER 
, SQUARE OUT NUMBER 
, CUBE OUT NUMBER 
) AS 
BEGIN
  square:=x*x;
  Cube:=x*x*x;
  
END ;*/


package com.akash.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementExample2 {

	private static final String CALL_SQ_CUBE_QUERY=" {CALL PROCEDURE_SQUARE(?,?,?)}"; 
	public static void main(String[] args) {
		
		Scanner sc=null;
		int no=0,square=0,cube=0;
		Connection con=null;
		CallableStatement cs=null;
		
		
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("enter a number:");
				no=sc.nextInt();
				
			}
			
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				
				if(con!=null)
						cs=con.prepareCall(CALL_SQ_CUBE_QUERY);
			
				if(cs!=null)
				{
						cs.registerOutParameter(2, Types.INTEGER);
						cs.registerOutParameter(3, Types.INTEGER);
				}
				
				if(cs!=null)
						cs.setInt(1, no);
				
				if(cs!=null)
					cs.execute();
				
				square=cs.getInt(2);
				cube=cs.getInt(3);
				System.out.println("SQUARE:::"+square);
				System.out.println("CUBE:::"+cube);
						
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
