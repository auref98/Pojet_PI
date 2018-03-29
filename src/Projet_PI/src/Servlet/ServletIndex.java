/*
 * NamingException(Aur�lien, Killian, Robin, Louis, Christophe)
 */

package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index.jsp")
public class ServletIndex extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request,  response);
	}
	
}
