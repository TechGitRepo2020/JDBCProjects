package com.akash.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsTest {

	public static void main(String[] args) {
		InputStream is=null;
		Properties props=null;
		try {
				//locate file using file inputSteram
				is=new FileInputStream("src/com/nt/commons/personalDetails.properties");
				//load propertis file data into java.util.properties class object
				props=new Properties();
				props.load(is);
				System.out.println("props data:: " +props);
				System.out.println("name key value::   "+props.getProperty("name"));
				System.out.println("age key value::      "+props.getProperty("age"));
				System.out.println("address key value::"+props.getProperty("address"));
		
		}catch(IOException ie)
		{
			ie.printStackTrace();
		}
		catch(Exception  e)
		{
			e.printStackTrace();
		}
	}//main

}//class
