

package com.akash.jdbc;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI_Miniproject extends JFrame implements ActionListener{
	private static final String st_Query="SELECT SNO FROM ALL_STUDENT";
	private static final String ps_Query="SELECT * FROM ALL_STUDENT WHERE SNO=?";
	private static final String cs_Query="{CALL P_PASS_FAIL(?,?,?,?)}";
	
	private JLabel lno,lname,lm1,lm2,lm3,lresult;
	private JTextField tname,tm1,tm2,tm3,tresult;
	private JButton bdetail,bresult;
	private JComboBox<Integer> cbno;
	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private CallableStatement cs;
	private ResultSet rs1,rs2;
	
	
	public GUI_Miniproject() {
		System.out.println("0-param constructor..");
		System.out.println("GUI_Miniproject.GUI_Miniproject()");
		
		setTitle("MINI PROJECT USING ALL STATEMENT");
		setSize(400, 300);
		setLayout(new FlowLayout());
		setBackground(Color.gray);
			//Add component	
		  lno=new JLabel("Student Id");
		  add(lno);
		 cbno=new JComboBox<Integer>();
		 add(cbno);
		 
		 bdetail=new JButton("Details");
		 bdetail.addActionListener(this);
		 add(bdetail);
		  
		  lname=new JLabel("Student name");
		 add(lname);
		 tname=new JTextField(10);
		 add(tname);
		 
		 lm1=new JLabel("marks m1");
		 add(lm1);
		 tm1=new JTextField(10);
		 add(tm1);
		
		 lm2=new JLabel("marks m2");
		 add(lm2);
		 tm2=new JTextField(10);
		 add(tm2);
		 
		 lm3=new JLabel("marks m3");
		 add(lm3);
		 tm3=new JTextField(10);
		 add(tm3);
		
		 bresult=new JButton("Result");
		 bresult.addActionListener(this);
		 add(bresult);
		 tresult=new JTextField(10);
		 add(tresult);
		
		 
		 		
		setVisible(true);
		
		jdbcInitiazed();
		
		 this.addWindowListener(new WindowAdapter() {
			 
			 @Override
			public void windowClosing(WindowEvent arg0) {
					System.out.println("ScrollGUIAppl..new WindowAdapter() {...}.windowClosing()");
					//close jdbc obj
					try
					{
						if(rs1!=null)
							rs1.close();
						
					}catch(SQLException se)
					{
						se.printStackTrace();					
					}
					
					try
					{
						if(rs2!=null)
							rs2.close();
						
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
						if(ps!=null)
							ps.close();
						
					}catch(SQLException se)
					{
						se.printStackTrace();					
					}
					
					try
					{
						if(cs!=null)
							cs.close();
						
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
		});
		 
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 
	}//constructor close
	
	private void jdbcInitiazed()
	{
		System.out.println("GUI_Miniproject.jdbcInitiazed()..");
		try
		{
			//register jdbc driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			//simple statemnet
			if(con!=null)
				st=con.createStatement();
		   //prepared statemnet object
			if(con!=null)	
				ps=con.prepareStatement(ps_Query);
			//callable statement obj
			if(con!=null) {
				cs=con.prepareCall(cs_Query);
				cs.registerOutParameter(4, Types.VARCHAR);
				
			}
			rs1=st.executeQuery(st_Query);
			if(rs1!=null)
			{
				while(rs1.next())
				{
					cbno.addItem(rs1.getInt(1));
				}
			}		
			//
			
			
			
			
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
		
	}
	
	public static void main(String[] args) {
		System.out.println("GUI_Miniproject.main()");
		new GUI_Miniproject();		
	
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("GUI_Miniproject.actionPerformed()");
		int m1=0,m2=0,m3=0;
		String res=null;
		
		if(ae.getSource()==bdetail)
		{
			System.out.println("Detail button pressed");
			int no=(int) cbno.getSelectedItem();
			try {
			    ps.setInt(1, no);
			    rs2=ps.executeQuery();
			    if(rs2.next()) {
			    tname.setText(rs2.getString(2));
			    tm1.setText(rs2.getString(3));
			    tm2.setText(rs2.getString(4));
			    tm3.setText(rs2.getString(5));
			    }
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
			
		}
		else
		{
			System.out.println("Result button pressed");
			m1=Integer.parseInt(tm1.getText());
			m2=Integer.parseInt(tm2.getText());
			m3=Integer.parseInt(tm3.getText());
			
			//
			try {
					cs.setInt(1,m1);
					cs.setInt(2,m2);
					cs.setInt(3,m3);
					//
					cs.execute();
					res=cs.getString(4);
					tresult.setText(res);
					
			}
			catch (SQLException e) {
					e.printStackTrace();
			}
			
		}
			
				
	}
	
	
	

}
