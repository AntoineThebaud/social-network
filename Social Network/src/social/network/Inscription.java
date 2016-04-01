package social.network;

import java.io.IOException;
import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.taskdefs.condition.Http;

import com.google.apphosting.utils.config.ClientDeployYamlMaker.Request;

public class Inscription extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans la servlet Inscription");
		char[] char_table;
		int jour, mois, annees;
		boolean erreur;
		erreur = false;
		Personne nouvelUser = new Personne();
		//nouvelUser.setAge(Integer.parseInt(req.getParameter("age")));
		nouvelUser.setMail(req.getParameter("mail"));
		nouvelUser.setMdp(req.getParameter("mdp"));
		nouvelUser.setNom(req.getParameter("nom"));
		nouvelUser.setPrenom(req.getParameter("prenom"));
		//nouvelUser.setVille(req.getParameter("ville"));
		jour = Integer.parseInt(req.getParameter("jour"));
		mois = Integer.parseInt(req.getParameter("mois"));
		annees = Integer.parseInt(req.getParameter("annees"));
		
		//Verification du mail dans la base de données.
		if(/*si un select dans la bdd sur mail renvoi un resultat*/true){
			//Erreur le client existe deja.
			//erreur = true;
		}
		
		//Verification du double mot de passe
		if(!req.getParameter("mdp2").equals(nouvelUser.getMdp())){
			System.out.println("Les deux mdp ne sont pas identiques");
			erreur = true;
		}
		
		//Formattage du nom => Premiere lettre en majuscule
		char_table = nouvelUser.getNom().toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		nouvelUser.setNom(new String(char_table));
		
		//Formatage du prenom => Premiere lettre en maj.
		char_table = nouvelUser.getPrenom().toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		nouvelUser.setPrenom(new String(char_table));
		
		//Formatage de la ville
		/*char_table = nouvelUser.getVille().toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		nouvelUser.setVille(new String(char_table));*/
		
		
		//Si tout est OK, inserer le nouvel utilisateur et rediriger.
		//Sinon renvoyer sur le formulaire avec une erreure.
		if (erreur) {
			resp.sendRedirect("/inscription.jsp");
		} else {
			//Sauvegarde dans le Datastore.
			ofy().save().entity(nouvelUser).now();
			
			//Creation d'une session
			HttpSession session = req.getSession();
			session.setAttribute("Nom", nouvelUser.getNom());
			session.setAttribute("Prenom", nouvelUser.getPrenom());
			session.setAttribute("Mail", nouvelUser.getMail());
			session.setAttribute("Valide", true);
			
			//Redirection vers son flux d'actualité.
			resp.sendRedirect("/index.jsp");
		}
	}
}