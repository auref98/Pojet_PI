/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integré: réalisation d'un logiciel de gestion des inscriptions à des événements
 * 
 * Groupe: NamingException {
 * 				Adam Ludovic;
 *				Arnould Killian;
 * 				De Bernardi Christophe;
 * 				Fockedey Aurelien;
 * 				Mathieu Robin;
 * 				Modave Louis;
 * 				}
 */

package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.*;
import DAO.*;

@WebServlet("/Connexion")	// Balise indiquant au servlet la cible de la requete HTTP déclenchant son appel
public class ServletConnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/* Constructeur par défaut, explicité pour générer la javadoc */
	/**
	 * Constructeur par défaut; ne fait rien.
	 */
	public ServletConnexion() {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		boolean failed = false;
		boolean firstConnection = false;
		
		if(mail.indexOf("student") != -1)
		{
			Etudiant etudiant = new DAOEtudiant().find(mail, password);
			if(etudiant == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
			if(etudiant.getFirstName() == null) firstConnection = true;
			session.setAttribute("etudiant", etudiant);
		}
		else
		{
			Professeur prof = new DAOProfesseur().find(mail, password);
			if(prof == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
			if(prof.getFirstName() == null) firstConnection = true;
			session.setAttribute("professeur", prof);
		}
		
		if(!failed && firstConnection == false)
		{
			ArrayList<Evenement> evens = new DAOEvenement().find(0,4);
			for(Evenement even : evens){
				ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
				even.setListPlage(p);
			}
			request.setAttribute("evens", evens);
			request.setAttribute("debut", 0);
			request.setAttribute("cpt", 4);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListEvenement.jsp").forward(request, response);
		}
		else if(!failed && firstConnection == true)
		{
			this.getServletContext().getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request,  response);
		}
	}
}
