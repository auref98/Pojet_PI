package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

@WebServlet("/EnregistrerProfil")
public class ServletEnregistrerProfil extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		Representant rep = (Representant)session.getAttribute("representant");
		if(etu == null & prof == null & rep == null){
			session.invalidate();
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Connexion.jsp");
			reqDisp.forward(request, response);
		}else{
		
			request.setAttribute("relais", (boolean)session.getAttribute("relais"));
			request.setAttribute("charge", (boolean)session.getAttribute("charge"));
		
			String lastName = request.getParameter("Nom");
			String firstName = request.getParameter("Prenom");		
			String phone = request.getParameter("Phone");										
			String mail = request.getParameter("Mail");	
			String matricule = request.getParameter("Matricule");		
			String password = request.getParameter("ConfirmerNouveauMotDePasse");
			String AncienMotDePasse = request.getParameter("AncienMotDePasse");
			
			//Etudiant etu = (Etudiant)session.getAttribute("etudiant");
			//Professeur prof = (Professeur)session.getAttribute("professeur");
			boolean enregistrementSuccess = false;
			
			Representant repre = etu;
			if(etu == null)repre = prof;
			if(repre == null)repre = rep;
			
			if(AncienMotDePasse != null && new DAORepresentant().find(repre.getMail(), AncienMotDePasse) != null){
				if(etu != null)
				{
					etu.setLastname(lastName);
					etu.setFirstname(firstName);
					etu.setPhone(phone);
					//etu.setMail(mail+"@student.hers.be");
					etu.setMatricule(matricule);
					etu.setPassword(password);
					
					etu.setDateNaissance(LocalDate.parse(request.getParameter("DateNaissance"), DateTimeFormatter.ISO_DATE));			
					etu.setPaysNaissance(request.getParameter("PaysNaissance"));					
					etu.setLieuNaissance(request.getParameter("LieuNaissance"));					
					etu.setNumNational(request.getParameter("NumNational"));			
					etu.setNationalite(request.getParameter("Nationalite"));				
					etu.setNumBanque(request.getParameter("NumBanque"));			
					etu.setSoutienSocial( (request.getParameter("SoutienSocial") != null)?true:false );							
					etu.setEmplacementEcole(request.getParameter("EmplacementEcole"));		
					etu.setRole(request.getParameter("Role"));		
		
					String localite = request.getParameter("Localite");
					int codePostal = Integer.parseInt(request.getParameter("CodePostal"));
					String rue = request.getParameter("Rue");
					int numero = Integer.parseInt(request.getParameter("Numero"));
					String boite = request.getParameter("Boite");
					String pays = request.getParameter("Pays");
					Adresse adr = new Adresse(-1, localite, codePostal, rue, numero, boite, pays);
					new DAOAdresse().find(adr);
					if(adr.getId() == -1) new DAOAdresse().create(adr);
					etu.setAdr(adr);
					
					int idSect = Integer.parseInt(request.getParameter("Section"));
					Section sec = new DAOSection().find(idSect);
					etu.setSec(sec);
					enregistrementSuccess = new DAOEtudiant().update(etu);
					session.setAttribute("etudiant", etu);
				}
				else if(prof != null)
				{
					prof.setLastname(lastName);
					prof.setFirstname(firstName);
					prof.setPhone(phone);
					//prof.setMail(mail+"@hers.be");
					prof.setMatricule(matricule);
					prof.setPassword(password);
					
					Enumeration sections = request.getParameterNames();
					DAOProfesseur daoProf = new DAOProfesseur();
					prof.setEnseigne(daoProf.findEnseigne(prof.getId()));
					for(Section sect : prof.getEnseigne()){
						daoProf.removeEnseigne(prof.getId(), sect.getId());
					}
					while(sections.hasMoreElements()){
						String section = (String)sections.nextElement();
						String []sect = section.split("-");
						if(sect[0].equals("section")){
							int id = -1;
							try{
								id = Integer.parseInt(sect[1]);
							}catch(Exception e){
								System.out.println("Integer parse -> ServletEnrgistrement");
							}
							if(id > 0){
								daoProf.addEnseigne(prof.getId(), id);
							}
						}
					}
					
					enregistrementSuccess = new DAOProfesseur().update(prof);
					session.setAttribute("professeur", prof);
				}
				else if(rep != null){
					rep.setLastname(lastName);
					rep.setFirstname(firstName);
					rep.setPhone(phone);
					rep.setMail(mail+"@hers.be");
					rep.setMatricule(matricule);
					rep.setPassword(password);
					
					enregistrementSuccess = new DAORepresentant().update(rep);
					session.setAttribute("representant", rep);
				}
			}
			if(enregistrementSuccess && (boolean)session.getAttribute("firstConnection"))session.setAttribute("firstConnection", false);
			request.setAttribute("enregistrementSuccess", enregistrementSuccess);
			this.getServletContext().getRequestDispatcher("/Profil").forward(request,  response);
		}
	}
}
