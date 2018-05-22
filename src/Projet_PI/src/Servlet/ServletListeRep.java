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
			
			if(tabProf != null){
				for(Professeur p : tabProf){
					if(p.getMatricule() == null || p.getMatricule() == "")tabProf.remove(p);
				}
			}
			if(tabEtud != null){
				for(int i = 0; i < tabEtud.size(); i++){
					if(tabEtud.get(i).getMatricule() == null || tabEtud.get(i).getMatricule() == "")tabEtud.remove(i);
				}
			}
			
			request.setAttribute("prof", tabProf);
			request.setAttribute("etu", tabEtud);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeRep.jsp");
			reqDisp.forward(request, response);
		}
	}
}
