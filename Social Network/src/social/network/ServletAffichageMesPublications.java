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

public class ServletAffichageMesPublications extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans le doGet de ServletAffichageMesPublications");
		HttpSession session = req.getSession();
		ServicePersonne service = new ServicePersonne();
		
		Long id = Long.parseLong(req.getParameter("id"));
		List<Publication> mesPublications = service.researchMesPublications(id);
		
		//pour l'affichage : récupérer le nom & prenom des destinataires des publications.
		List<Personne> destinataires = new ArrayList<>();
		for(Publication p : mesPublications) {
			if(p.getDestinataire() == null) {
				destinataires.add(null);
			} else {
				destinataires.add(service.getPersonne(p.getDestinataire()));
			}
		}
		
		req.setAttribute("mesPublications", mesPublications);
		req.setAttribute("mesPublicationsDest", destinataires);
		//on affiche les messages envoyés, les attributs monFlux et mesMessagesReçus de req sont mis à null
		req.setAttribute("mesFlux", null);
		req.setAttribute("mesFluxAuteurs", null);
		req.setAttribute("mesMessagesReçus", null);
		req.setAttribute("mesMessagesReçusAuteurs", null);
		
		//Redirection vers la meme page
		try {
			//besoin de recharger les infos de la page consultée donc appel à la servlet affichageProfil (TODO : peut mieux faire?)
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
//System.out.println("=============ATRIBUTES==============");
//Enumeration ea = req.getSession().getAttributeNames();
//while (ea.hasMoreElements()) {
//  String name = (String) ea.nextElement();
//  System.out.println(name + ": " + req.getSession().getAttribute(name));
//}
//System.out.println("====================================");