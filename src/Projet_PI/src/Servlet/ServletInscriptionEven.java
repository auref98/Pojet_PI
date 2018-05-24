package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.LinkedList;

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
import DAO.DAOPlage;
import DAO.DAOProfesseur;
import DAO.DAORepresentant;

@WebServlet("/InscriptionEven")
public class ServletInscriptionEven extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
			
			//Etudiant etu = (Etudiant)session.getAttribute("etudiant");
			//Professeur prof = (Professeur)session.getAttribute("professeur");
			if(idPlage != 0 && (etu != null | prof != null)){
				Representant rep = etu;
				if(rep == null)rep = prof;
				
				Plage p = new DAOPlage().find(idPlage);
				Inscription ins = new Inscription(0,false,rep,p);
				boolean inscri = new DAOInscription().create(ins);
				request.setAttribute("inscri", inscri);
				
				for(Plage pl : even.getListePlage()){
					Inscription insc = new DAOInscription().find(rep.getId(), pl.getId());
					if(insc != null){
						pl.addInscription(insc);
					}
				}
				request.setAttribute("rep",rep);
			}
			
			LinkedList<Professeur> profs = new LinkedList<Professeur>();
			if(prof != null){
				for(Plage p : even.getListePlage()){
					LinkedList<Inscription> inscris = new DAOPlage().findListeInscription(p);
					if(inscris != null){
						for(Inscription ins : inscris){
							Professeur pr = new DAOProfesseur().find(ins.getRepresentant().getId());
							boolean add = true;
							for(Professeur prf : profs){
								if(add)add = !(prf.getId() == pr.getId());
							}
							if(add)profs.add(pr);
						}
					}
				}
			}
			
			request.setAttribute("peutSinscrire", true);
			request.setAttribute("profs", profs);
			request.setAttribute("even", even);
			request.setAttribute("postercom", posterCom);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/DetailEvenement.jsp");
			reqDisp.forward(request, response);
		}
	}
}
