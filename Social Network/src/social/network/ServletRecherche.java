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
		 * 
		 */
		ServicePersonne service = new ServicePersonne();
		String recherche = checkNull(req.getParameter("recherche"));
		
		String filtre = req.getParameter("filtre");
		
		if (filtre == null) {
			//On etablie une requete dans le datastore le resultat de la recherche
			List<Personne> resultatPersonne = service.researchPersonne(recherche);
			req.setAttribute("resultatPersonne", resultatPersonne);
			
			List<Interet> resultatInteret = service.researchInteret(recherche);
			req.setAttribute("resultatInteret", resultatInteret);
			
			List<Publication> resultatPublication = service.researchPublication(recherche);
			req.setAttribute("resultatPublication", resultatPublication);
		}
		else if(filtre.equals("comptes")){
			List<Personne> resultatPersonne = service.researchPersonne(recherche);
			req.setAttribute("resultatPersonne", resultatPersonne);
		}
		else if(filtre.equals("interets")){
			List<Interet> resultatInteret = service.researchInteret(recherche);
			req.setAttribute("resultatInteret", resultatInteret);
		}
		else if(filtre.equals("publications")){
			List<Publication> resultatPublication = service.researchPublication(recherche);
			req.setAttribute("resultatPublication", resultatPublication);
		}
		else{
			/* a completer*/
		}
		
		//Redirection vers la page recherche.jsp
		try {
			this.getServletContext().getRequestDispatcher("/recherche.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur dans le forwarding (ServletRecherche.java)");
			e.printStackTrace();
		}
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
		List<Personne> resultatPersonne = service.researchPersonne(recherche);
		req.setAttribute("resultatPersonne", resultatPersonne);
		
		List<Interet> resultatInteret = service.researchInteret(recherche);
		req.setAttribute("resultatInteret", resultatInteret);
		
		List<Publication> resultatPublication = service.researchPublication(recherche);
		req.setAttribute("resultatPublication", resultatPublication);
		
		
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
