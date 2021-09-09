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

public class ViewMyProfileServlet extends HttpServlet {
	
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
		FacebookUser ff=fd.viewMyProfile(fu);
		
		//
		rd = getServletContext().getRequestDispatcher("/viewMyProfile.html");
		rd.include(request, response);
		//
		out.println("<html><head>");
		out.println("</head><body>");
		out.println("<div style='display:flex; flex-direction: column; justify-content:space-between; align-items:center'>");
		out.println("<div style='background: #56B3EB;width: 50%;height: 40%;display: flex; flex-direction: row; justify-content: center; align-items: center; border-radius: 10px'>");
			out.println("<div style = 'width:50%; height:100%; justify-content:center; padding:10px'>");
				out.println("Name");
				out.println("<br>");
				out.println("Password");
				out.println("<br>");
				out.println("email");
				out.println("<br>");
				out.println("Address");
			out.println("</div>");
			out.println("<div style = 'width:50%; height:100%; justify-content:center'>");
				out.println(ff.getName());
				out.println("<br>");
				out.println(ff.getPassword());
				out.println("<br>");
				out.println(ff.getEmail());
				out.println("<br>");
				out.println(ff.getAddress());
			out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body></html>");

		// table to display my profile data
		/*out.println("<html><body><center>");
		out.println("<br>");
		out.println("Name: "+ff.getName());
		out.println("<br>");
		out.println("Password: "+ff.getPassword());
		out.println("<br>");
		out.println("email: "+ff.getEmail());
		out.println("<br>");
		out.println("Address: "+ff.getAddress());
		out.println("</center></body></html>");*/

	}

}

