package social.network;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Incription extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private char[] char_table;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("On viens de passer dans la servlet Inscription !");
		Personne nouvelUser = new Personne();
		nouvelUser.setAge(Integer.parseInt(req.getParameter("age")));
		nouvelUser.setMail(req.getParameter("mail"));
		nouvelUser.setMdp(req.getParameter("mdp"));
		nouvelUser.setNom(req.getParameter("nom"));
		nouvelUser.setPrenom(req.getParameter("prenom"));
		nouvelUser.setVille(req.getParameter("ville"));
		
		//Verification du double mot de passe
		if(req.getParameter("mdp2") != nouvelUser.getMdp()){
			//Erreur
		}
		
		//Verification du mail dans la base de données.
		if(/*si un select dans la bdd sur mail renvoi un resultat*/true){
			//Erreur le client existe deja.
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
		char_table = nouvelUser.getVille().toCharArray();
		char_table[0]=Character.toUpperCase(char_table[0]);
		nouvelUser.setVille(new String(char_table));
		
		
		//Si tout est OK, inserer le nouvel utilisateur et rediriger.
		//Sinon renvoyer une erreur.
	}
}
