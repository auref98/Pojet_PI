package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Etudiant;
import Bean.Professeur;
import Bean.Representant;
import Bean.Section;
import DAO.DAOProfesseur;
import DAO.DAOSection;

@WebServlet("/SupprimerSection")
public class ServletSupprimerSection extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		Representant rep = (Representant)session.getAttribute("representant");
		if(etu == null & prof == null & rep == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
		
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			request.setAttribute("charge", (boolean)session.getAttribute("charge"));
			
			Enumeration enumes = request.getParameterNames();
			while(enumes.hasMoreElements()){
				String name = (String)enumes.nextElement();
				String [] namesplit = name.split("-");
				if(namesplit[0].equals("supp")){
					int id = -1;
					try{
						id = Integer.parseInt(namesplit[1]);
					}catch(NullPointerException e){
						System.out.println("Erreur : Parse impossible (Supprimer section)");
					}
					if(id>0)
						new DAOSection().delete(new Section(id,"",null));
				}
			}
			
			ArrayList<Section> sect = new DAOSection().findAll();
			for(Section sec : sect){
				if(sec.getRelais().getMatricule() == null)sec.setRelais(new DAOProfesseur().find(sec.getRelais().getId()));
			}
			ArrayList<Professeur> profs = new DAOProfesseur().findAll();
			request.setAttribute("section",sect);
			request.setAttribute("profs", profs);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeSection.jsp");
			reqDisp.forward(request, response);
		}
	}
}
