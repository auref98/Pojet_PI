package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.*;
import DAO.*;

@WebServlet("/DemandeMDP")
public class ServletDemandeMDP extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String mail = request.getParameter("email");
		String[] nomDom = mail.split("@");
		
		if(new DAORepresentant().find(mail) != null)
			request.setAttribute("inscriptionSuccess", false);
		else
		{
			if(nomDom[1].equals("hers.be"))
			{
				Professeur prof = new Professeur();
				prof.setMail(mail);
				new DAOProfesseur().create(prof);
			}
			else if(nomDom[1].equals("student.hers.be"))
			{
				Etudiant etud = new Etudiant();
				etud.setMail(mail);
				new DAOEtudiant().create(etud);
			}
			request.setAttribute("inscriptionSuccess", true);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request,  response);
	}

}
