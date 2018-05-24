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

import Bean.*;
import DAO.*;

@WebServlet("/ModifierProfil")
public class ServletMofifierProfilUtilisateur  extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		Representant repre = (Representant)session.getAttribute("representant");
		if(etu == null & prof == null & repre == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
		
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			request.setAttribute("charge", (boolean)session.getAttribute("charge"));
			
			int id = -1;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			} catch (NullPointerException e) {
				System.out.println("Erreur : impossible de parse (ServletMofifierProfilUtilisateur)");
			}
			Etudiant e = new DAOEtudiant().find(id);
			Professeur p = null;
			if(e == null)p = new DAOProfesseur().find(id);
			
			ArrayList<Section> sects = new DAOSection().findAll();
			if(e != null){
				e.setAdr(new DAOEtudiant().findAddr(e.getId()));
				e.setSec(new DAOEtudiant().findSect(e.getId()));
				request.setAttribute("rep", e);
				request.setAttribute("mail", e.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+e.getMail().split("@")[1]);
				request.setAttribute("adr", e.getAdr());
			}else if(p != null){
				request.setAttribute("rep", p);
				request.setAttribute("mail", p.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+p.getMail().split("@")[1]);
				for(Section sect : sects){
					sect.setProf(new DAOSection().findListeProfesseur(sect));
				}
			}
			request.setAttribute("sects", sects);
			request.setAttribute("isEtu", e!=null);
			request.setAttribute("isProf", p!=null);
			request.setAttribute("autreutilisateur", true);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Profil.jsp");
			reqDisp.forward(request, response);
		}
	}
}
