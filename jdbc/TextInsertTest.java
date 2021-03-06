package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TextInsertTest {
  private static final String  INSERT_TEXT_RECORDS="INSERT INTO file1 VALUES(?,?,?)";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=null;
		int no=0;
		String name=null,addrs=null;
		int count=0;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Sno::");
				no=sc.nextInt();
				System.out.println("Enter sname::");
				name=sc.next();
				System.out.println("Enter sadd::");
				addrs=sc.next();
			}
			//register JDBC driver s/w (optional)
			Class.forName("com.hxtt.sql.text.TextDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:Text:///e:/apps/TextDB");
			//create PreparedStatement object
			if(con!=null)
			   ps=con.prepareStatement(INSERT_TEXT_RECORDS);
			//set query param values
			if(ps!=null) {
				ps.setInt(1,no);
				ps.setString(2,name);
				ps.setString(3,addrs);
			}
			//execute the Query
			if(ps!=null)
				count=ps.executeUpdate();
			
			if(count==0)
				System.out.println("Data not inserted");
			else
				System.out.println("Data inserted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
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
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
