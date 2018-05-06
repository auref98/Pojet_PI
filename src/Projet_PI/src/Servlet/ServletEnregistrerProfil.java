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

import Bean.Adresse;
import Bean.Etudiant;
import Bean.Professeur;
import Bean.Section;

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
		String password = request.getParameter("MotDePasse");
		
		HttpSession session = request.getSession(true);
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		
		if(etu != null)
		{						
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");
			LocalDate dateNaissance = LocalDate.parse(request.getParameter("DateNaissance"), formatter);			
			String paysNaissance = request.getParameter("PaysNaissance");					
			String lieuNaissance = request.getParameter("LieuNaissance");					
			String numNational = request.getParameter("NumNational");			
			String nationalite = request.getParameter("Nationalite");				
			String numBanque = request.getParameter("NumBanque");					
			//boolean soutienSocial = request.getParameter("SoutienSocial");							
			String emplacementEcole = request.getParameter("EmplacementEcole");		
			String role = request.getParameter("Role");		

			String localite = request.getParameter("Localite");
			int codePostal = Integer.parseInt(request.getParameter("CodePostal"));
			String rue = request.getParameter("Rue");
			int numero = Integer.parseInt(request.getParameter("Numero"));
			String boite = request.getParameter("Boite");
			String pays = request.getParameter("Pays");
			Adresse adr = new Adresse(0, localite, codePostal, rue, numero, boite, pays);
			Section sec;
		}
		else
		{
			
		}
	}

}
