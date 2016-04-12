package social.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.condition.Http;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import social.network.services.ServicePersonne;

import com.google.apphosting.utils.config.ClientDeployYamlMaker.Request;

public class ServletInscription extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans la servlet Inscription");
		char[] char_table;
		int jour, mois, annees;
		boolean erreur;
		erreur = false;

		String nom = checkNull(req.getParameter("nom"));
		String prenom = checkNull(req.getParameter("prenom"));
		String mail = checkNull(req.getParameter("mail"));
		/* interet... */
		String mdp = checkNull(req.getParameter("mdp"));

		//nouvelUser.setVille(req.getParameter("ville"));
		jour = Integer.parseInt(checkNull(req.getParameter("jour")));
		mois = Integer.parseInt(checkNull(req.getParameter("mois")));
		annees = Integer.parseInt(checkNull(req.getParameter("annees")));

		//Verification du mail dans la base de données.
		//List<Personne> personnes = ofy().load().type(Personne.class).filter("mail ==", mail).list();
		ServicePersonne service = new ServicePersonne();
		if(service.existsPersonne(mail)){
			System.out.println("L'adresse mail existe deja.");
			req.setAttribute("erreur", "L'adresse mail existe déjà !");
			erreur = true;
		}

		//Verification du double mot de passe
		String mdp2 = checkNull(req.getParameter("mdp2"));

		if(!mdp2.equals(mdp)){
			System.out.println("Les deux mdp ne sont pas identiques");
			req.setAttribute("erreur", "Les deux mots de passe ne sont pas identiques !");
			erreur = true;
		}

		//Formattage du nom => Premiere lettre en majuscule
		char_table = nom.toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		nom = new String(char_table);

		//Formatage du prenom => Premiere lettre en maj.
		char_table = prenom.toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		prenom = new String(char_table);


		//Si tout est OK, inserer le nouvel utilisateur et rediriger.
		//Sinon renvoyer sur le formulaire avec une erreure.
		if (erreur) {
			//resp.sendRedirect("/inscription.jsp");
			try {
				this.getServletContext().getRequestDispatcher("/inscription.jsp").forward(req, resp);
			} catch (ServletException e) {
				System.out.println("erreur dans le forwarding !");
			}
		} else {
			//Sauvegarde dans le Datastore.
			String defautSlogan = "Un slogan, une description !";
			Personne personne = new Personne(nom, prenom, mail, jour,mois,annees, mdp, defautSlogan);
			service.register(personne);

			//Creation d'une session
			HttpSession session = req.getSession();
			session.setAttribute("Id", personne.getId());
			session.setAttribute("Nom", personne.getNom());
			session.setAttribute("Prenom", personne.getPrenom());
			session.setAttribute("Mail", personne.getMail());
			session.setAttribute("Slogan", defautSlogan);
			session.setAttribute("Valide", true);

			//Redirection vers son flux d'actualité.
			resp.sendRedirect("/affichageProfil");
		}
	}

	private String checkNull(String s) {
	    if (s == null) {
	      return "";
	    }
	    return s;
	}
}
