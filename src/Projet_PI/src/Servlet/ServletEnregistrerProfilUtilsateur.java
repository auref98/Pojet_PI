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

@WebServlet("/EnregistrerProfilUtilsateur")
public class ServletEnregistrerProfilUtilsateur extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
			
			String lastName = request.getParameter("Nom");
			String firstName = request.getParameter("Prenom");		
			String phone = request.getParameter("Phone");										
			String mail = request.getParameter("Mail");	
			String matricule = request.getParameter("Matricule");		
			String password = request.getParameter("ConfirmerNouveauMotDePasse");
			
			boolean enregistrementSuccess = false;
			int id = -1;
			try {
				id = Integer.parseInt(request.getParameter("representant"));
			} catch (NullPointerException e) {
				System.out.println("Erreur : impossible de parse (ServletMofifierProfilUtilisateur)");
			}
			Etudiant et = new DAOEtudiant().find(id);
			Professeur pr = null;
			if(et == null)pr = new DAOProfesseur().find(id);
			
			if(et != null)
			{
				et.setLastname(lastName);
				et.setFirstname(firstName);
				et.setPhone(phone);
				et.setMail(mail+"@student.hers.be");
				et.setMatricule(matricule);
				et.setPassword(password);
				
				et.setDateNaissance(LocalDate.parse(request.getParameter("DateNaissance"), DateTimeFormatter.ISO_DATE));			
				et.setPaysNaissance(request.getParameter("PaysNaissance"));					
				et.setLieuNaissance(request.getParameter("LieuNaissance"));					
				et.setNumNational(request.getParameter("NumNational"));			
				et.setNationalite(request.getParameter("Nationalite"));				
				et.setNumBanque(request.getParameter("NumBanque"));			
				et.setSoutienSocial( (request.getParameter("SoutienSocial") != null)?true:false );							
				et.setEmplacementEcole(request.getParameter("EmplacementEcole"));		
				et.setRole(request.getParameter("Role"));		
	
				String localite = request.getParameter("Localite");
				int codePostal = Integer.parseInt(request.getParameter("CodePostal"));
				String rue = request.getParameter("Rue");
				int numero = Integer.parseInt(request.getParameter("Numero"));
				String boite = request.getParameter("Boite");
				String pays = request.getParameter("Pays");
				Adresse adr = new Adresse(-1, localite, codePostal, rue, numero, boite, pays);
				new DAOAdresse().find(adr);
				if(adr.getId() == -1) new DAOAdresse().create(adr);
				et.setAdr(adr);
				
				int idSect = Integer.parseInt(request.getParameter("Section"));
				Section sec = new DAOSection().find(idSect);
				et.setSec(sec);
				enregistrementSuccess = new DAOEtudiant().update(et);
			}
			else if(pr != null)
			{
				pr.setLastname(lastName);
				pr.setFirstname(firstName);
				pr.setPhone(phone);
				pr.setMail(mail+"@hers.be");
				pr.setMatricule(matricule);
				pr.setPassword(password);
				
				Enumeration sections = request.getParameterNames();
				DAOProfesseur daoProf = new DAOProfesseur();
				pr.setEnseigne(daoProf.findEnseigne(pr.getId()));
				for(Section sect : pr.getEnseigne()){
					daoProf.removeEnseigne(pr.getId(), sect.getId());
				}
				while(sections.hasMoreElements()){
					String section = (String)sections.nextElement();
					String []sect = section.split("-");
					if(sect[0].equals("section")){
						int idS = -1;
						try{
							idS = Integer.parseInt(sect[1]);
						}catch(Exception e){
							System.out.println("Integer parse -> ServletEnrgistrement");
						}
						if(idS > 0){
							daoProf.addEnseigne(pr.getId(), idS);
						}
					}
				}
				
				enregistrementSuccess = new DAOProfesseur().update(pr);
			}
			
			/**
			 * redirection vers Liste rep
			 */
			ArrayList<Professeur> tabProf = new DAOProfesseur().findAll();
			ArrayList<Etudiant> tabEtud = new DAOEtudiant().findAll();
			if(tabProf != null){
				for(Professeur p : tabProf){
					if(p.getMatricule() == null || p.getMatricule() == "")tabProf.remove(p);
				}
			}
			if(tabEtud != null){
				for(int i = 0; i < tabEtud.size(); i++){
					if(tabEtud.get(i).getMatricule() == null || tabEtud.get(i).getMatricule() == "")tabEtud.remove(i);
				}
			}
			
			request.setAttribute("prof", tabProf);
			request.setAttribute("etu", tabEtud);
			RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/ListeRep.jsp");
			reqDisp.forward(request, response);
		}
	}
}
