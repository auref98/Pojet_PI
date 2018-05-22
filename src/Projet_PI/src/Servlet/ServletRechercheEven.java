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

import Bean.Etudiant;
import Bean.Evenement;
import Bean.Professeur;
import Bean.Representant;
import DAO.DAOEvenement;

@WebServlet("/RechercherEvent")
public class ServletRechercheEven extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		Representant repre = (Representant)session.getAttribute("representant");
		if(etu == null & prof == null & repre == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
		
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			request.setAttribute("charge", (boolean)session.getAttribute("charge"));
			
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
}
