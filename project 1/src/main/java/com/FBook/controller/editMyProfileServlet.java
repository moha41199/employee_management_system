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
public class editMyProfileServlet extends HttpServlet {
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
				rd = getServletContext().getRequestDispatcher("/editMyProfile.html");
				rd.include(request, response);
				
				
				
				//table view
				/*out.println("<html><body><center>");
				out.println("<form>");
				out.println("<table border=5");
				out.println("<tr><td>Name</td><td>"+ff.getName()+"</td><td><input type=text name=\"nm\" ></td></tr>");
				out.println("<br>");
				out.println("<tr><td>Password</td><td>"+ff.getPassword()+"</td><td><input type=text name=\"pw\"></td></tr>");
				out.println("<br>");
				out.println("<tr><td>email</td><td>"+ff.getEmail()+"</td><td><input type=text name=\"em\" value=\"not editable\" disabled></td></tr>");
				out.println("<br>");
				out.println("<tr><td>Address</td><td>"+ff.getAddress()+"</td><td><input type=text name=\"ad\"></td></tr>");
				out.println("<tr><td><input type=submit value=\"edit\"></td></tr>");
				out.println("</form>");
				out.println("</center></body></html>");*/
				//test
				out.println("<html><head>");
				out.println("</head><body>");
				out.println("<form>");
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
				out.println("<div style = 'width:50%; height:100%; justify-content:center'>");
				out.println("<input type=text name=\"nm\" >");
				out.println("<input type=text name=\"pw\">");
				out.println("<input type=text name=\"em\" value=\"not editable\" disabled>");
				out.println("<input type=text name=\"ad\">");
				out.println("</div>");
				out.println("<input type=submit value=\"edit\">");
				out.println("</form>");
				out.println("</div>");
				out.println("</div>");
				out.println("</body></html>");
				
				//edit profile
				String name = request.getParameter("nm");
				String password = request.getParameter("pw");
				String address = request.getParameter("ad");
				fu.setName(name);
				fu.setPassword(password);
				fu.setAddress(address);
				System.out.println(name);
				System.out.println(password);
				System.out.println(address);
				//function call
				int i = fd.editMyProfile(fu);
				if(i>0) {
					System.out.println("Profile edit success");
					//rd = getServletContext().getRequestDispatcher("/viewMyProfile.html");
					//rd.include(request, response);
				}
				else
					System.out.println("Profile edit failed");
	}

	private void editFun() {
		// TODO Auto-generated method stub
		
	}

}
