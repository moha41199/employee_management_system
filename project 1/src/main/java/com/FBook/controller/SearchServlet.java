package com.FBook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.FBook.DAO.FacebookDAOInterface;
import com.FBook.entity.FacebookUser;
import com.FBook.utility.DAOFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
public class SearchServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("I'm in search profile servlet");
		//declarations
		RequestDispatcher rd;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//variable declarations
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		System.out.println(email);
		System.out.println(name);
		//setting into entity
		FacebookUser fu = new FacebookUser();
		fu.setEmail(email);
		fu.setName(name);
		
		//
		rd = getServletContext().getRequestDispatcher("/searchProfile.html");
		rd.include(request, response);

		//creating object
		FacebookDAOInterface fd = DAOFactory.createObjectHibernate();
		//dispalying result - email
		if(name == null) {
			FacebookUser ff = fd.searchProfileByEmail(fu);
			if(ff != null) { 
				  /*out.println("<html><body><center>"); out.println("<br>");
				  out.println("Name: "+ff.getName()); out.println("<br>");
				  out.println("Password: "+ff.getPassword()); out.println("<br>");
				  out.println("email: "+ff.getEmail()); out.println("<br>");
				  out.println("Address: "+ff.getAddress()); out.println("</center></body></html>"); */
					out.println("<html><head>");
		out.println("</head><body>");
		out.println("<div style='display:flex; flex-direction: column; justify-content:space-between; align-items:center'>");
		out.println("<div style='background: #56B3EB;width: 50%;height: 40%;display: flex; flex-direction: row; justify-content: center; align-items: center; border-radius: 10px'>");
			out.println("<div style = 'width:50%; height:100%; justify-content:center; padding:10px'>");
				out.println("Name");
				out.println("<br>");
				out.println("email");
				out.println("<br>");
				out.println("Address");
			out.println("</div>");
			out.println("<div style = 'width:50%; height:100%; justify-content:center'>");
				out.println(ff.getName());
				out.println("<br>");
				out.println(ff.getEmail());
				out.println("<br>");
				out.println(ff.getAddress());
			out.println("</div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body></html>");
			  }
			  else {
				  System.out.println("not found");			  
			  }			  
		}
		//dispalying result - name
		else  {
			ArrayList<ArrayList<String>> allProfile = fd.searchProfileByName(fu);
			//
			out.println("<html><head>");
			out.println("</head><body>");
			for(int i=0;i<allProfile.size();i++) {
				int j=0;
					  /*out.println("<table border=5");
					  out.println("<tr><td>Name</td><td>"+allProfile.get(i).get(j)+"</td></tr>");
					  out.println("<tr><td>Email</td><td>"+allProfile.get(i).get(j+1)+"</td></tr>");
					  out.println("<tr><td>Address</td><td>"+allProfile.get(i).get(j+2)+"</td></tr>");
					  out.println("</table>");*/
					  
				out.println("<div style='display:flex; flex-direction: column; justify-content:space-between; align-items:center'>");
					out.println("<div style='background: #56B3EB;width: 50%;height: 40%;display: flex; flex-direction: row; justify-content: center; align-items: center; border-radius: 10px'>");
						out.println("<div style = 'width:50%; height:100%; justify-content:center; padding:10px'>");
							out.println("Name");
							out.println("<br>");
							out.println("email");
							out.println("<br>");
							out.println("Address");
						out.println("</div>");
						out.println("<div style = 'width:50%; height:100%; justify-content:center'>");
							out.println(allProfile.get(i).get(j));
							out.println("<br>");
							out.println(allProfile.get(i).get(j+1));
							out.println("<br>");
							out.println(allProfile.get(i).get(j+2));
							out.println("<br>");
						out.println("</div>");
					out.println("</div>");
				out.println("</div>");
				out.println("<br>");
			}
		out.println("</body></html>");
			
		}
	}

}
