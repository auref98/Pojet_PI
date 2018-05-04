/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2	
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

/**
 * Classe de type "servlet" pour la page de connexion redirigeant les requêtes adressées à la page principale de l'application, la liste des événements organisés.. <br><br> 
 * Redirige vers la page de connexion 
 * @see HttpServlet
 */

@WebServlet("/Connexion")	// Balise indiquant au servlet la cible de la requete HTTP déclenchant son appel
public class ServletConnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/* Constructeur par défaut, explicité pour générer la javadoc */
	/**
	 * Constructeur par défaut; ne fait rien.
	 */
	public ServletConnexion() {}
	
	/**
	 * Called by the server (via the service method) to allow a servlet to handle a GET request <i>(summary from <code>HttpServlet</code>)</i>. <br><br>
	 * Méthode redirigeant la requête HTTP vers la page ad hoc.  (En l'occurrence de la page "Connexion" vers elle-même si la connexion a échoué, vers la page "ListeEvenement" si elle a réussi).
	 * @param request un objet <code>HttpServletRequest</code> contenant la requête que le client a faite au servlet
	 * @param response un objet <code>HttpServletResponse</code> contenant la réponse que le servlet envoie au client
	 * @throws IOException si une erreur d'input ou d'output est détectée quand le servlet traite la requête POST
	 * @throws ServletException si la requête POST n'a pas pu être traitée
	 * @see <code>HttpServlet#doPost</code>
	 * @see <code>javax.servlet.GenericServlet#getServletContext()</code>
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");														// Récupère le nom d'utilisateur
		String password = request.getParameter("password");												// Récupère le mot de passe
		HttpSession session = request.getSession(true);
		boolean failed = false;
		if(mail.indexOf("student") != -1)																// Si le nom d'utilisateur correspond à un étudiant
		{
			Etudiant etudiant = new DAOEtudiant().find(mail, password);
			if(etudiant == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
			session.setAttribute("etudiant", etudiant);
		}
		else																							// Si le nom d'utilisateur correspond à un professeur
		{
			Professeur prof = new DAOProfesseur().find(mail, password);
			if(prof == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
			session.setAttribute("professeur", prof);
		}
		// si les informations de l'utilisateur ont été vérifiées, redirige sur la première page du site
		if(!failed)this.getServletContext().getRequestDispatcher("/WEB-INF/NewFile.jsp").forward(request,  response);
	}
	
}
