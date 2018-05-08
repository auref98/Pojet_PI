/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.*;
import DAO.*;

@WebServlet("/Connexion")	// Balise indiquant au servlet la cible de la requete HTTP d�clenchant son appel
public class ServletConnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/* Constructeur par d�faut, explicit� pour g�n�rer la javadoc */
	/**
	 * Constructeur par d�faut; ne fait rien.
	 */
	
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
			else if(etudiant.getFirstName() == null) firstConnection = true;
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
			else if(prof.getFirstName() == null) firstConnection = true;
			session.setAttribute("professeur", prof);
		}
		
		if(!failed && firstConnection == false)
		{
			int cpt = 2;
			ArrayList<Evenement> evens = new DAOEvenement().find(0,cpt);
			int compteur = 0;
			for(Evenement even : evens){
				ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
				if(p != null)even.setListPlage(p);
				compteur++;
			}
			request.setAttribute("evens", evens);
			request.setAttribute("debut", 0);
			request.setAttribute("suiv", compteur == cpt);
			request.setAttribute("cpt", 1);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListEvenement.jsp").forward(request, response);
		}
		else if(!failed && firstConnection == true)
		{
			Etudiant etu = (Etudiant)session.getAttribute("etudiant");
			Professeur prof = (Professeur)session.getAttribute("professeur");
			request.setAttribute("firstConnection", firstConnection);
			boolean isEtu = (etu != null)?true:false;
			if(isEtu){
				etu.setAdr(new DAOEtudiant().findAddr(etu.getId()));
				etu.setSec(new DAOEtudiant().findSect(etu.getId()));
				request.setAttribute("sects", new DAOSection().findAll());
				request.setAttribute("rep", etu);
				request.setAttribute("adr", etu.getAdr());
			}else{
				request.setAttribute("rep", prof);
			}
			request.setAttribute("isEtu", isEtu);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request,  response);
		}
	}
}
