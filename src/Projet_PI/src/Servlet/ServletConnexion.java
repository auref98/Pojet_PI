/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2	
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

/**
 * Classe de type "servlet" pour la page de connexion redirigeant les requ�tes adress�es � la page principale de l'application, la liste des �v�nements organis�s.. <br><br> 
 * Redirige vers la page de connexion 
 * @see HttpServlet
 */

@WebServlet("/Connexion")	// Balise indiquant au servlet la cible de la requete HTTP d�clenchant son appel
public class ServletConnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/* Constructeur par d�faut, explicit� pour g�n�rer la javadoc */
	/**
	 * Constructeur par d�faut; ne fait rien.
	 */
	public ServletConnexion() {}
	
	/**
	 * Called by the server (via the service method) to allow a servlet to handle a GET request <i>(summary from <code>HttpServlet</code>)</i>. <br><br>
	 * M�thode redirigeant la requ�te HTTP vers la page ad hoc.  (En l'occurrence de la page "Connexion" vers elle-m�me si la connexion a �chou�, vers la page "ListeEvenement" si elle a r�ussi).
	 * @param request un objet <code>HttpServletRequest</code> contenant la requ�te que le client a faite au servlet
	 * @param response un objet <code>HttpServletResponse</code> contenant la r�ponse que le servlet envoie au client
	 * @throws IOException si une erreur d'input ou d'output est d�tect�e quand le servlet traite la requ�te POST
	 * @throws ServletException si la requ�te POST n'a pas pu �tre trait�e
	 * @see <code>HttpServlet#doPost</code>
	 * @see <code>javax.servlet.GenericServlet#getServletContext()</code>
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");														// R�cup�re le nom d'utilisateur
		String password = request.getParameter("password");												// R�cup�re le mot de passe
		HttpSession session = request.getSession(true);
		boolean failed = false;
		if(mail.indexOf("student") != -1)																// Si le nom d'utilisateur correspond � un �tudiant
		{
			Etudiant etudiant = new DAOEtudiant().find(mail, password);
			if(etudiant == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
			session.setAttribute("etudiant", etudiant);
		}
		else																							// Si le nom d'utilisateur correspond � un professeur
		{
			Professeur prof = new DAOProfesseur().find(mail, password);
			if(prof == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
			session.setAttribute("professeur", prof);
		}
		// si les informations de l'utilisateur ont �t� v�rifi�es, redirige sur la premi�re page du site
		if(!failed)this.getServletContext().getRequestDispatcher("/WEB-INF/NewFile.jsp").forward(request,  response);
	}
	
}
