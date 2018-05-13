package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.*;
import DAO.*;

@WebServlet("/CreeEvenement")
public class ServletCreeEvenement extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		request.setAttribute("relais", (boolean)session.getAttribute("relais"));
		
		ArrayList<Section> sects = new DAOSection().findAll();
		request.setAttribute("sects", sects);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/CreeEvenement.jsp");
		reqDisp.forward(request, response);
	}

}
