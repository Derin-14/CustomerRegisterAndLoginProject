package com.derin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.derin.dao.DataAccess;
import com.derin.model.Customer;
import com.derin.util.SuccessPage;

@WebServlet("/")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = request.getServletPath();
		
		switch(path)
		{
		case "/logCustomer":
			validateCustomer(request, response);
			break;
		case "/regCustomer":
			insertCustomer(request,response);
			break;
		case "/reg":
			getRegistrationPage(request,response);
			break;
		case "/log":
			getLoginPage(request,response);
			break;
		default:
			getStartUpPage(request,response);
			break;
		}
	}

	private void validateCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		//Read the email & password from jsp page
		String em = request.getParameter("tbEmailLog");
		String p = request.getParameter("tbPassLog");
		
		//Call the method present in DAO
		boolean res = DataAccess.validateCustomer(em, p);
		
		//Condition and Redirect to destination page (Success)
		if(res)
		{
			try
			{
				RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
				// Store the SuccessPage class data inside RD
				SuccessPage sp = new SuccessPage();
				request.setAttribute("successDeatils", sp);
				rd.forward(request, response);
			} 
			catch (ServletException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else 
		{
			getLoginPage(request, response);
		}
	}

	private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
	{
		//Read the data from jsp page
		
		String name = request.getParameter("tbName");
		String email = request.getParameter("tbEmail");
		long mobile = Long.parseLong(request.getParameter("tbMobile"));
		String password = request.getParameter("tbPass");
		String state = request.getParameter("ddlState");
		
		
		//Store that data in customer object
		
		Customer cus = new Customer(name, email, mobile, password, state);
		
		//call insertCustomer method present in dao package
		
		DataAccess.insertCustomer(cus);
		
		//Redirect user to Registration to Login page
		try
		{
			RequestDispatcher rd = request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}

	private void getRegistrationPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try
		{
			RequestDispatcher rd= request.getRequestDispatcher("customer_registration.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void getLoginPage(HttpServletRequest request, HttpServletResponse response) 
	{
		try
		{
			RequestDispatcher rd= request.getRequestDispatcher("customer_login.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response) 
	{
		
		try
		{
			RequestDispatcher rd= request.getRequestDispatcher("customer_management.jsp");
			rd.forward(request, response);
		}
		catch (ServletException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

