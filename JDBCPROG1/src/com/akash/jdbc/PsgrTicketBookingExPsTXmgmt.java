package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*CREATE SEQUENCE  "SCOTT"."TKTID_SEQ"  MINVALUE 1 MAXVALUE 10000 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
 
 *  CREATE TABLE "SCOTT"."INDIAN_RAILWAY_RESERVATION" 
   (	"TKTID" NUMBER NOT NULL ENABLE, 
	"PSNGRNAME" VARCHAR2(20 BYTE), 
	"SRCPLACE" VARCHAR2(20 BYTE), 
	"DESTPLACE" VARCHAR2(20 BYTE), 
	"FARE" FLOAT(126), 
	 CONSTRAINT "INDIAN_RAILWAY_RESERVATION_PK" PRIMARY KEY ("TKTID") 
 * */
public class PsgrTicketBookingExPsTXmgmt {
private static final String INSERT_RESERVATION_QUERY ="INSERT INTO INDIAN_RAILWAY_RESERVATION VALUES(TKTID_SEQ.NEXTVAL,?,?,?,?) ";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		String psngrname=null,srcplace=null,destplace=null;
		PreparedStatement ps=null;
		float fare=0.0f;
		int count=0;
		int result[]=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter passenger count::");
				count=sc.nextInt();
				System.out.println("Enter source place::");
				srcplace=sc.next();
				System.out.println("Enter destination place::");
				destplace=sc.next();
				System.out.println("Enter fare::");
				fare=sc.nextFloat();  
			}
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//create Connection obj
			if(con!=null) 
				ps=con.prepareStatement(INSERT_RESERVATION_QUERY);
			if(ps!=null) {
				for(int i=1;i<=count;++i){
					System.out.println("Enter"+i+"passenger name::");
					psngrname=sc.next();
					ps.setString(1, psngrname);
					ps.setString(2, srcplace);
					ps.setString(3, destplace);
					ps.setFloat(4, fare);
					ps.addBatch();				
				}
				result=ps.executeBatch();
						if(result!=null)
							System.out.println("Ticket Booked");
						else
							System.out.println("Ticket not Booked");
				}
		}
			catch(SQLException se){
						se.printStackTrace();			
			}
			catch(ClassNotFoundException cnf)	{
						cnf.printStackTrace();
			}
			catch(Exception e) {
							e.printStackTrace();
			}
			finally {
										
				try {
						if(ps!=null)
							ps.close();
					}
					catch(SQLException se) {
						se.printStackTrace();				
					}
				
				try {
					if(con!=null)
						con.close();
					}
					catch(SQLException se) {
						se.printStackTrace();				
					}
				
				try {
					if(sc!=null)
						sc.close();
					}
					catch(Exception se) {
						se.printStackTrace();				
					}
						
			}//finally
			


	}

}
