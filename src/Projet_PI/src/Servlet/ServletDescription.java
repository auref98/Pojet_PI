/*
 * Haute école Robert Schuman - Libramont, année scolaire 2017 - 2018
<<<<<<< HEAD
 * Bachelier en informatique de gestion, bloc 2		
=======
 * Informatique de geston, bloc 2	
>>>>>>> 3f83460c30736d108d7093f65211d90231cd359a
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


/**
 * Classe de type "servlet" redirigeant les requêtes adressées à la page d'inscription. <br><br> 
 * @see HttpServlet
 */
@WebServlet("/Inscription")	// Balise indiquant au servlet la cible de la requete HTTP déclenchant son appel
public class ServletDescription extends HttpServlet
{
	private static final long serialVersionUID = 1L; 

	/* Constructeur par défaut, explicité pour générer la javadoc */
	/**
	 * Constructeur par défaut; ne fait rien.
	 */
	public ServletDescription() {}
	
	/**
	 * <i>Called by the server (via the service method) to allow a servlet to handle a GET request </i>(summary from <code>HttpServlet</code>). <br><br>
	 * Méthode redirigeant la requête HTTP vers la page ad hoc.  (En l'occurrence vers la page "Inscription").
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request,  response);
	}
	
}
