package social.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;

public class ServletAffichageFlux extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans le doGet de ServletAffichageFlux");
		HttpSession session = req.getSession();
		ServicePersonne service = new ServicePersonne();
		
		Long id = Long.parseLong(req.getParameter("id"));
		List<Publication> mesFlux = service.researchMesFlux(id);
		
		//pour l'affichage : récupérer le nom & prenom des auteurs des publications.
		List<Personne> auteurs = new ArrayList<>();
		for(Publication p : mesFlux) {
			auteurs.add(service.getPersonne(p.getAuteur()));
		}
		
		req.setAttribute("mesFlux", mesFlux);
		req.setAttribute("mesFluxAuteurs", auteurs);
		
		//on affiche le flux, les attributs mesPublications et mesMessagesReçus de req sont mis à null
		req.setAttribute("mesPublications", null);
		req.setAttribute("mesMessagesReçus", null);
		
		//Redirection vers la meme page
		try {
			//besoin de recharger les infos de la page consultée donc appel à la servlet affichageProfil (TODO : peut mieux faire?)
			this.getServletContext().getRequestDispatcher("/affichageProfil").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur dans le forwarding (ServletAffichageFlux.java)");
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans doPost de la ServletAffichageMesPublications");
	}
}


////affichage des attributs de la session
//System.out.println("=============ATRIBUTES==============");
//Enumeration ea = req.getSession().getAttributeNames();
//while (ea.hasMoreElements()) {
//  String name = (String) ea.nextElement();
//  System.out.println(name + ": " + req.getSession().getAttribute(name));
//}
//System.out.println("====================================");