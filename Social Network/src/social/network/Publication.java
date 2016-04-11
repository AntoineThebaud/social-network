package social.network;

import java.util.Date;

import com.googlecode.objectify.annotation.Id;

public class Publication {

	private @Id Long id;
	private String auteur;
	private String contenu;
	private int nblikes;
	private Date datepub;
	
	//type de la publication (servira à adapter le html généré en fonction du type ?)
	private enum TypePublication { SIMPLE, PARTAGE, REPONSE };
	private TypePublication type;
	
	/* à confirmer :
	 * private int nb_partage;
	 * private List<Publication> commentaires;
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

	public String getAuteur() {
		return auteur;
	}
	
	public Date getDatepub() {
		return datepub;
	}

	public int getNblikes() {
		return nblikes;
	}
	
	public void incrLike() {
		nblikes++;
	}

}
