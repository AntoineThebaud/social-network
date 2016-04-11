package social.network;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;

public class ServletAjoutInteret extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String input = "#"+req.getParameter("inputKey");
		System.out.println("input = " + input);//TODO : remove line
		
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("Mail");
		
		ServicePersonne service = new ServicePersonne();
		Personne client = service.getPersonne(mail);
		
		//Requete dans la base ...
		if(service.existsInteret(input)){
			System.out.println("exist : true");
		} else {	
			System.out.println("exist : false");
			service.creerInteret(client,input);
		}
		
		//return
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(input);
	}
}
