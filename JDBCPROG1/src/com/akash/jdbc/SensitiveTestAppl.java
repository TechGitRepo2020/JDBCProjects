package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveTestAppl {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;		
		int count=0;
		try
		{
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//createStatement obj
			if(con!=null)
		       	st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD,SAVG FROM STUDENT");
			System.out.println("modify data from sql prompt");
			if(rs!=null){
					while(rs.next()){
						if(count==0)
							Thread.sleep(40000);//during this modify database record..
						    rs.refreshRow();
						  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
						  count++;
						}
			}
		}catch(SQLException se){
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf)	{
			cnf.printStackTrace();
		}
		catch(Exception e){
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


	}

}
