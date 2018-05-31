package Servlet;

import java.awt.Image;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.Iterator;

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
			
			String n = request.getParameter("dao");
			boolean create = (n == null || n.equals("create"));
			
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
				if(create)
					new DAOAdresse().create(adr);
				else
					new DAOAdresse().update(adr);
				adrCree = create;
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
			if(!create){
				int idEven = 0;
				try {
					idEven = Integer.parseInt(n.split("-")[1]);
				} catch (NullPointerException e) {
					System.out.println("Erreur : impossible de parse pour l'even(ServletEnregNouvEven)");
				}
				if(idEven>0){
					eve.setId(idEven);
					new DAOEvenement().update(eve);
				}
				ArrayList<Plage> ListePlage = new DAOEvenement().findListePlage(eve);
				for(Plage p : ListePlage)
					new DAOPlage().delete(p);
			}
			if(create && !new DAOEvenement().create(eve) && adrCree)new DAOAdresse().delete(adr);
			else{
				Enumeration enume = request.getParameterNames();
				while(enume.hasMoreElements()){
					String name = (String)enume.nextElement();
					String [] nameSplit = name.split("-");
					if(nameSplit[0].equals("date")){
						LocalDate date = LocalDate.parse(request.getParameter(name), DateTimeFormatter.ISO_DATE);
						LocalTime HDebut = LocalTime.parse(request.getParameter("debut-"+nameSplit[1]), DateTimeFormatter.ISO_LOCAL_TIME);
						LocalTime HFin = LocalTime.parse(request.getParameter("fin-"+nameSplit[1]), DateTimeFormatter.ISO_LOCAL_TIME);
						Plage p = new Plage(-1,date,HDebut,HFin,eve);
						new DAOPlage().create(p);
					}
				}
			}
			if(create){
				LinkedList<String> listeDest = new LinkedList<String>();
				for(Section section : sects)
				{
					ArrayList<Professeur> listeProf = new DAOSection().findListeProfesseur(section);
					ArrayList<Etudiant> listeEtud = new DAOSection().findListeEtudiant(section);
					if(listeProf != null)for(Professeur professeur : listeProf) listeDest.add(professeur.getMail());
					if(listeEtud != null)for(Etudiant etudiant : listeEtud) listeDest.add(etudiant.getMail());
				}
				
				HashSet<String> hs = new HashSet<String>(listeDest);
				java.util.Iterator it = hs.iterator(); 
				String destS = "";
				while(it.hasNext ()) destS += (String)it.next() + ":";  
			
				String[] tabDest = destS.split(":");
				String subject = "Nouvel évèvenement disponible !";
				String text= "HERSEvents Officiel vient de poster un nouvel évènement :\n"
						+ "\n" + eve.getNom()
						+ "\nVenez vous inscrire dès maintenant sur le site : \n http://localhost:8080/Projet_PI";
				
				EnvoieMail envoieMail = new EnvoieMail();
				envoieMail.send(tabDest, subject, text);
			}
			
			RequestDispatcher reqDisp = request.getRequestDispatcher("/ListEvenSuivPrec");
			reqDisp.forward(request, response);
		}
	}
}
