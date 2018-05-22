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

@WebServlet("/ListeSection")
public class ServletListeSection extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		Representant rep = (Representant)session.getAttribute("representant");
		if(etu == null & prof == null & rep == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
		
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			request.setAttribute("charge", (boolean)session.getAttribute("charge"));
			
			ArrayList<Section> sect = new DAOSection().findAll();
			//ArrayList<Professeur> profs = new ArrayList<Professeur>();
			for(Section sec : sect){
				if(sec.getRelais().getMatricule() == null)sec.setRelais(new DAOProfesseur().find(sec.getRelais().getId()));
				/*boolean add = true;
				for(Professeur prf : profs){
					if(add && prf != null)add = !(prf.getId() == sec.getRelais().getId());
				}
				if(add) profs.add(sec.getRelais());*/
			}
			request.setAttribute("section",sect);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeSection.jsp");
			reqDisp.forward(request, response);
		}
	}
}
