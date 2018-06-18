/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Informatique de geston, bloc 2	
 * 
 * Projet integr�: r�alisation d'un logiciel de gestion des inscriptions � des �v�nements
 * 
 * Groupe: NamingException {
 * 				Adam Ludovic;
 *				Arnould Killian;
 * 				De Bernardi Christophe;
 * 				Fockedey Aurelien;
 * 				Mathieu Robin;
 * 				Modave Louis;
 * 				}
 */

package Servlet;

import java.io.IOException;
import java.time.LocalDate;
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

@WebServlet("/Connexion")	// Balise indiquant au servlet la cible de la requete HTTP d�clenchant son appel
public class ServletConnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/* Constructeur par d�faut, explicit� pour g�n�rer la javadoc */
	/**
	 * Constructeur par d�faut; ne fait rien.
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		boolean failed = false;
		boolean firstConnection = false;
		session.setAttribute("firstConnection", firstConnection);
		session.setAttribute("relais", false);
		session.setAttribute("charge", false);
		
		if(mail.indexOf("student") != -1)
		{
			Etudiant etudiant = new DAOEtudiant().find(mail, password);
			if(etudiant == null) {
				request.setAttribute("connectionFailed", true);
				this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
				failed = true;
			}
			else if(etudiant.getFirstName() == null) firstConnection = true;
			session.setAttribute("etudiant", etudiant);
		}
		else
		{
			Representant rep = null;
			Professeur prof = new DAOProfesseur().find(mail, password);
			if(prof == null) {
				rep = new DAORepresentant().find(mail, password);
				if(rep != null){
					session.setAttribute("relais", true);
					session.setAttribute("charge", true);
					session.setAttribute("representant", rep);
				}else{
					request.setAttribute("connectionFailed", true);
					this.getServletContext().getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request,  response);
					failed = true;
				}
			}
			else if(prof.getFirstName() == null) firstConnection = true;
			session.setAttribute("professeur", prof);
		}
		
		if(!failed && firstConnection == false)
		{
			Professeur prof = (Professeur) session.getAttribute("professeur");
			if( (prof != null && new DAOProfesseur().findRelais(prof.getId()) != null) || (boolean)session.getAttribute("charge")  ){
				session.setAttribute("relais", true);
			}else{
				session.setAttribute("relais", false);
			}
			
			int cpt = 5; // nombre d'evenement
			ArrayList<Evenement> evens = new DAOEvenement().findNow(0,cpt);
			int compteur = 0;
			for(Evenement even : evens){
				ArrayList<Plage> p = new DAOEvenement().findListePlage(even);
				ArrayList<Plage> plage = new ArrayList<Plage>();
				for(Plage pl : p){
					boolean add = true;
					for(Plage pls : plage){
						if(add)add = (!pls.getDate().toString().equals(pl.getDate().toString()));
					}
					if(add)plage.add(pl);
				}
				even.setListPlage(plage);
				compteur++;
			}
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			request.setAttribute("evens", evens);
			request.setAttribute("debut", 0);
			request.setAttribute("suiv", compteur == cpt);
			request.setAttribute("cpt", 1);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ListEvenement.jsp").forward(request, response);
		}
		else if(!failed && firstConnection == true)
		{
			Etudiant etu = (Etudiant)session.getAttribute("etudiant");
			Professeur prof = (Professeur)session.getAttribute("professeur");
			session.setAttribute("firstConnection", firstConnection);
			request.setAttribute("firstConnection", firstConnection);
			boolean isEtu = (etu != null)?true:false;
			ArrayList<Section> sects = new DAOSection().findAll();
			if(isEtu){
				etu.setAdr(new DAOEtudiant().findAddr(etu.getId()));
				etu.setSec(new DAOEtudiant().findSect(etu.getId()));
				request.setAttribute("rep", etu);
				request.setAttribute("mail", etu.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+etu.getMail().split("@")[1]);
				request.setAttribute("adr", etu.getAdr());
			}else{
				request.setAttribute("rep", prof);
				request.setAttribute("mail", prof.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+prof.getMail().split("@")[1]);
				for(Section sect : sects){
					sect.setProf(new DAOSection().findListeProfesseur(sect));
				}
			}
			request.setAttribute("sects", sects);
			request.setAttribute("isEtu", isEtu);
			request.setAttribute("isProf", prof != null);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request,  response);
		}
	}
}
