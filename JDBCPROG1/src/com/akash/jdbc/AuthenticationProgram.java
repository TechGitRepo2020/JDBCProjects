package com.akash.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class AuthenticationProgram {
	private static final String PRO_CALL="{CALL  P_AUTH_TABLE(?,?,?)} ";

	public static void main(String[] args) {
		Scanner sc=null;
		String uname=null;
		String pwd=null;
		Connection con=null;
		CallableStatement cs=null;
		String res=null;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("enter uname::");
				uname=sc.next();
				System.out.println("enter password:");
				pwd=sc.next();
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			
			//
			if(con!=null)
			   cs=con.prepareCall(PRO_CALL);
			if(cs!=null)
			{
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.setString(1,uname);
			    cs.setString(2,pwd);
			}
			if(cs!=null)
				cs.execute();
			
			res=cs.getString(3);
			System.out.println(res);
			
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
