package social.network;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;

public class ServletAffichageMesPublications extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans le doGet de ServletAffichageMesPublications");
		HttpSession session = req.getSession();
		//requete dans le datastore pour récuperer les posts publiée par l'utilisateur
		Long id = (Long) session.getAttribute("Id");
		ServicePersonne service = new ServicePersonne();
		List<Publication> mesPublications = service.researchMesPublications(id);
		req.setAttribute("mesPublications", mesPublications);
		
		//on affiche les messages envoyés, les attributs monFlux et mesMessagesReçus de req sont mis à null
		req.setAttribute("monFlux", null);
		req.setAttribute("mesMessagesReçus", null);
		
		//Redirection vers la meme page
		try {
			//la redirection sur index.jsp ne marche pas (pourquoi??) donc forward sur affichageProfil TODO : à changer
			//this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
			this.getServletContext().getRequestDispatcher("/affichageProfil").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur dans le forwarding (ServletAffichageMesPublications.java)");
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans doPost de la ServletAffichageMesPublications");
	}
}


////affichage des attributs de la session
//Enumeration e = session.getAttributeNames();
//while (e.hasMoreElements()) {
//  String name = (String) e.nextElement();
//  System.out.println(name + ": " + session.getAttribute(name));
//}