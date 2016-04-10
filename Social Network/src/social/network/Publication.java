package social.network;

import java.util.Date;

import com.googlecode.objectify.annotation.Id;

public class Publication {
	
	private @Id Long id;
	private String auteur;
	private String contenu;
	private int nblikes;
	private Date datepub;
	
	/* à confirmer 
	 * 
	 * private int nb_partage;
	 * private List<Publication> commentaires;
	 * 
	 * //type de la publication (servira à adapter le html généré en fonction du type ?)
	 * private enum TypePublication { SIMPLE, PARTAGE, REPONSE };
	 * private TypePublication type;
	 */

	public Publication(Long id, String auteur, String contenu) {
		this.id = id;
		this.auteur = auteur;
		this.contenu = contenu;
		this.nblikes = 0;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getAuteur() {
		return auteur;
	}
	
	public void incrLike() {
		nblikes++;
	}
	
//	//inutilisé :
//	public void setAuteur(Long auteur) {
//		this.auteur = auteur;
//	}
}
