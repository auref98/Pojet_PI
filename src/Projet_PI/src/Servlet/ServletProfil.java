/*
 * Haute �cole Robert Schuman - Libramont, ann�e scolaire 2017 - 2018
 * Bachelier en informatique de gestion, bloc 2	
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

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Professeur;
import Bean.Representant;
import Bean.Section;
import DAO.DAOAdresse;
import DAO.DAOEtudiant;
import DAO.DAOProfesseur;
import DAO.DAORepresentant;
import DAO.DAOSection;

/**
 * Classe de type "servlet" redirigeant les requ�tes adress�es � la page de profil. <br><br> 
 * @see HttpServlet
 */
@WebServlet("/Profil")	// Balise indiquant au servlet la cible de la requete HTTP d�clenchant son appel
public class ServletProfil extends HttpServlet{
	
/* Constructeur par d�faut, explicit� pour g�n�rer la javadoc */
/**
 * Constructeur par d�faut; ne fait rien.
 */
public ServletProfil(){}
	
private static final long serialVersionUID = 1L;
	
/*protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request,  response);
	}*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
			
			//Etudiant etu = (Etudiant)session.getAttribute("etudiant");
			//Professeur prof = (Professeur)session.getAttribute("professeur");
			boolean isEtu = etu != null;
			boolean isProf = prof != null;
			boolean firstConnection = false;
			firstConnection = (boolean) session.getAttribute("firstConnection");
			request.setAttribute("firstConnection", firstConnection);
			ArrayList<Section> sects = new DAOSection().findAll();
			if(isEtu){
				//if(etu.getAdr() == null) etu.setAdr(new DAOAdresse().find(etu.getAdr().getId()));
				etu.setAdr(new DAOEtudiant().findAddr(etu.getId()));
				etu.setSec(new DAOEtudiant().findSect(etu.getId()));
				request.setAttribute("rep", etu);
				request.setAttribute("mail", etu.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+etu.getMail().split("@")[1]);
				request.setAttribute("adr", etu.getAdr());
			}else if(isProf){
				request.setAttribute("rep", prof);
				request.setAttribute("mail", prof.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+prof.getMail().split("@")[1]);
				for(Section sect : sects){
					sect.setProf(new DAOSection().findListeProfesseur(sect));
				}
			}else{
				request.setAttribute("rep", repre);
				request.setAttribute("mail", repre.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+repre.getMail().split("@")[1]);
			}
			request.setAttribute("sects", sects);
			request.setAttribute("isEtu", isEtu);
			request.setAttribute("isProf", isProf);
			request.setAttribute("autreutilisateur", false);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Profil.jsp");
			reqDisp.forward(request, response);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
			
			//Etudiant etu = (Etudiant)session.getAttribute("etudiant");
			//Professeur prof = (Professeur)session.getAttribute("professeur");
			boolean firstConnection = false;
			firstConnection = (boolean) session.getAttribute("firstConnection");
			request.setAttribute("firstConnection", firstConnection);
			boolean isEtu = etu != null;
			boolean isProf = prof != null;
			ArrayList<Section> sects = new DAOSection().findAll();
			if(isEtu){
				//if(etu.getAdr() != null && ) etu.setAdr(new DAOAdresse().find(etu.getAdr().getId()));
				etu.setAdr(new DAOEtudiant().findAddr(etu.getId()));
				etu.setSec(new DAOEtudiant().findSect(etu.getId()));
				request.setAttribute("rep", etu);
				request.setAttribute("mail", etu.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+etu.getMail().split("@")[1]);
				request.setAttribute("adr", etu.getAdr());
			}else if(isProf){
				request.setAttribute("rep", prof);
				request.setAttribute("mail", prof.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+prof.getMail().split("@")[1]);
				for(Section sect : sects){
					sect.setProf(new DAOSection().findListeProfesseur(sect));
				}
			}else{
				request.setAttribute("rep", repre);
				request.setAttribute("mail", repre.getMail().split("@")[0]);
				request.setAttribute("hers", "@"+repre.getMail().split("@")[1]);
			}
			request.setAttribute("sects", sects);
			request.setAttribute("isEtu", isEtu);
			request.setAttribute("isProf", isProf);
			request.setAttribute("autreutilisateur", false);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Profil.jsp");
			reqDisp.forward(request, response);
		}
	}
}
