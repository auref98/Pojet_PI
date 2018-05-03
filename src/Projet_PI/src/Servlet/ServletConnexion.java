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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		
		if(mail.indexOf("student") != -1)
		{
			System.out.println("Etudiant");
			Etudiant etudiant = new DAOEtudiant().find(mail, password);
			session.setAttribute("etudiant", etudiant);
		}
		else
		{
			System.out.println("Prof");
			Professeur prof = new DAOProfesseur().find(mail, password);
			session.setAttribute("professeur", prof);
		}
		
		response.sendRedirect("/Profil");
	}
	
}
