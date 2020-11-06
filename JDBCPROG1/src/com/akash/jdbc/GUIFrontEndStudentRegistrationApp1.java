package com.akash.jdbc;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUIFrontEndStudentRegistrationApp1 extends JFrame implements ActionListener {
	private static final String  INSERT_STUDENT_QUERY="INSERT INTO STUDENT21_TAB VALUES(SNO_SEQ.NEXTVAL,?,?,?)";
	private  JLabel lname,laddrs,lavg,lresult;
	private JTextField  tname,taddrs,tavg;
	private JButton register;
	private Connection con;
	private PreparedStatement ps;
	
	public GUIFrontEndStudentRegistrationApp1() {
		System.out.println(" 0-param constructor");
	     setTitle("GUI-Student Registration App");
	     setBackground(Color.gray);
	     setLayout(new FlowLayout());
	     setSize(400,300);
	     //add comps
	     lname=new JLabel("sname");
	     add(lname);
	     tname=new JTextField(10);
	     add(tname);
	     
	     laddrs=new JLabel("address");
	     add(laddrs);
	     taddrs=new JTextField(10);
	     add(taddrs);
	     
	     lavg=new JLabel("Student marks Avg");
	     add(lavg);
	     tavg=new JTextField(10);
	     add(tavg);
	     
	     register=new JButton("register");
	     add(register);
	     register.addActionListener(this);
	     
	     lresult=new JLabel("result::");
         add(lresult);	    		 
         
              
	     //add windowListner
         addWindowListener(new WindowAdapter() {
        	 @Override
     		public void windowClosing(WindowEvent e) {
     			 System.out.println("GUIFrontEndStudentRegistrationApp1.MyWindowAdapter.windowClosing(-)");
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
     			
     		}   	 
		});
         
	     //invoke jdbcInitialize() 
	     jdbcInitialize();
	     
	     //set visiblity to ON
	     setVisible(true);
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void  jdbcInitialize() {
		System.out.println("jdbcInitialize()");
		try {
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			//create PrepraedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_STUDENT_QUERY);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
			lresult.setText("insert values to text box");
			System.out.println("insert values to text box");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(" start of main()");
		new GUIFrontEndStudentRegistrationApp1();
		System.out.println(" end of main()");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed(-)");
		String name=null,addrs=null;
		float avg=0.0f;
		int result=0;
		try {
			//read text box values
			name=tname.getText();
			addrs=taddrs.getText();
			avg=Float.parseFloat(tavg.getText());
			//set these values as query param values
			ps.setString(1,name);
			ps.setString(2,addrs);
			ps.setFloat(3,avg);
			//execute the Query
			result=ps.executeUpdate();
			//process Result
			if(result==1) {
				lresult.setForeground(Color.GREEN);
				lresult.setText("Registration Sucessfull");
			}
			else {
				lresult.setForeground(Color.RED);
				lresult.setText("Registration failed");
			}
		}//try
		catch(SQLException se) {
			lresult.setText("Registration failed");
			se.printStackTrace();
		}
		catch(NumberFormatException nfe) {
			lresult.setForeground(Color.RED);
			lresult.setText("insert values to text box");
			System.out.println("String empty! Filled Up with someValue Again");
			//	nfe.printStackTrace();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			//emtpty text boxes and lresult
			tname.setText("");
			tavg.setText("");
			taddrs.setText(""); 
		}
	}//actionPerformed(-)
	
	}//class
