package social.network;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import social.network.services.ServicePersonne;

@SuppressWarnings("serial")
public class ServletProfil extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans le doGet de profil");
		//Quand on veut acceder a la page profil
		//Cette servlet va faire une requete dans le datastore pour recuperer les informations de la personne.
		//Ajoutes les variables a req.
		//Et enfin rediriger vers la page profil.jsp ainsi le client verra sa page pré-rempli avec ses informations.

		//On recupere la session en cours pour avoir l'adresse mail.
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("Mail");

		//On etablie une requete dans le datastore pour recuperer les informatiions liées au compte.
		ServicePersonne service = new ServicePersonne();
		Personne client = service.getPersonne(mail);

		// ----------------  /!\ WARNING /!\ -----------------------
		//Verifer que "client" contient bien une personne. Sinon nullPointerException !!!
		//Ce qui devrait etre le cas theoriquement.

		//A ce stade, nous avons recup la classe 'Personne' du client. Donc on ajoute
		//les variables dans req pour pré-emplir le formualaire.

		req.setAttribute("Nom", client.getNom());
		req.setAttribute("Prenom", client.getPrenom());
		req.setAttribute("Mail", client.getMail());
		req.setAttribute("Slogan", client.getSlogan());
		req.setAttribute("resultatAmis", service.getAmis(client));
		System.out.println("resulatsAmis taille : " + service.getAmis(client).size());
		req.setAttribute("resultatInterets", service.getInterets(client));
		System.out.println("resulatsInterets taille : " + service.getInterets(client).size());
		req.setAttribute("NbTags", service.getInterets(client).size());
		req.setAttribute("NbAmis", service.getAmis(client).size());
		req.setAttribute("tendances", service.getTendances());
	
		//Redirection vers la page profil.jsp
		try {
			this.getServletContext().getRequestDispatcher("/profil.jsp").forward(req, resp);
		} catch (ServletException e) {
			System.out.println("Erreur dans le forwarding (Profil.java)");
		}	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans le doPost de profil");
		//L'acces a cette servlet en "post" se fait uniquement apres avoir valider le formulaire
		//qui se trouve dans la page profil.jsp
		//La servlet va sauvegarder dans le datastore les nouvelles données apres verifications.
		HttpSession session = req.getSession();
		String mail = (String) session.getAttribute("Mail");

		System.out.println("Mail : " + mail);
		String slogan = checkNull(req.getParameter("Slogan"));
		String nom = checkNull(req.getParameter("Nom"));
		String prenom = checkNull(req.getParameter("Prenom"));
		session.setAttribute("Slogan", slogan);
		session.setAttribute("Nom", nom);
		session.setAttribute("Prenom", prenom);


		ServicePersonne service = new ServicePersonne();
		Personne client = service.getPersonne(mail);
		req.setAttribute("tendances", service.getTendances());
		
		//Si l'utilisateur veut changer son mot de passe.
		//On test que le premier input de mot de passe soit rempli.
		String pwActuel = "";
		String pwNew = "";
		String pwNewConfirme = "";
		String erreur = "";
		if (!req.getParameter("Pw1").equals("") && !req.getParameter("Pw2").equals("") && !req.getParameter("Pw3").equals("")){
			pwActuel = req.getParameter("Pw1");
			System.out.println("Pw1 = "+pwActuel);
			pwNew = req.getParameter("Pw2");
			pwNewConfirme = req.getParameter("Pw3");

			//Test du mot de passe actuel
			if (client.getMdp().equals(pwActuel)) {
				if (pwNew.equals(pwNewConfirme)) {
					//Le mot de passe actuel est bon ET les deux mot de passe suisvant sont egaux
					client.setMdp(pwNew);
				} else {
					erreur = "Le mot de passe et la confimation ne correspondent pas !";
					req.setAttribute("erreur", erreur);
					try {
						this.getServletContext().getRequestDispatcher("/profil.jsp").forward(req, resp);
					} catch (ServletException e) {
						System.out.println("Erreur dans le forwarding dans ServletProfil");
					}
				}
			} else {
				erreur = "Le mot de passe actuel est incorrect !";
				req.setAttribute("erreur", erreur);
				try {
					this.getServletContext().getRequestDispatcher("/profil.jsp").forward(req, resp);
				} catch (ServletException e) {
					System.out.println("Erreur dans le forwarding dans ServletProfil");
				}
			}
		}

		//Si il y a eu une erreur dans le changement des mot de passe.
		//On n'enregistre rien et on ne redirige pas.
		if (erreur.equals("")) {
			client.setSlogan(slogan);
			client.setNom(nom);
			client.setPrenom(prenom);
			service.update(client);

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
