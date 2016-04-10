package social.network;

import java.io.IOException;

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
    if (req.getParameter("id") != null ) {
    	System.out.println("id = "+req.getParameter("id"));
      //On ne veut pas afficher le profil de la session connectée.
      //Requete pour recuperer le nom, prenom, slogan, liste des interets et des follower de la Personne concernée.
    	//A des fin de tests, creation de la condition suivante.
    	if (req.getParameter("id").equals("123456")) {
    		req.setAttribute("Nom", "Durand");
    	    req.setAttribute("Prenom", "Maurice");
    	    req.setAttribute("Slogan", "Faut pas respirer la compote, ca fait tousser !");
    	    req.setAttribute("Statut", "Suivi");
    	    try {
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
			} catch (ServletException e) {
				System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
			}
		}
    }else{
      //On veut afficher le profil de la session courante.
      HttpSession session = req.getSession();
      ServicePersonne service = new ServicePersonne();
      Personne personne = service.getPersonne((String) session.getAttribute("Mail"));
      req.setAttribute("Nom", session.getAttribute("Nom"));
      req.setAttribute("Prenom", session.getAttribute("Prenom"));
      req.setAttribute("Slogan", session.getAttribute("Slogan"));
      req.setAttribute("resultatAmis", service.getAmis(personne));
	  System.out.println("resulatsAmis taille : " + service.getAmis(personne).size());
	  req.setAttribute("resultatInterets", service.getInterets(personne));
	  System.out.println("resulatsInterets taille : " + service.getInterets(personne).size());
      //Recuperer la liste des interets et des follower de la personne.
      try {
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
			} catch (ServletException e) {
				System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
			}
    }
  }
}
