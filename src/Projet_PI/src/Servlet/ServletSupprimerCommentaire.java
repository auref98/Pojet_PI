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
import DAO.DAOCommentaire;
import DAO.DAOEvenement;
import DAO.DAOInscription;
import DAO.DAOPlage;
import DAO.DAOProfesseur;
import DAO.DAORepresentant;

@WebServlet("/supprimerCommentaire")
public class ServletSupprimerCommentaire extends HttpServlet{

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
			
			boolean relais = (boolean) session.getAttribute("relais");
			
			Enumeration enume = request.getParameterNames();
			int id = -1;
			int idCom = -1;
			if(enume.hasMoreElements()){
				String nom = (String) enume.nextElement();
				String[] names = nom.split("-");
				try{
					idCom = Integer.parseInt(names[0]);
					id = Integer.parseInt(names[1]);
				}catch(Exception e){
					System.out.println("Erreur : parse int (ServletPosterCommentaire)");
				}
			}
			if(id > 0 & idCom > 0){
				DAOEvenement DAOeven = new DAOEvenement();
				Evenement even = DAOeven.find(id);
				even.setListPlage(DAOeven.findListePlage(even));
				boolean posterCom = false;
				for(Plage p : even.getListePlage()){
					posterCom=(p.getDate().toString().compareTo(LocalDate.now().toString()) < 0 )?true:false;
				}
				even.setSection(DAOeven.findListeSection(even));
				even.setAdresseEve(new DAOAdresse().find(even.getAdresseEve().getId()));
				
				Representant rep = etu;
				if(rep == null)rep = prof;
				Commentaire coment = new Commentaire();
				coment.setId(idCom);
				new DAOCommentaire().delete(coment);
				
				even.setCommentaire(DAOeven.findListeCom(even));
				if(even.getListeCommentaire()!=null){
					for(Commentaire com : even.getListeCommentaire()){
						com.setRep(new DAORepresentant().find(com.getRep().getId()));
					}
				}

				LinkedList<Representant> listeRep = null;
				boolean inscri = false;
				if(!relais){
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
						contientDate = p.getDate().toString().equals("2018-05-17");//LocalDate.now().toString()
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
}