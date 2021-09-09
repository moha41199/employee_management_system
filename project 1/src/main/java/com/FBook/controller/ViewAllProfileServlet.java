package com.FBook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FBook.DAO.FacebookDAOInterface;
import com.FBook.entity.FacebookUser;
import com.FBook.utility.DAOFactory;
public class ViewAllProfileServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("im in view all profile servlet");
		//declartion
		ServletContext sc = getServletContext();
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		response.setContentType("text/html");
		//variable declaration		
		String email = (String) sc.getAttribute("email");
		
		//setting into entity
		FacebookUser fu = new FacebookUser();
		fu.setEmail(email);
		
		//creating object
		FacebookDAOInterface fd = DAOFactory.createObjectHibernate();
		ArrayList<ArrayList<String>> allProfile = fd.viewAllProfile(fu);
			//System.out.println(allProfile);
			//System.out.println("allProfile size: "+allProfile.size());
			//System.out.println(allProfile.get(0).get(1));
		//
		rd = getServletContext().getRequestDispatcher("/viewAllProfile.html");
		rd.include(request, response);
		//
		out.println("<html><body><center>");
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
		out.println("</center></body></html>");
	}
}
