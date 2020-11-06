package com.akash.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RetrieveDemo {

	private static final String RETRIEVE_IMAGE="SELECT * FROM IMAGE_TAB WHERE PID=?";
	
	public static void main(String[] args) {
		Scanner sc=null;
		int id=0;
		Connection con=null;
		PreparedStatement ps=null;
		InputStream is=null;
		OutputStream os=null;
		ResultSet rs=null;
		byte[] buffer=new byte[4096];
		int bytesRead=0;
		try
		{
			sc=new Scanner(System.in);
				    if(sc!=null)
				    {
				    	System.out.println("enter id::");
				    	id=sc.nextInt();
				    }
				    //load jdbc driver
				    Class.forName("oracle.jdbc.driver.OracleDriver");
				    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
				    
				    if(con!=null)
				       ps=con.prepareStatement(RETRIEVE_IMAGE);
				    if(ps!=null)
				       ps.setInt(1, id);
				    if(ps!=null)
				       rs=ps.executeQuery();
				    if(rs!=null)
				    {
						    if(rs.next())
						    {
						    	System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
						    	is=rs.getBinaryStream(5);
						    }
				    	    else
				    	    	System.out.println("record not found");
				    
				    }
				    os=new FileOutputStream("new_img1.jpg");
						
						if(is!=null && os!=null)
						{
							while((bytesRead=is.read(buffer))!=-1)
							{
								os.write(buffer,0,bytesRead);
								
							}
						}
				    	    
				    
		}
		catch(SQLException se)
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
