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
		//Personne nouveau = new Personne();
		//nouvelUser.setAge(Integer.parseInt(req.getParameter("age")));
		String nom = checkNull(req.getParameter("nom"));
		String prenom = checkNull(req.getParameter("prenom"));
		String mail = checkNull(req.getParameter("mail"));
		/*int jour =  Integer.parseInt(checkNull(req.getParameter("jour")))*/
		/*int mois =  Integer.parseInt(checkNull(req.getParameter("mois")))*/
		/*int annee =  Integer.parseInt(checkNull(req.getParameter("annee")))*/
		String ville = checkNull(req.getParameter("ville"));
		/* interet... */
		String mdp = checkNull(req.getParameter("mdp"));
		/*
		nouveau.setMail();
		nouveau.setMdp(req.getParameter("mdp"));
		nouveau.setNom();
		nouveau.setPrenom();
		*/
		//nouvelUser.setVille(req.getParameter("ville"));
		jour = Integer.parseInt(checkNull(req.getParameter("jour")));
		mois = Integer.parseInt(checkNull(req.getParameter("mois")));
		annees = Integer.parseInt(checkNull(req.getParameter("annees")));
		
		//Verification du mail dans la base de données.
		//List<Personne> personnes = ofy().load().type(Personne.class).filter("mail ==", mail).list();
		ServicePersonne service = new ServicePersonne();
		if(service.exists(mail)){
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
		
		
		/*
		for (int i=0; i < personnes.size(); i++){
			if (personnes.get(i).getMail().equals(nouvelUser.getMail())) {
				//Erreur le client existe deja.
				System.out.println("L'adresse mail existe deja.");
				req.setAttribute("erreur", "L'adresse mail existe déjà !");
				erreur = true;
			}
		}
		*/
		
		//Formattage du nom => Premiere lettre en majuscule
		char_table = nom.toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		nom = new String(char_table);
		
		//Formatage du prenom => Premiere lettre en maj.
		char_table = prenom.toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		prenom = new String(char_table);
		
		//Formatage de la ville
		/*char_table = nouvelUser.getVille().toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		nouvelUser.setVille(new String(char_table));*/
		
		
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
			int dateNaissance = 0;
			Personne personne = new Personne(nom,prenom,mail,dateNaissance,ville,null,"");
			service.register(personne);
			
			//Creation d'une session
			HttpSession session = req.getSession();
			session.setAttribute("Nom", personne.getNom());
			session.setAttribute("Prenom", personne.getPrenom());
			session.setAttribute("Mail", personne.getMail());
			session.setAttribute("Valide", true);
			
			//Redirection vers son flux d'actualité.
			resp.sendRedirect("/index.jsp");
		}
	}
	
	private String checkNull(String s) {
	    if (s == null) {
	      return "";
	    }
	    return s;
	}
}
