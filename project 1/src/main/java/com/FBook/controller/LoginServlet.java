package com.FBook.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FBook.DAO.FacebookDAOInterface;
import com.FBook.entity.FacebookUser;
import com.FBook.utility.DAOFactory;

public class LoginServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I'm login servlet");
		//declarations
		RequestDispatcher rd;
		PrintWriter out = response.getWriter();
		HttpSession hs;
		ServletContext sc = getServletContext();
		
		//variable declarations
		String email = request.getParameter("em");
		String password = request.getParameter("pw");
		System.out.println(email);
		System.out.println(password);
		
		//setting into entity
		FacebookUser fu = new FacebookUser();
		fu.setEmail(email);
		fu.setPassword(password);
		
		//creating object
		FacebookDAOInterface fd = DAOFactory.createObjectHibernate();
		boolean b = fd.loginProfile(fu);
		
		if(b) {
			System.out.println("login success");
			hs = request.getSession(true);
			hs.setAttribute("password", password);	//httpSession
			sc.setAttribute("email", email);		//servletContext
			rd = getServletContext().getRequestDispatcher("/home.html");
			rd.forward(request, response);
		} else {
			System.out.println("login failed");
			out.println("<html><body>");
			out.println("<center><b><font size=5 color=red>Invalid username/password</font></b></center>");
			out.println("</body></html>");
			rd = getServletContext().getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
		
	}

}
