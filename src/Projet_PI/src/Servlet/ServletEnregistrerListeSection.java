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

import Bean.*;
import DAO.*;

@WebServlet("/EnregistrerSection")
public class ServletEnregistrerListeSection extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
			
			ArrayList<Section> sects = new ArrayList<Section>();
			sects = new DAOSection().findAll();
			Enumeration enumes = request.getParameterNames();
			while(enumes.hasMoreElements()){
				String name = (String)enumes.nextElement();
				String [] nameSplit = name.split("-");
				if(nameSplit[0].equals("sec")){
					String value = request.getParameter(name);
					int id = -1;
					try{
						id = Integer.parseInt(nameSplit[1]);
					}catch(NullPointerException e){
						System.out.println("Erreur : Parse impossible (EnregistrerListeSection)");
					}
					if(id>0)
						for(Section sec : sects)
							if(sec.getId() == id && !sec.getNom().equals(value)){
								sec.setNom(value);
								new DAOSection().update(sec);
							}
					
				}else if(nameSplit[0].equals("prof")){
					String value = request.getParameter(name);
					int idProf = -1;
					int idSect = -1;
					int refRelais = -1;
					try{
						idSect = Integer.parseInt(nameSplit[1]);
						idProf = Integer.parseInt(nameSplit[2]);
						refRelais = Integer.parseInt(value);
					}catch(NullPointerException | IndexOutOfBoundsException e){
						System.out.println("Erreur : Parse relais impossible (EnregistrerListeSection)");
					}
					if(idProf > 0 && idSect > 0)
						for(Section sec : sects)
							if(sec.getId() == idSect && sec.getRelais().getId() != refRelais){
								Professeur profe = new DAOProfesseur().find(refRelais);
								sec.setRelais(profe);
								new DAOSection().update(sec);
							}
					
				}
			}
			for(Section sec : sects)
				if(sec.getRelais().getMatricule() == null)
					sec.setRelais(new DAOProfesseur().find(sec.getRelais().getId()));
			
			ArrayList<Professeur> profs = new DAOProfesseur().findAll();
			request.setAttribute("section",sects);
			request.setAttribute("profs", profs);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeSection.jsp");
			reqDisp.forward(request, response);
		}
	}
}
