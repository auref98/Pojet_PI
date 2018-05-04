package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Representant;
import DAO.DAORepresentant;

@WebServlet("/DemandeMDP")
public class ServletDemandeMDP extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");
		
		if(new DAORepresentant().find(mail) != null) request.setAttribute("inscriptionFailed", true);
		else
		{
			request.setAttribute("inscriptionSuccess", true);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request,  response);
	}

}
