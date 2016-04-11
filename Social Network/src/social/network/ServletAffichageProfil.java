package social.network;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;


@SuppressWarnings("serial")
public class ServletAffichageProfil extends HttpServlet {
  //Cette servlet remplace une redirection vers /index.jsp.
  //On verifie si il y a un parametre
    //Si oui on recupere l'id et on cherche les informations en bdd de la personne
    //Sinon on redirige vers index.jsp et les variables de session contiennent deja les infos utiles.
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    System.out.println("Je suis dans le doGet de ServletAffichageProfil");
    HttpSession session = req.getSession();
    ServicePersonne service = new ServicePersonne();
    //Personne personneCourante = service.getPersonne((String) session.getAttribute("Mail"));
    if (req.getParameter("id") != null && !req.getParameter("id").equals(session.getAttribute("Id").toString())) {
		System.out.println("id = "+req.getParameter("id"));
		System.out.println("id de la session courante = "+session.getAttribute("Id"));
		//On ne veut pas afficher le profil de la session connectée.
		//Requete pour recuperer le nom, prenom, slogan, liste des interets et des follower de la Personne concernée.
		//A des fin de tests, creation de la condition suivante.
		Personne personne = service.getPersonne(Long.parseLong(req.getParameter("id")));
		req.setAttribute("Nom", personne.getNom());
		req.setAttribute("Prenom", personne.getPrenom());
		req.setAttribute("Slogan", personne.getSlogan());
		req.setAttribute("Id", personne.getId());
		Personne connecte = service.getPersonne((Long)session.getAttribute("Id"));
		if(connecte.estAmi(personne)){
			req.setAttribute("Statut", "Suivi");
		}
		else{
			req.setAttribute("Statut", "NonSuivi");
		}
		
		req.setAttribute("resultatAmis", service.getAmis(personne));
		System.out.println("resulatsAmis taille : " + service.getAmis(personne).size());
		req.setAttribute("resultatInterets", service.getInterets(personne));
		System.out.println("resulatsInterets taille : " + service.getInterets(personne).size());
		req.setAttribute("NbTags", service.getInterets(personne).size());
		req.setAttribute("NbAmis", service.getAmis(personne).size());
	    
	    try {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
		}
    }else{
		 //On veut afficher le profil de la session courante.
		 Personne personne = service.getPersonne((String) session.getAttribute("Mail"));
		 req.setAttribute("Nom", session.getAttribute("Nom"));
		 req.setAttribute("Prenom", session.getAttribute("Prenom"));
		 req.setAttribute("Slogan", session.getAttribute("Slogan"));
		 req.setAttribute("resultatAmis", service.getAmis(personne));
		 System.out.println("resulatsAmis taille : " + service.getAmis(personne).size());
		 req.setAttribute("resultatInterets", service.getInterets(personne));
		 System.out.println("resulatsInterets taille : " + service.getInterets(personne).size());
		 req.setAttribute("NbTags", service.getInterets(personne).size());
		 req.setAttribute("NbAmis", service.getAmis(personne).size());
		 //Recuperer la liste des interets et des follower de la personne.
		 try {
			 this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		 } catch (ServletException e) {
			System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
		 }
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
		Long id = Long.parseLong(req.getParameter("id"));
		String suivre = req.getParameter("suivre");
		//On etablit une requete dans le datastore pour recuperer les informations liées au compte.
		ServicePersonne service = new ServicePersonne();
		Personne client = service.getPersonne(mail);
		Personne ami = service.getPersonne(id);
		if(suivre.equals("true")){
			client.addAmi(ami);
		}
		else{
			client.removeAmi(ami);
		}
		service.update(client);
		
		req.setAttribute("Nom", ami.getNom());
		req.setAttribute("Prenom", ami.getPrenom());
		req.setAttribute("Slogan", ami.getSlogan());
		req.setAttribute("Id", ami.getId());
		if(client.estAmi(ami)){
			req.setAttribute("Statut", "Suivi");
		}
		else{
			req.setAttribute("Statut", "NonSuivi");
		}
		
		req.setAttribute("resultatAmis", service.getAmis(ami));
		System.out.println("resulatsAmis taille : " + service.getAmis(ami).size());
		req.setAttribute("resultatInterets", service.getInterets(ami));
		System.out.println("resulatsInterets taille : " + service.getInterets(ami).size());
		
		try {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		 } catch (ServletException e) {
			System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
		}
  	}
}
