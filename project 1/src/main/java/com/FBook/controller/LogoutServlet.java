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
public class LogoutServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//declarations
		ServletContext sc = getServletContext();
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		HttpSession hs;
		//
		String email = (String)sc.getAttribute("email");
		System.out.println(email);
		hs = request.getSession();
		
		System.out.println("before: "+hs);		
		hs.invalidate();
		System.out.println("after: "+hs);
		rd = getServletContext().getRequestDispatcher("/login.html");
		rd.forward(request, response);
		
	}

}
