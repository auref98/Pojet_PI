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

import Bean.Evenement;
import DAO.DAOEvenement;

@WebServlet("/CopierEvenement")
public class ServletCopierEvenement extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		request.setAttribute("relais", (boolean)session.getAttribute("relais"));
		ArrayList<Evenement> events = new DAOEvenement().findAll();
		request.setAttribute("events", events);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeAllEventCopier.jsp");
		reqDisp.forward(request, response);
	}
}
