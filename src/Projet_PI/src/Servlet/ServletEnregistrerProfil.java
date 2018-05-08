package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
		String lastName = request.getParameter("Nom");
		String firstName = request.getParameter("Prenom");		
		String phone = request.getParameter("Phone");										
		String mail = request.getParameter("Mail");	
		String matricule = request.getParameter("Matricule");		
		String password = request.getParameter("ConfirmerNouveauMotDePasse");
		
		HttpSession session = request.getSession(true);
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		boolean enregistrementSuccess = false;
		
		if(etu != null)
		{
			etu.setLastname(lastName);
			etu.setFirstname(firstName);
			etu.setPhone(phone);
			etu.setMail(mail);
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
		else
		{
			prof.setLastname(lastName);
			prof.setFirstname(firstName);
			prof.setPhone(phone);
			prof.setMail(mail);
			prof.setMatricule(matricule);
			prof.setPassword(password);
			
			enregistrementSuccess = new DAOProfesseur().update(prof);
			session.setAttribute("professeur", prof);
		}
		
		request.setAttribute("enregistrementSuccess", enregistrementSuccess);
		this.getServletContext().getRequestDispatcher("/Profil").forward(request,  response);
	}
}
