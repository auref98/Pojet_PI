package Servlet;

import java.io.IOException;
import java.util.Enumeration;

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
import DAO.DAOEvenement;

@WebServlet("/SupprimerEvenement")
public class ServletSupprimerEven extends HttpServlet{

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
			
			Enumeration enume = request.getParameterNames();
			if(enume.hasMoreElements()){
				String nom = (String)enume.nextElement();
				String [] nomSplit = nom.split("-");
				if(nomSplit[0].equals("event")){
					int id = -1;
					try {
						id = Integer.parseInt(nomSplit[1]);
					} catch (Exception e) {
						System.out.println("Erreur : parse int (SupprimerEven)");
					}
					if(id>0){
						Evenement eve = new Evenement();
						eve.setId(id);
						new DAOEvenement().delete(eve);
					}
				}
			}
			RequestDispatcher reqDisp = request.getRequestDispatcher("/ListEvenSuivPrec");
			reqDisp.forward(request, response);
		}
	}
}
