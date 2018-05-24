package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Enumeration;
import java.util.LinkedList;
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
import Bean.Section;
import DAO.DAOAdresse;
import DAO.DAOEvenement;
import DAO.DAOInscription;
import DAO.DAOPlage;
import DAO.DAOProfesseur;
import DAO.DAORepresentant;

@WebServlet("/DetailEvenement")
public class ServletDetailEven extends HttpServlet{

	/**
	 * 
	 */
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
			int id = 0;
			if(names.hasMoreElements()){
				id = Integer.parseInt((String)names.nextElement());
			}
			DAOEvenement DAOeven = new DAOEvenement();
			Evenement even = DAOeven.find(id);
			even.setListPlage(DAOeven.findListePlage(even));
			boolean posterCom = false;
			for(Plage p : even.getListePlage()){
				if(!posterCom)posterCom=(p.getDate().toString().compareTo(LocalDate.now().toString()) < 0 )?true:false;
			}
			even.setSection(DAOeven.findListeSection(even));
			
			/**
			 * check si l'etudiant ou le prof peut s'inscrire a l'evenement
			 */
			boolean peutSinscrire = false;
			if( even.getListeSection() != null){
				if(!(boolean)session.getAttribute("relais") && etu != null){
					for(Section sec : even.getListeSection())
						if(!peutSinscrire)
							peutSinscrire = (sec.getId() == etu.getSec().getId());
				}
				else if(!(boolean)session.getAttribute("relais") && prof != null){
					prof.setEnseigne(new DAOProfesseur().findEnseigne(prof.getId()));
					for(Section sect : even.getListeSection())
						if(!peutSinscrire)
							for(Section sec : prof.getEnseigne())
								if(!peutSinscrire)
									peutSinscrire = sec.getId() == sect.getId();
				}
			}else{
				peutSinscrire = true;
			}
			
			even.setAdresseEve(new DAOAdresse().find(even.getAdresseEve().getId()));
			even.setCommentaire(DAOeven.findListeCom(even));
			if(even.getListeCommentaire()!=null){
				for(Commentaire com : even.getListeCommentaire()){
					com.setRep(new DAORepresentant().find(com.getRep().getId()));
				}
			}
			
			boolean relais = (boolean)session.getAttribute("relais");
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));

			LinkedList<Representant> listeRep = null;
			boolean inscri = false;
			if(!relais){
				Representant rep = etu;
				if(rep == null)rep = prof;
				for(Plage p : even.getListePlage()){
					Inscription ins = new DAOInscription().find(rep.getId(), p.getId());
					if(ins != null){
						p.addInscription(ins);
						inscri = true;
					}
				}
				request.setAttribute("rep",rep);
			}else{
				boolean contientDate = false;
				LinkedList<Plage> pla = new LinkedList<Plage>();
				for(Plage p : even.getListePlage()){
					contientDate = p.getDate().toString().equals(LocalDate.now().toString());//"2018-05-17"
					if(contientDate){
						pla.add(p);
					}
				}
				if(!pla.isEmpty()){
					for(Plage pl : pla){
						LinkedList<Inscription> ListeInscris = new DAOPlage().findListeInscription(pl);
						listeRep = new LinkedList<Representant>();
						if(ListeInscris != null && !ListeInscris.isEmpty()){
							for(Inscription ins : ListeInscris){
								if(ins.isValide() == true){
									Representant repr = new DAORepresentant().find(ins.getRepresentant().getId());
									repr.addInscrits(ins);
									listeRep.add(repr);
								}
							}
						}
					}
				}
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
			
			request.setAttribute("peutSinscrire", peutSinscrire);
			if(relais && listeRep != null && !listeRep.isEmpty())request.setAttribute("ListeInscris", listeRep);
			if(!profs.isEmpty())request.setAttribute("profs", profs);
			request.setAttribute("nbPlage", even.getListePlage().size());
			request.setAttribute("even", even);
			if(!relais)request.setAttribute("inscri", inscri);
			if(even.getListeCommentaire() != null)request.setAttribute("com", !even.getListeCommentaire().isEmpty());
			else if(posterCom)request.setAttribute("com", posterCom);
			request.setAttribute("postercom", posterCom);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/DetailEvenement.jsp");
			reqDisp.forward(request, response);
		}
	}
}
