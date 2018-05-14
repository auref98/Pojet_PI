package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Evenement;
import DAO.DAOEvenement;

@WebServlet("/RechercherEvent")
public class ServletRechercheEven extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		request.setAttribute("relais", (boolean)session.getAttribute("relais"));
		
		//recherche
		String recherche = request.getParameter("recherche");
		if(recherche != null && !recherche.equals("")){
			ArrayList<Evenement> events= new DAOEvenement().find(0, Integer.MAX_VALUE);
			LinkedList<Evenement> eventsOk = new LinkedList<Evenement>();
			for(Evenement event : events){
				if(event.getNom().toLowerCase().indexOf(recherche.toLowerCase())>=0){
					event.setListPlage(new DAOEvenement().findListePlage(event));
					eventsOk.add(event);
				}
			}
			request.setAttribute("events", eventsOk);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeEvenementRechercher.jsp");
			reqDisp.forward(request, response);
		}else{
			RequestDispatcher reqDisp = request.getRequestDispatcher("/ListEvenSuivPrec");
			reqDisp.forward(request, response);
		}
	}
}