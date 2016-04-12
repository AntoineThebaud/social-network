package social.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import social.network.services.ServiceInteret;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Publication {

	private @Id Long id;
	private @Index Long id_auteur;
	private @Index String contenu;
	private ArrayList<String> tagList;
	private int nblikes;
	private Date datepub;
	
	//type de la publication (servira à adapter le html généré en fonction du type ?)
	private enum TypePublication { SIMPLE, PARTAGE, REPONSE };
	private TypePublication type;
	
	/* à confirmer :
	 * private int nb_partage;
	 * private List<Publication> commentaires;
	 */
	
	public Publication() {}

	public Publication(Long id_auteur, String contenu) {
		this.id_auteur = id_auteur;
		this.contenu = contenu;
		this.tagList = parseTag(contenu);
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
	
	//récupère les tags dans la String s
	private ArrayList<String> parseTag(String s) {
		String[] words = s.split(" ");
		ArrayList<String> tags = new ArrayList<String>();
		//récupérer les tags (= les mots qui commencent pas "#")
		for(String word : words) {
			if(word.startsWith("#")) {
				tags.add(word);
			}
		}
		//création des tags dans la DB si ils n'existent pas
		for(String tag : tags) {
			ServiceInteret service = new ServiceInteret();
			if(!service.exists(tag)) {
				service.register(new Interet(tag));
			}
			System.out.println("tag="+tag);
		}
		return tags;
	}
}
