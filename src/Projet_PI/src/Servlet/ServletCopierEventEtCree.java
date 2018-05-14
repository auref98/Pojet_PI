package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.*;
import DAO.*;

@WebServlet("/CopierCreeEvent")
public class ServletCopierEventEtCree extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		if(etu == null & prof == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
			
			int id = -1;
			Enumeration enume = request.getParameterNames();
			boolean cont = true;
			while(enume.hasMoreElements() && cont){
				String name = (String) enume.nextElement();
				String[] names = name.split("-");
				if(names[0].equals("event"))
					try{
						id = Integer.parseInt(names[1]);
						cont = false;
					}catch(Exception e){
						System.out.println("Erreur : Parse integer (ServletCopierEventEtCree)");
					}
			}
			
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			if(id > 0){
				Evenement event = new DAOEvenement().find(id);
				event.setAdresseEve(new DAOAdresse().find(event.getAdresseEve().getId()));
				event.setListPlage(new DAOEvenement().findListePlage(event));
				ArrayList<Section> sects = new DAOSection().findAll();
				request.setAttribute("event", event);
				request.setAttribute("sects", sects);
				RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/CreeCopierEvent.jsp");
				reqDisp.forward(request, response);
			}else{
				ArrayList<Evenement> events = new DAOEvenement().findAll();
				request.setAttribute("events", events);
				RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeAllEventCopier.jsp");
				reqDisp.forward(request, response);
			}
			
		}
	}
}
