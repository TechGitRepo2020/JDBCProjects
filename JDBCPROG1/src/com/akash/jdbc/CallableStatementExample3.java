package com.akash.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMPLOYEE_DETAILS 
(
  NO IN NUMBER 
, NAME OUT VARCHAR2 
, SAL OUT NUMBER 
) AS 
BEGIN
  select ename,sal into name,sal from emp where empno=no;
END P_GET_EMPLOYEE_DETAILS;*/



public class CallableStatementExample3 {
	
	private static final String CALL_QUERY="{CALL P_GET_EMPLOYEE_DETAILS(?,?,?)}";
	public static void main(String[] args) {
		
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		try
		{
			sc=new Scanner(System.in);
					if(sc!=null)
					{
						System.out.println("Enter no::");
						no=sc.nextInt();
					}
					//register jdbc driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl:","scott","tiger");
					
					if(con!=null)
						cs=con.prepareCall(CALL_QUERY);
					if(cs!=null)
					{
					  cs.registerOutParameter(2, Types.VARCHAR);
					  cs.registerOutParameter(3, Types.INTEGER);
					}
					//
					if(cs!=null)
						cs.setInt(1, no);
					if(cs!=null)
						cs.execute();
					
					if(cs!=null)
					{
						System.out.println("EMPname::"+cs.getString(2));
						System.out.println("EMPsal::"+cs.getInt(3));
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
