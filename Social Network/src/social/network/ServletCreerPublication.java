package social.network;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;

public class ServletCreerPublication extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String contenu = req.getParameter("text");
		System.out.println("contenu = " + contenu);//TODO : remove line
		String dest = req.getParameter("destinataire");
		System.out.println("dest = " + dest);//TODO : remove line
		
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("Mail");
		
		ServicePersonne service = new ServicePersonne();
		Personne client = service.getPersonne(mail);
		
		//creation de la nouvelle publication
		service.creerPublication(client, contenu, dest);
	}
}
