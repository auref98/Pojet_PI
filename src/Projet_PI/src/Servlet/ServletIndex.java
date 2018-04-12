/*
 * Haute école Robert Schuman - Libramont, annee scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integre: realisation d'un logiciel de gestion des inscriptions à des evenements
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

@WebServlet("/index.jsp")
public class ServletIndex extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		this.getServletContext().getRequestDispatcher("/WEB-INF/Connection.jsp").forward(request,  response);
	}
	
}
