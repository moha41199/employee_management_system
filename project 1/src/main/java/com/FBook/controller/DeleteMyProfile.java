package com.FBook.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FBook.DAO.FacebookDAOInterface;
import com.FBook.entity.FacebookUser;
import com.FBook.utility.DAOFactory;
public class DeleteMyProfile extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//declarations
		ServletContext sc = getServletContext();
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		response.setContentType("text/html");
		//getting ServletContext data
		String email = (String)sc.getAttribute("email");
		System.out.println(email);
		//Setting into entity
		FacebookUser fu = new FacebookUser();
		fu.setEmail(email);
			
		//Create object
		FacebookDAOInterface fd = DAOFactory.createObjectHibernate();
		int i = fd.deleteMyProfile(fu);
		if(i>0) {
			System.out.println("delete success");
			rd = getServletContext().getRequestDispatcher("/login.html");
			rd.forward(request, response);
		}
		else
			System.out.println("delete failed");
		
	}

}
