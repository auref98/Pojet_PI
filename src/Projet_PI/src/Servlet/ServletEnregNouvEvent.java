package Servlet;

import java.awt.Image;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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

import org.apache.tomcat.jni.Time;

import Bean.*;
import DAO.*;

@WebServlet("/EnregistrerEvenement")
public class ServletEnregNouvEvent extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
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
			
			String rue = request.getParameter("input-rue");
			int numero = Integer.parseInt(request.getParameter("input-numero"));
			String boite = request.getParameter("input-boite");
			int codePostal = Integer.parseInt(request.getParameter("input-codePostal"));
			String localite = request.getParameter("input-localite");
			String pays = request.getParameter("input-pays");
			Adresse adr = new Adresse(-1,localite,codePostal,rue,numero,boite,pays);
			new DAOAdresse().find(adr);
			boolean adrCree = false;
			if(adr.getId() == -1){
				new DAOAdresse().create(adr);
				adrCree = true;
			}
			
			String nomEven = request.getParameter("nomEvenement");
			int nbParticipant = Integer.parseInt(request.getParameter("input-personnerequise"));
			String description = request.getParameter("input-description");
			String img = request.getParameter("image");
			
			ArrayList<Section> sects = new ArrayList<Section>();
			Enumeration names = request.getParameterNames();
			while(names.hasMoreElements()){
				String nom = (String)names.nextElement();
				String[] noms = nom.split("-");
				if(noms[0].equals("section")){
					int id = -1;
					try{
						id = Integer.parseInt(noms[1]);
					}catch(Exception e){
						System.out.println("Parse int impossible");
					}
					if(id > 0){
						sects.add(new DAOSection().find(id));
					}
				}
			}
			
			Evenement eve = new Evenement(-1,nomEven,nbParticipant,description,img,adr);
			eve.setSection(sects);
			if(!new DAOEvenement().create(eve) && adrCree)new DAOAdresse().delete(adr);
			else{
				Enumeration enume = request.getParameterNames();
				while(enume.hasMoreElements()){
					String name = (String)enume.nextElement();
					String [] nameSplit = name.split("-");
					if(nameSplit[0].equals("date")){
						//int id = Integer.parseInt(nameSplit[1]);
						LocalDate date = LocalDate.parse(request.getParameter(name), DateTimeFormatter.ISO_DATE);
						LocalTime HDebut = LocalTime.parse(request.getParameter("debut-"+nameSplit[1]), DateTimeFormatter.ISO_LOCAL_TIME);
						LocalTime HFin = LocalTime.parse(request.getParameter("fin-"+nameSplit[1]), DateTimeFormatter.ISO_LOCAL_TIME);
						Plage p = new Plage(-1,date,HDebut,HFin,eve);
						new DAOPlage().create(p);
					}
				}
			}
			RequestDispatcher reqDisp = request.getRequestDispatcher("/ListEvenSuivPrec");
			reqDisp.forward(request, response);
			
			for(Section section : sects)
			{
				String[] tabDest;
				String dest = "";
				ArrayList<Professeur> listeProf = new DAOSection().findListeProfesseur(section);
				ArrayList<Etudiant> listeEtud = new DAOSection().findListeEtudiant(section);
				if(listeProf != null)for(Professeur professeur : listeProf) dest += professeur.getMail() + ":";
				if(listeEtud != null)for(Etudiant etudiant : listeEtud) dest += etudiant.getMail() + ":";
				tabDest = dest.split(":");
				
				for(int i = 0; i < tabDest.length; i++)
				{
					System.out.println(tabDest[i]);
				}
			}
		}
	}
}
