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
    Personne personneCourante = service.getPersonne((String) session.getAttribute("Mail"));
    if (req.getParameter("id") != null && !req.getParameter("id").equals(session.getAttribute("Id").toString())) {
    	System.out.println("id = "+req.getParameter("id"));
    	System.out.println("id de la session courante = "+session.getAttribute("Id"));
      //On ne veut pas afficher le profil de la session connectée.
      //Requete pour recuperer le nom, prenom, slogan, liste des interets et des follower de la Personne concernée.
    	//A des fin de tests, creation de la condition suivante.
    	Personne personne2 = service.getPersonne(Long.parseLong(req.getParameter("id")));
    	req.setAttribute("Nom", personne2.getNom());
	    req.setAttribute("Prenom", personne2.getPrenom());
	    req.setAttribute("Slogan", personne2.getSlogan());
	    
	    //Pour savoir quel type de statut mettre, il faut savoir si la session courante suit deja la personne qu'il consulte.
	    List<Personne> amisComtpeCourant = service.getAmis(personneCourante);
	    System.out.println("Affichage de la liste des amis de la session courante :");
	    boolean statut = false;
	    for (int i = 0; i < amisComtpeCourant.size(); i++) {
			System.out.println(amisComtpeCourant.get(i).getPrenom());
			if (amisComtpeCourant.get(i).getId().equals(personne2.getId())) {
				statut = true;
			}
		}
	    if (statut) {
	    	req.setAttribute("Statut", "Suivi");
		}else {
			req.setAttribute("Statut", "NonSuivi");
		}
	    req.setAttribute("resultatAmis", service.getAmis(personne2));
		System.out.println("resulatsAmis taille : " + service.getAmis(personne2).size());
		req.setAttribute("resultatInterets", service.getInterets(personne2));
		System.out.println("resulatsInterets taille : " + service.getInterets(personne2).size());
	    
	    try {
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
		}
    }else{
      //On veut afficher le profil de la session courante.
      req.setAttribute("Nom", session.getAttribute("Nom"));
      req.setAttribute("Prenom", session.getAttribute("Prenom"));
      req.setAttribute("Slogan", session.getAttribute("Slogan"));
      req.setAttribute("resultatAmis", service.getAmis(personneCourante));
	  System.out.println("resulatsAmis taille : " + service.getAmis(personneCourante).size());
	  req.setAttribute("resultatInterets", service.getInterets(personneCourante));
	  System.out.println("resulatsInterets taille : " + service.getInterets(personneCourante).size());
      //Recuperer la liste des interets et des follower de la personne.
      try {
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
			} catch (ServletException e) {
				System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
			}
    }
  }
}
