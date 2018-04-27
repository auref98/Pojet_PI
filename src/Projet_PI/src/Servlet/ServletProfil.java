package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Profil.jsp")
public class ServletProfil extends HttpServlet{
	public ServletProfil(){}
	private static final long serialVersionUID = 1L;
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request,  response);
	}*/
}
