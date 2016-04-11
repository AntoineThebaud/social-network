package social.network;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Publication {

	private @Id Long id;
	private @Index Long id_auteur;
	private @Index String contenu;
	private int nblikes;
	private Date datepub;
	
	//type de la publication (servira à adapter le html généré en fonction du type ?)
	private enum TypePublication { SIMPLE, PARTAGE, REPONSE };
	private TypePublication type;
	
	/* à confirmer :
	 * private int nb_partage;
	 * private List<Publication> commentaires;
	 */

	public Publication(Long id_auteur, String contenu) {
		this.id_auteur = id_auteur;
		this.contenu = contenu;
		this.nblikes = 0;
	}

	public String getContenu() {
		return contenu;
	}

	public Long getAuteur() {
		return id_auteur;
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
