package com.FBook.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.FBook.DAO.FacebookDAOInterface;
import com.FBook.entity.FacebookUser;
import com.FBook.utility.DAOFactory;

public class RegisterServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(RegisterServlet.class);
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//declarations
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		log.info("REGISTER SERVLET LOG");
		//variable declarations
		String name = request.getParameter("nm");
		String password = request.getParameter("pw");
		String email = request.getParameter("em");
		String address = request.getParameter("ad");
		//save user data
		FacebookUser fu = new FacebookUser();
		fu.setName(name);
		fu.setPassword(password);
		fu.setEmail(email);
		fu.setAddress(address);
		System.out.println(fu.getName());
		System.out.println(fu.getPassword());
		System.out.println(fu.getEmail());
		System.out.println(fu.getAddress());
		
		//creating object
		FacebookDAOInterface fd1 = DAOFactory.createObjectHibernate();
		System.out.println(fd1);
		
		//create profile
		int i = fd1.createProfile(fu);
		
		//create profile - response
		if(i>0) {
			System.out.println("profile created");
			rd = getServletContext().getRequestDispatcher("/login.html");
			rd.forward(request, response);
		} else {
			System.out.println("profile creation failed");
			  out.println("<html><body>");
			  out.println("Registration Failed :( Please try again..");
			  out.println("</body></html>");
			rd = getServletContext().getRequestDispatcher("/register.html");
			rd.include(request, response);
		}
	}

}
