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

import DAO.*;
import Bean.*;

@WebServlet("/ListeRep")
public class ServletListeRep extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		if(etu == null & prof == null)
		{
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}
		else
		{
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			
			ArrayList<Professeur> tabProf = new DAOProfesseur().findAll();
			ArrayList<Etudiant> tabEtud = new DAOEtudiant().findAll();
			
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeRep.jsp");
			reqDisp.forward(request, response);
		}
	}
}
