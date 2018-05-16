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

/**
 * Classe de type "servlet" principale, redirigeant les requêtes adressées à la page "index". <br><br> 
 * (Nom de page implicite dans l'url, destination par défaut de l'application).  <br>
 * Redirige vers la page de connexion.
 * @see HttpServlet
 */

@WebServlet("/index.jsp")	// Balise indiquant au servlet la cible de la requete HTTP déclenchant son appel
public class ServletIndex extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/* Constructeur par défaut, explicité pour générer la javadoc */
	/**
	 * Constructeur par défaut; ne fait rien.
	 */
	public ServletIndex() {}
	
	/**
<<<<<<< HEAD
	 * <i>Called by the server (via the service method) to allow a servlet to handle a GET request </i>(summary from <code>HttpServlet</code>). <br><br>
	 * Méthode redirigeant la requête HTTP vers la page ad hoc.  (En l'occurrence de la page "index" vers la page "Connexion").
=======
	 * Called by the server (via the service method) to allow a servlet to handle a GET request <i>(summary from <code>HttpServlet</code>)</i>. <br><br>
	 * Méthode redirigeant la requête HTTP vers la page ad hoc.  (En l'occurrence de la page "index" vers la page "connection").
>>>>>>> 3f83460c30736d108d7093f65211d90231cd359a
	 * @param request un objet <code>HttpServletRequest</code> contenant la requête que le client a faite au servlet
	 * @param response un objet <code>HttpServletResponse</code> contenant la réponse que le servlet envoie au client
	 * @throws IOException si une erreur d'input ou d'output est détectée quand le servlet traite la requête GET
	 * @throws ServletException si la requête GET n'a pas pu être traitée
	 * @see <code>HttpServlet#doGet</code>
	 * @see <code>javax.servlet.GenericServlet#getServletContext()</code>
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		boolean firstConnection = false;
		try
		{
			firstConnection = (boolean)session.getAttribute("firstConnection");	
		}
		catch(NullPointerException ex)
		{
			System.out.println("erreur: Attributs de session innexistants");
		}
		if(etu == null && prof == null) this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
		else 
		{
			if(firstConnection) this.getServletContext().getRequestDispatcher("/Profil").forward(request,  response);
			else this.getServletContext().getRequestDispatcher("/ListeEvenement").forward(request,  response);
		}
	}
	
}
