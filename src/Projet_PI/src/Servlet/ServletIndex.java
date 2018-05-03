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

/**
 * Classe de type "servlet" principale, redirigeant les requ�tes adress�es � la page "index". <br><br> 
 * (Nom de page implicite dans l'url, destination par d�faut de l'application).  <br>
 * Redirige vers la page de connexion.
 * @see HttpServlet
 */

@WebServlet("/index.jsp")	// Balise indiquant au servlet la cible de la requete HTTP d�clenchant son appel
public class ServletIndex extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/* Constructeur par d�faut, explicit� pour g�n�rer la javadoc */
	/**
	 * Constructeur par d�faut; ne fait rien.
	 */
	public ServletIndex() {}
	
	/**
	 * Called by the server (via the service method) to allow a servlet to handle a GET request <i>(summary from <code>HttpServlet</code>)</i>. <br><br>
	 * M�thode redirigeant la requ�te HTTP vers la page ad hoc.  (En l'occurrence de la page "index" vers la page "connexion").
	 * @param request un objet <code>HttpServletRequest</code> contenant la requ�te que le client a faite au servlet
	 * @param response un objet <code>HttpServletResponse</code> contenant la r�ponse que le servlet envoie au client
	 * @throws IOException si une erreur d'input ou d'output est d�tect�e quand le servlet traite la requ�te GET
	 * @throws ServletException si la requ�te GET n'a pas pu �tre trait�e
	 * @see <code>HttpServlet#doGet</code>
	 * @see <code>javax.servlet.GenericServlet#getServletContext()</code>
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
	}
	
}
