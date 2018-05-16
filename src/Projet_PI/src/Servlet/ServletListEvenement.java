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

@WebServlet("/ListeEvenement")
public class ServletListEvenement extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
			int cpt = 5;
			ArrayList<Evenement> evens = new DAOEvenement().find(0,cpt);
			int compteur = 0;
			for(Evenement even : evens){
				ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
				ArrayList<Plage> plage = new ArrayList<Plage>();
				boolean add = true;
				for(Plage pl : plage){
					for(Plage pls : p){
						if(add)add = (!pls.getDate().equals(pl.getDate()) )?true:false;
					}
				}
				if(add)even.setListPlage(plage);
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

}
