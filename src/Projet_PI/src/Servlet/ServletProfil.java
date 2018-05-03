package Servlet;

import java.io.IOException;
import java.time.LocalDate;

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

@WebServlet("/Profil.jsp")
public class ServletProfil extends HttpServlet{
	public ServletProfil(){}
	private static final long serialVersionUID = 1L;
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request,  response);
	}*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		HttpSession session = request.getSession(true);
		Etudiant etu = (Etudiant)session.getAttribute("etudiant");
		Professeur prof = (Professeur)session.getAttribute("professeur");
		/*if(rep == null){
			rep = new DAORepresentant().find(42);
			//Adresse adr = new Adresse(1,"Libramont",6800,"pas loin d'ici",4,"E","Belgique");
			//new DAOAdresse().create(adr);
			//Section sect = new Section(1,"Informatique",new DAOProfesseur().find(22));
			//new DAOSection().create(sect);
			//new DAOEtudiant().create(new Etudiant("Fock","Auref","+32.000.00.00.00","mailTest@student.hers.be","e160000",0,LocalDate.parse("1998-08-05"),"Belgique","Libramont","9808051365","Belge","BE0065445678",false,"Libramont","Presentant",adr,sect));
		}*/
		//Professeur prof = new DAOProfesseur().find(rep.getId());
		//Etudiant etu = new DAOEtudiant().find(rep.getId());
		boolean isEtu = (etu != null)?true:false;
		if(isEtu){
			if(etu.getAdr().getPays() == null)etu.setAdr(new DAOAdresse().find(etu.getAdr().getId()));
			request.setAttribute("rep", etu);
			request.setAttribute("adr", etu.getAdr());
		}else{
			request.setAttribute("rep", prof);
		}
		request.setAttribute("isEtu", isEtu);
		RequestDispatcher reqDisp = request.getRequestDispatcher("/WEB-INF/Profil.jsp");
		reqDisp.forward(request, response);
	}
}
