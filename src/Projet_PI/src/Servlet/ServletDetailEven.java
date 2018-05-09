package Servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.Commentaire;
import Bean.Evenement;
import DAO.DAOAdresse;
import DAO.DAOEvenement;
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
		even.setSection(DAOeven.findListeSection(even));
		even.setAdresseEve(new DAOAdresse().find(even.getAdresseEve().getId()));
		even.setCommentaire(DAOeven.findListeCom(even));
		if(even.getListeCommentaire()!=null){
			for(Commentaire com : even.getListeCommentaire()){
				if(com.getRep() != null)System.out.println(com.getRep().getId());
				else System.out.println("erreur");
				com.setRep(new DAORepresentant().find(com.getRep().getId()));
			}
		}
		request.setAttribute("even", even);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/DetailEvenement.jsp");
		reqDisp.forward(request, response);
	}
}
