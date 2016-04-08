package social.network;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;

public class ServletRecherche extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans le doGet de la servlet Recherche");	
		/*
		//On recupere la session en cours 
		HttpSession session = req.getSession();
		String recherche = (String) session.getAttribute("Recherche");
		
		//On etablie une requete dans le datastore le resultat de la recherche
		ServicePersonne service = new ServicePersonne();
		List<Personne> resultat = service.researchPersonne(recherche);
		
		req.setAttribute("resultat", resultat);
		*/
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans doPost de la servlet Recherche");
		//Quand on veut acceder a un resultat d'une recherche
		//Cette servlet va faire une requete dans le datastore pour recuperer les informations par rapport a la recherche
		//Et enfin rediriger vers la page recherche.jsp 

		//On recupere la session en cours pour avoir l'adresse mail.
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("Mail");

		//On etablit une requete dans le datastore pour recuperer les informations liées au compte.
		ServicePersonne service = new ServicePersonne();
		Personne client = service.getPersonne(mail);
		
		String recherche = checkNull(req.getParameter("recherche"));
		
		session.setAttribute("Nom", client.getNom());
		session.setAttribute("Prenom", client.getPrenom());
		session.setAttribute("Mail", client.getMail());
		session.setAttribute("Slogan", client.getSlogan());
		session.setAttribute("Recherche", client.getSlogan());
		
		//On etablie une requete dans le datastore le resultat de la recherche
		List<Personne> resultat = service.researchPersonne(recherche);
		req.setAttribute("resultat", resultat);
		//session.setAttribute("resultat", resultat);
		System.out.println("size : " + resultat.size());
		
		System.out.println("recherche : " + recherche);
		
		//Redirection vers la page recherche.jsp
		try {
			this.getServletContext().getRequestDispatcher("/recherche.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur dans le forwarding (ServletRecherche.java)");
			e.printStackTrace();
		}
	}
	
	private String checkNull(String s) {
	    if (s == null) {
	      return "";
	    }
	    return s;
	}

}
