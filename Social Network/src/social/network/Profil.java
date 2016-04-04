package social.network;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Profil extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Quand on veut acceder a la page profil
		//Cette servlet va faire une requete dans le datastore pour recuperer les informations de la personne.
		//Ajoutes les variables a req.
		//Et enfin rediriger vers la page profil.jsp ainsi le client verra sa page pré-rempli avec ses informations.
		
		//On recupere la session en cours pour avoir l'adresse mail.
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("Mail");
		
		//On etablie une requete dans le datastore pour recuperer les informatiions liées au compte.
		Personne client = null;
		List<Personne> personnes = ofy().load().type(Personne.class).filter("mail ==", mail).list();
		for (int i=0; i < personnes.size(); i++){
			if (personnes.get(i).getMail().equals(mail)) {
				client = personnes.get(i); 
			}
		}
		
		// ----------------  /!\ WARNING /!\ -----------------------
		//Verifer que "client" contient bien une personne. Sinon nullPointerException !!!
		//Ce qui devrait etre le cas theoriquement.
		
		//A ce stade, nous avons recup la classe 'Personne' du client. Donc on ajoute
		//les variables dans req pour pré-emplir le formualaire.
		req.setAttribute("Nom", client.getNom());
		req.setAttribute("Prenom", client.getPrenom());
		req.setAttribute("Mail", client.getMail());
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//L'acces a cette servlet en "post" se fait uniquement apres avoir valider le formulaire
		//qui se trouve dans la page profil.jsp
		//La servlet va sauvegarder dans le datastore les nouvelles données apres verifications.
	}
}
