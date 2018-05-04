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
	public ServletConnexion() {}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		boolean failed = false;
		if(mail.indexOf("student") != -1)
		{
			Etudiant etudiant = new DAOEtudiant().find(mail, password);
			if(etudiant == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
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
			session.setAttribute("professeur", prof);
		}
		
		if(!failed)this.getServletContext().getRequestDispatcher("/WEB-INF/ListeEvenement").forward(request,  response);
	}
	
}
