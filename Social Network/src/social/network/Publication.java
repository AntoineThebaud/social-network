package social.network;

public class Publication {

	private Long auteur;
	private String contenu;
	private int nblikes;
	
	/* à confirmer 
	 * 
	 * private int nb_partage;
	 * private List<Publication> commentaires;
	 * 
	 * //type de la publication (servira à adapter le html généré en fonction du type ?)
	 * private enum TypePublication { SIMPLE, PARTAGE, REPONSE };
	 * private TypePublication type;
	 */

	public Publication() {
		
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Long getAuteur() {
		return auteur;
	}
	
//	//inutilisé :
//	public void setAuteur(Long auteur) {
//		this.auteur = auteur;
//	}
}
