package social.network;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Connexion extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Je suis dans la servlet Connexion");
		if (/*Requete dans bdd avec login + mdp ET si correct alors */true) {
			//Variable de session ...
			
			//Redirection vers son flux d'actualité
			//resp.sendRedirect("/index.jsp");
		}
		//Sinon afficher une erreur dans la page d'inscription / connexion.
	}
}
