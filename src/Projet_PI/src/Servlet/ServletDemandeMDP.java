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
		
		//Edition message
				EnvoieMail envoieMail = new EnvoieMail();
				String[] tabDest = new String[] {mail};
				String subject = "Inscription validée !";
				String text = "Bonjour et bienvenu sur l'application HersEvent Officielle.\n"
							+ "Suite à votre demande d'inscription sur l'application HersEvent Officielle,\n"
							+ "voici votre mot de passe (que vous pourrez modifier par la suite):\n\n";
				String signature;
		
		if(new DAORepresentant().find(mail) != null)
			request.setAttribute("inscriptionSuccess", false);
		else
		{
			if(nomDom[1].equals("hers.be"))
			{
				Professeur prof = new Professeur();
				prof.setMail(mail);
				new DAOProfesseur().create(prof);
				text += prof.getPassword();
			}
			else if(nomDom[1].equals("student.hers.be"))
			{
				Etudiant etud = new Etudiant();
				etud.setMail(mail);
				new DAOEtudiant().create(etud);
				text += etud.getPassword();
			}
			
			signature = "\n\nBien à vous,\n"
						+ "Le groupe NamingException.";
			text += signature;
			envoieMail.send(tabDest, subject, text);
			request.setAttribute("inscriptionSuccess", true);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request,  response);
	}
}
