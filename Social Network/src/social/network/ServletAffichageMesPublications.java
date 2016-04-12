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
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans doPost de la ServletAffichageMesPublications");
		HttpSession session = req.getSession();
		//requete dans le datastore pour récuperer les posts publiée par l'utilisateur
		Long id = (Long) session.getAttribute("Id");
		ServicePersonne service = new ServicePersonne();
		List<Publication> mesPublications = service.researchMesPublications(id);
		req.setAttribute("mesPublications", mesPublications);
		
		//test affichage:
		System.out.println("==============CONTENU==============");
		for(Publication p : mesPublications) {
			System.out.println(p.getContenu());
		}
		System.out.println("===================================");
		
		//Redirection vers la meme page
		try {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur dans le forwarding (ServletAffichageMesPublications.java)");
			e.printStackTrace();
		}
	}
}


////affichage des attributs de la session
//Enumeration e = session.getAttributeNames();
//while (e.hasMoreElements()) {
//  String name = (String) e.nextElement();
//  System.out.println(name + ": " + session.getAttribute(name));
//}