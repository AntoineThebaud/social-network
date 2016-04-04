package social.network;

import java.io.IOException;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Connexion extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String addrMail = req.getParameter("mail");
		String pw = req.getParameter("pw");
		boolean succes = false;
		
		//Requete dans la base ...
		List<Personne> personnes = ofy().load().type(Personne.class).filter("mail ==", addrMail).list();
		
		//Analyse des resultats ...
		for (int i=0; i < personnes.size(); i++){
			if (personnes.get(i).getMail().equals(addrMail)) {
				if (personnes.get(i).getMdp().equals(pw)) {
					//Success !
					HttpSession session = req.getSession();
					session.setAttribute("Nom", personnes.get(i).getNom());
					session.setAttribute("Prenom", personnes.get(i).getPrenom());
					session.setAttribute("Mail", personnes.get(i).getMail());
					session.setAttribute("Valide", true);
					succes = true;
					resp.sendRedirect("/index.jsp");
				}
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
}
