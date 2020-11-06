package com.akash.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//CREATE TABLE "SCOTT"."BANK_DB_TABLE"(	"ACNO" NUMBER(10,0) NOT NULL ENABLE, 
//	"HOLDERNAME" VARCHAR2(20 BYTE), "BALANCE" FLOAT(126), 
//	 CONSTRAINT "BANK_DB_TABLE_PK" PRIMARY KEY ("ACNO")

import java.util.Scanner;

public class BatchProcessingTxmgmtBankEx {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int srcAccount=0,destAccount=0;
		float amount=0.0f;
		int result[]=null;
		boolean flag=false;
		try
		{
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Source AccountNo::");
				srcAccount=sc.nextInt();
				System.out.println("Enter Destination AccountNo::");
				destAccount=sc.nextInt();
				System.out.println("Enter Amount::");
				amount=sc.nextFloat();
			}
			//register jdbc Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			//begin tx
			if(con!=null) {
				con.setAutoCommit(false);
				//create Statement obj
				st=con.createStatement();			
			}
			if(st!=null){
				//ADD QUERIES TO BATCH
				//withdraw query
				st.addBatch("UPDATE BANK_DB_TABLE SET BALANCE=BALANCE-"+amount+"WHERE ACNO="+srcAccount);
				//deposit query
				st.addBatch("UPDATE BANK_DB_TABLE SET BALANCE=BALANCE+"+amount+"WHERE ACNO="+destAccount);
			}
			//execute batch
			if(st!=null)
				result=st.executeBatch();
			//perform txmgmt(commit or rollback)
			if(result!=null){
				for(int i=0;i<result.length;++i) {
					if(result[i]==0)	{
						flag=true;
						break;					
					}//if
				}//for
												
			}//if
						
			
		}//try
		catch(SQLException se){
			flag=true;
			se.printStackTrace();			
		}
		catch(ClassNotFoundException cnf)	{
			flag=true;
			cnf.printStackTrace();
		}
		catch(Exception e) {
			flag=true;
			e.printStackTrace();
		}
		finally {
					try {
						if(flag==true){
							con.rollback();
							System.out.println("Tx is rolled back--money transfer failed");
							
						}
						else {
							con.commit();
							System.out.println("Tx is commited--money tranfer successful");			
							
						}
					}catch(SQLException se){
						se.printStackTrace();
					}
			
			try {
					if(st!=null)
						st.close();
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
		
	}//main

}//class
