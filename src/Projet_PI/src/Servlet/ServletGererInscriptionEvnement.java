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

@WebServlet("/GererInscription")
public class ServletGererInscriptionEvnement extends HttpServlet{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
			
			int id = -1;
			try{
				id = Integer.parseInt(request.getParameter("inscription"));
			}catch(NullPointerException e){
				System.out.println("Impossible de parse..");
			}
			if(id > 1){
				Evenement eve = new Evenement();
				eve.setId(id);
				ArrayList<Plage> pl = new DAOEvenement().findListePlage(eve);
				for(Plage p : pl){
					p.setListeInscription(new DAOPlage().findListeInscription(p));
					if(p.getListeInscription() != null)
						for(Inscription ins : p.getListeInscription())
							ins.setRepresentant(new DAORepresentant().find(ins.getRepresentant().getId()));
						
				}
				
				request.setAttribute("even", eve);
				request.setAttribute("plage", pl);
				RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/GererInscriptionEvenement.jsp");
				reqDisp.forward(request, response);
			}
		}
	}
}
