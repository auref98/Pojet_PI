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

@WebServlet("/EnregistrerListeValide")
public class ServletEnregistrerListeValide extends HttpServlet{

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
			
			int id = -1;
			int idEve = -1;
			String idPlage = request.getParameter("ValiderIns");
			try{
				id = Integer.parseInt(request.getParameter("ValiderIns"));
				idEve = Integer.parseInt(request.getParameter("Evenement"));
			}catch(NullPointerException e){
				System.out.println("Erreur : impossible de parse (ServletEnregistrerListeValide)");
			}
			if(id>0){
				Plage p = new DAOPlage().find(id);
				p.setListeInscription(new DAOPlage().findListeInscription(p));
				ArrayList<Inscription> inscri = new ArrayList<Inscription>();
				Enumeration enumes = request.getParameterNames();
				while(enumes.hasMoreElements()){
					String nom = (String)enumes.nextElement();
					String[] nameSplit = nom.split("-");
					if(nameSplit[0].equals(idPlage)){
						int idRep = -1;
						int idIns = -1;
						try{
							idRep = Integer.parseInt(nameSplit[1]);
							idIns = Integer.parseInt(nameSplit[2]);
						}catch(NullPointerException e){
							System.out.println("Erreur : parse impossible (ServletEnregistrerListeValide)");
						}
						if(idIns > 0 && idRep > 0){
							Representant rep = new Representant();
							rep.setId(idRep);
							Inscription ins = new Inscription(idIns,true,rep,p);
							inscri.add(ins);
						}
					}
				}
				for(Inscription ins : p.getListeInscription()){
					int trouver = -1;
					int cpt = 0;
					for(Inscription insc : inscri){
						if(trouver == -1)
							trouver = (insc.getId() == ins.getId()) ? cpt : -1;
						cpt++;
					}
					if(trouver == -1)
						ins.setValide(false);
					else
						ins = inscri.get(trouver);
					new DAOInscription().update(ins);
				}
				
				if(idEve > 0){
					Evenement eve = new Evenement();
					eve.setId(idEve);
					ArrayList<Plage> pl = new DAOEvenement().findListePlage(eve);
					for(Plage pla : pl){
						pla.setListeInscription(new DAOPlage().findListeInscription(pla));
						if(pla.getListeInscription() != null)
							for(Inscription ins : pla.getListeInscription())
								ins.setRepresentant(new DAORepresentant().find(ins.getRepresentant().getId()));
							
					}
					
					request.setAttribute("even", eve);
					request.setAttribute("plage", pl);
					RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/GererInscriptionEvenement.jsp");
					reqDisp.forward(request, response);
				}
			}
		}
	}
}
