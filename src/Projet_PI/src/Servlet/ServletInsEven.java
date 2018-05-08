package Servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inscriptionEvenement")
public class ServletInsEven extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Enumeration names = request.getParameterNames();
		while(names.hasMoreElements()){
			//System.out.println((String)names.nextElement());
			System.out.println(request.getParameter((String)names.nextElement()));
		}
	}
}
