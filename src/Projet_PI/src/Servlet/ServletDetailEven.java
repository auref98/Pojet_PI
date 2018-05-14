package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.Vector;

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
import Bean.Plage;
import Bean.Professeur;
import Bean.Representant;
import DAO.DAOAdresse;
import DAO.DAOEvenement;
import DAO.DAOInscription;
import DAO.DAORepresentant;

@WebServlet("/DetailEvenement")
public class ServletDetailEven extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Enumeration names = request.getParameterNames();
		int id = 0;
		if(names.hasMoreElements()){
			id = Integer.parseInt((String)names.nextElement());
		}
		DAOEvenement DAOeven = new DAOEvenement();
		Evenement even = DAOeven.find(id);
		even.setListPlage(DAOeven.findListePlage(even));
		boolean posterCom = false;
		for(Plage p : even.getListePlage()){
			posterCom=(p.getDate().toString().compareTo(LocalDate.now().toString()) < 0 )?true:false;
		}
		even.setSection(DAOeven.findListeSection(even));
		even.setAdresseEve(new DAOAdresse().find(even.getAdresseEve().getId()));
		even.setCommentaire(DAOeven.findListeCom(even));
		if(even.getListeCommentaire()!=null){
			for(Commentaire com : even.getListeCommentaire()){
				com.setRep(new DAORepresentant().find(com.getRep().getId()));
			}
		}
		request.setAttribute("even", even);
		
		HttpSession session = request.getSession(true);
		request.setAttribute("relais", (boolean)session.getAttribute("relais"));
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		boolean inscri = false;
		if(etu != null | prof != null){
			Representant rep = etu;
			if(rep == null)rep = prof;
			for(Plage p : even.getListePlage()){
				Inscription ins = new DAOInscription().find(rep.getId(), p.getId());
				if(ins != null){
					p.addInscription(ins);
					inscri = true;
				}
			}
		}
		request.setAttribute("inscri", inscri);
		request.setAttribute("postercom", posterCom);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/DetailEvenement.jsp");
		reqDisp.forward(request, response);
	}
}
