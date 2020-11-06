package com.akash.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollGUIAppl extends JFrame implements ActionListener{
	private static final String GET_RECORD_QUERY="SELECT SNO,SNAME,SADD,SAVG FROM STUDENT";
	private JLabel lno,lname,ladd,lavg;
	private JTextField tno,tname,tadd,tavg;
	private JButton bfirst,bnext,bprevious,blast;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	
	public ScrollGUIAppl() {
		System.out.println("ScrollGUIAppl:: 0-param contructor..)");
		setTitle("ScrollFrame appl");
		setBackground(Color.yellow);
		setSize(300,300);
		setLayout( new FlowLayout());
				
		//add comps
		lno=new JLabel("student no:"); 
		add(lno);
		tno=new JTextField(10);
		add(tno);
		
		lname=new JLabel("student name:"); 
		add(lname);
		tname=new JTextField(10);
		add(tname);
		
		ladd=new JLabel("Address:"); 
		add(ladd);
		tadd=new JTextField(10);
		add(tadd);
		
		lavg=new JLabel("Average"); 
		add(lavg);
		tavg=new JTextField(10);
		add(tavg);
		
		//add button
		bfirst=new JButton("First");
		add(bfirst);
		bfirst.addActionListener(this);
		
		bnext=new JButton("Next");
		add(bnext);
		bnext.addActionListener(this);
		
		bprevious=new JButton("Previous");
		add(bprevious);
		bprevious.addActionListener(this);
		
		blast=new JButton("Last");
		add(blast);
		blast.addActionListener(this);
		
		//disable editable
		tno.setEditable(false);
		tname.setEditable(false);
		tadd.setEditable(false);
		tavg.setEditable(false);
		
		
		jdbcInitialized();
		setVisible(true);
	
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent ae) {
				System.out.println("ScrollGUIAppl..new WindowAdapter() {...}.windowClosing()");
				//close jdbc obj
				try
				{
					if(rs!=null)
						rs.close();
					
				}catch(SQLException se)
				{
					se.printStackTrace();					
				}
				
				try
				{
					if(st!=null)
						st.close();
					
				}catch(SQLException se)
				{
					se.printStackTrace();					
				}
				
				try
				{
					if(con!=null)
						con.close();
					
				}catch(SQLException se)
				{
					se.printStackTrace();					
				}
				
			}//method
						
		});//class
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void jdbcInitialized() {
		
		try {
		//register jdbc driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		//create Statement
		if(con!=null)
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		//execute Query
		if(st!=null)
			rs=st.executeQuery(GET_RECORD_QUERY);
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		System.out.println("ScrollGUIAppl.main()..");
		new ScrollGUIAppl();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("ScrollGUIAppl.actionPerformed()");
		System.out.println(ae.getActionCommand());
		boolean flag=false;
		try {
			
				if(ae.getSource()==bfirst)
				{
					System.out.println("first button");
					rs.first();
					flag=true;
				}
				else if(ae.getSource()==bnext)
				{
					System.out.println("next button");
					if(!rs.isLast())
					{
						rs.next();
						flag=true;
					}//if
				}
				else if(ae.getSource()==blast)
				{
					System.out.println("last button");
					rs.last();
					flag=true;
					
				}
				else
				{
					System.out.println("previous button");
					if(!rs.isFirst())
					{
						rs.previous();
						flag=true;
					}//if
				}//else
			//set record to textfeild
				if(flag==true)
				{
				  tno.setText(rs.getString(1));	
				  tname.setText(rs.getString(2));	
				  tadd.setText(rs.getString(3));	
				  tavg.setText(rs.getString(4));	
				}//if
							
		}//try
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
