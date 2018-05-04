package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Evenement;
import Bean.Plage;
import DAO.DAOEvenement;
import DAO.DAOPlage;

@WebServlet("/ListeEvenement")
public class ServletListEvenement extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		ArrayList<Evenement> evens = new DAOEvenement().find(0,4);
		for(Evenement even : evens){
			ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
			even.setListePlage(p);
		}
		request.setAttribute("evens", evens);
		request.setAttribute("debut", 0);
		request.setAttribute("cpt", 4);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListEvenement.jsp");
		reqDisp.forward(request, response);
	}

}
