package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableTest {
private static final String SELECT_QUERY="SELECT * FROM STUDENT";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;		
		try
		{
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//createStatement obj
			if(con!=null)
				ps=con.prepareStatement(SELECT_QUERY,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);   
			if(ps!=null)
				rs=ps.executeQuery();
			if(rs!=null){
				System.out.println("top to bottom--");
					while(rs.next()){
						  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
						}
					
				System.out.println("bottom--top");
					while(rs.previous()){
						  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
						}
					
					System.out.println("----------------------");
//first record
					rs.first();
						  System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
//last record
					rs.last();
					  System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
					  //3 rd record from top
					  rs.absolute(3);
					  System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
					  
					  //3 record from bottom
					  rs.absolute(-3);
					  System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
						
					  rs.relative(2);
					  System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));

						rs.relative(-4);
					  System.out.println(rs.getRow()+"----->"+rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));

										  
						  
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
				if(con!=null)
				   con.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
		}


	}//main

}//class
