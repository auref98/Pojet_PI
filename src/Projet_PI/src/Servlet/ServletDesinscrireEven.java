package Servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.Commentaire;
import Bean.Etudiant;
import Bean.Evenement;
import Bean.Inscription;
import Bean.Professeur;
import Bean.Representant;
import DAO.DAOAdresse;
import DAO.DAOEvenement;
import DAO.DAOInscription;
import DAO.DAORepresentant;

@WebServlet("/DesinscriptionEven")
public class ServletDesinscrireEven extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		Enumeration names = request.getParameterNames();
		int idPlage = 0;
		int idEven = 0;
		if(names.hasMoreElements()){
			String ids = (String)names.nextElement();
			String [] tabIds = ids.split("-");
			idEven = Integer.parseInt(tabIds[1]);
			idPlage = Integer.parseInt(tabIds[0]);
		}
		DAOEvenement DAOeven = new DAOEvenement();
		Evenement even = DAOeven.find(idEven);
		even.setListPlage(DAOeven.findListePlage(even));
		even.setSection(DAOeven.findListeSection(even));
		even.setAdresseEve(new DAOAdresse().find(even.getAdresseEve().getId()));
		even.setCommentaire(DAOeven.findListeCom(even));
		if(even.getListeCommentaire()!=null){
			for(Commentaire com : even.getListeCommentaire()){
				com.setRep(new DAORepresentant().find(com.getRep().getId()));
			}
		}
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		if(idPlage != 0 && (etu != null | prof != null)){
			Representant rep = etu;
			if(rep == null)rep = prof;
			
			Inscription ins = new DAOInscription().find(rep.getId(),idPlage);
			new DAOInscription().delete(ins);
		}
		request.setAttribute("even", even);
		request.setAttribute("inscri", true);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/DetailEvenement.jsp");
		reqDisp.forward(request, response);
	}
}
