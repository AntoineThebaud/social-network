package social.network;

import java.io.IOException;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;

@SuppressWarnings("serial")
public class ServletConnexion extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Dans le doPost de ServletConnexion");
		String mail = checkNull(req.getParameter("mail"));
		String pw = checkNull(req.getParameter("pw"));
		boolean succes = false;
		ServicePersonne service = new ServicePersonne();
		//Requete dans la base ...

		if(service.existsPersonne(mail)){
			Personne personne = service.getPersonne(mail);
			if (personne.getMdp().equals(pw)) {
				//Success !
				HttpSession session = req.getSession();
				session.setAttribute("Id", personne.getId());
				session.setAttribute("Nom", personne.getNom());
				session.setAttribute("Prenom", personne.getPrenom());
				session.setAttribute("Mail", personne.getMail());
				session.setAttribute("Slogan", personne.getSlogan());
				
				session.setAttribute("Valide", true);
				succes = true;
				resp.sendRedirect("/affichageProfil");
			}
		}
		//Sinon afficher une erreur dans la page d'inscription / connexion.
		if (!succes) {
			//Erreur
			req.setAttribute("erreur", "Identifiant ou mot de passe incorrect");
			try {
				this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(req, resp);
			} catch (ServletException e) {
				System.out.println("Erreur du forwarding dans la servlet Connexion");
			}
		}
	}

	private String checkNull(String s) {
	    if (s == null) {
	      return "";
	    }
	    return s;
	}
}
