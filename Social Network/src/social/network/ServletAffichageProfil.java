package social.network;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
      //Requete pour recuperer le nom, prenom, slogan de la Personne concernée.
    }else{
      //On veut afficher le profil de la session courante.
      HttpSession session = req.getSession();
      req.setAttribute("Nom", session.getAttribute("Nom"));
      req.setAttribute("Prenom", session.getAttribute("Prenom"));
      req.setAttribute("Slogan", session.getAttribute("Slogan"));
      try {
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
			} catch (ServletException e) {
				System.out.println("Erreur du forwarding dans la servlet AffichageProfil");
			}
    }
  }
}
