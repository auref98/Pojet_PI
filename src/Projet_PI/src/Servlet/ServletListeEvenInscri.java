package Servlet;

import java.io.IOException;
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
import Bean.Inscription;
import Bean.Plage;
import Bean.Professeur;
import Bean.Representant;
import DAO.DAOEvenement;
import DAO.DAOInscription;
import DAO.DAOPlage;
import DAO.DAORepresentant;

@WebServlet("/ListEvenInscrit")
public class ServletListeEvenInscri  extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
			
			//Professeur prof = (Professeur)session.getAttribute("professeur");
			//Etudiant etu = (Etudiant)session.getAttribute("etudiant");
			Representant rep = etu;
			if(rep == null)rep = prof;
			
			LinkedList<Evenement> evens = new LinkedList<Evenement>();
			rep.setInscrits(new DAORepresentant().findInscription(rep.getId()));
			for(Inscription inscri : rep.getInscrits()){
				Plage p = new DAOPlage().find(inscri.getPlage().getId());
				Evenement eve = new DAOEvenement().find(p.getEve().getId());
				boolean add = true;
				for(Evenement e : evens){
					if(add)add = !(e.getId() == eve.getId());
				}
				if(add)evens.add(eve);
			}
			request.setAttribute("evens", evens);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeEvenInscrit.jsp");
			reqDisp.forward(request, response);
		}
	}
}
