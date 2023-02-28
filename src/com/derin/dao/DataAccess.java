package com.derin.dao;

import java.sql.*;

import com.derin.model.Customer;

public class DataAccess
{
	private  static  final String dbUrl = "jdbc:mysql://localhost:3306/servlet";
	private  static  final String dbUsername = "root";
	private  static  final String dbPassword = "admin";
	
	private static Connection con=null;
	private static Statement s=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	
	private static  final String insertQry="insert into customer"
			+ "(name,email,mobile,password,state) values(?,?,?,?,?)";
	
	private static  final String validateQry = "select email, password from customer"
			+ " where email=? and password=?";
	
	public static boolean validateCustomer(String email, String password)
	{
		boolean b = false;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
			
			ps = con.prepareStatement(validateQry);
			ps.setString(1, email);
			ps.setString(2, password);
			
			
			rs = ps.executeQuery();
			
			b=rs.next();
			
		/*	String e =rs.getString("email");
			String p =rs.getString("password");
			
			if(e.equals(email) && p.equals(password))
			{
				b=true;
			}*/
				
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(rs!=null)
				try 
				{
					rs.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			if(ps!=null)
				try 
				{
					ps.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			if(con!=null)
				try 
				{
					con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		}
		return b;
	}
	
	public static void insertCustomer(Customer c)
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
			
			ps = con.prepareStatement(insertQry);
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobile());
			ps.setString(4, c.getPassword());
			ps.setString(5, c.getState());
			
			ps.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null)
				try 
				{
					ps.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			if(con!=null)
				try 
				{
					con.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
		}
	}
}
