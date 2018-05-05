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

@WebServlet("/ListEvenSuivPrec")
public class ServletListEvenSuivPrec extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		ArrayList<Evenement> evens = new DAOEvenement().find(0,2);
		for(Evenement even : evens){
			ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
			even.setListPlage(p);
		}
		request.setAttribute("evens", evens);
		request.setAttribute("debut", 0);
		request.setAttribute("cpt", 2);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListEvenement.jsp");
		reqDisp.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String suivant = request.getParameter("Suivant");
		String precedent = request.getParameter("Precedent");
		int nb = 0;
		int cpt = 2;
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
