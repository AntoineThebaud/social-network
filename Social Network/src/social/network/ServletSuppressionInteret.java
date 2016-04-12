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

@SuppressWarnings("serial")
public class ServletSuppressionInteret extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans la servlet de suppression d'interet.");
		System.out.println("l'interet a supprimer est : ["+req.getParameter("interet")+"]");
		
		//Je recupere la session, pour l'id.
		HttpSession session = req.getSession();
		
		//Initialisation des services.
		ServicePersonne service = new ServicePersonne();
		//ServicePersonne serviceinteret = new ServiceInteret();
		
		//Recuperation de la personne et de l'interet concerné.
		Personne personne = service.getPersonne((Long)session.getAttribute("Id"));
		List<Interet> interets = service.researchInteret(req.getParameter("interet"));
		
		int i =0;
		for (i = 0; i < interets.size(); i++) {
			if (interets.get(i).getNom().equals("#"+req.getParameter("interet"))) {
				break;
			}
		}
		
		//Suppression de la ref de l'interet dans le personne.
		System.out.println("Je veut supprimer l'interet "+interets.get(i).getNom()+" de la personne "+ personne.getPrenom());
		personne.removeInteret(interets.get(i));
		service.update(personne);
		
		
		resp.sendRedirect("/affichageProfil");
	}
}
