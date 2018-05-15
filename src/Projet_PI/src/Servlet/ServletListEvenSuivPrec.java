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

import Bean.Etudiant;
import Bean.Evenement;
import Bean.Plage;
import Bean.Professeur;
import DAO.DAOEvenement;
import DAO.DAOPlage;

@WebServlet("/ListEvenSuivPrec")
public class ServletListEvenSuivPrec extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		if(etu == null & prof == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
		
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			
			int cpt = 5;
			int compteur = 0;
			ArrayList<Evenement> evens = new DAOEvenement().find(0,cpt);
			for(Evenement even : evens){
				ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
				even.setListPlage(p);
				compteur++;
			}
			request.setAttribute("evens", evens);
			request.setAttribute("debut", 0);
			request.setAttribute("suiv", compteur == cpt);
			request.setAttribute("cpt", 1);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListEvenement.jsp");
			reqDisp.forward(request, response);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		if(etu == null & prof == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
		
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			
			String suivant = request.getParameter("Suivant");
			String precedent = request.getParameter("Precedent");
			int nb = 0;
			int cpt = 5;
			if(suivant != null){
				String [] suiv = suivant.split("age ");
				nb = Integer.parseInt(suiv[1]);
				nb--;
				nb*=cpt;
			}else if(precedent != null){
				String [] prec = precedent.split("age ");
				nb = Integer.parseInt(prec[1]);
				nb*=cpt;
				if(nb >= cpt)nb-=cpt;
				else nb = 0;
			}
			int compteur = 0;
			ArrayList<Evenement> evens = new DAOEvenement().find(nb,cpt);
			for(Evenement even : evens){
				ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
				even.setListPlage(p);
				compteur++;
			}
			request.setAttribute("evens", evens);
			request.setAttribute("debut", (int)nb/cpt);
			request.setAttribute("suiv", compteur == cpt);
			request.setAttribute("cpt", 1);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListEvenement.jsp");
			reqDisp.forward(request, response);
		}
	}
}
