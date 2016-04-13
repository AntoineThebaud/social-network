package social.network.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

import social.network.Interet;
import social.network.Personne;
import social.network.Publication;
import social.network.dao.GestionInteret;
import social.network.dao.GestionPersonne;
import social.network.dao.GestionPub;

public class ServicePersonne {
	private final GestionPersonne gestionPers = new GestionPersonne(); 
	private final GestionInteret gestionInteret = new GestionInteret();
	private final GestionPub gestionPub = new GestionPub();
	
	public void register(Personne personne) {
	    gestionPers.put(personne);
	}
	
	public void unregister(Personne personne) {
	    gestionPers.delete(personne);
	}
	
	public boolean existsPersonne(String mail){
		return gestionPers.exists(mail);
	}
	
	public boolean existsInteret(String nom){
		return gestionInteret.exists(nom);
	}
	
	public Personne getPersonne(String mail){
		return gestionPers.findByMail(mail);
	}
	
	public Personne getPersonne(Long id){
		return gestionPers.get(id);
	}
	
	public Interet getInteret(String mail){
		return gestionInteret.get(mail);
	}
	
	public void update(Personne personne){
		gestionPers.put(personne);
	}
	
	public void update(Interet interet){
		gestionInteret.put(interet);
	}
	
	/* une personne cree un interet */
	public void creerInteret(Personne personne,String nom){
		Interet interet = new Interet(nom);
		interet.addInteresse(personne);
		gestionInteret.put(interet);
		personne.addInteret(interet);
		gestionPers.put(personne);
	}
	
	public void creerPublication(Personne client, String input, String dest) {
		Publication pub;
		if(dest == "") {
			pub = new Publication(client.getId(), input);
		} else {
			pub = new Publication(client.getId(), input, Long.parseLong(dest));
		}
		gestionPub.put(pub);
	}
	
	public List<Personne> researchPersonne(String nom){
		return gestionPers.searchAll(nom);
	}
	
	public List<Personne> researchPersonneAmis(Personne personne,String nom){
		return gestionPers.searchAmis(personne,nom);
	}
	
	public List<Interet> researchInteret(String nom){
		return gestionInteret.search(nom);
	}
	
	public List<Interet> researchInteretAmis(Personne personne,String nom){
		List<Personne> amisInt = getAmisInterets(personne);
		List<Interet> interets = new ArrayList<Interet>();
		for(Personne ami : amisInt){
			List<Interet> list = gestionInteret.getSubset(ami.getRefInterets());
			interets.addAll(gestionInteret.search(list,nom));
		}
		return interets;
	}
	
	public List<Publication> researchPublicationAmis(Personne personne, String nom){
		List<Publication> amisPub = gestionPub.searchText(nom);
		List<Publication> pubs = new ArrayList<Publication>();
		List<Long> amis = personne.getRefAmis();
		for(Publication pub : amisPub){
			if(amis.contains(pub.getAuteur())){
				pubs.add(pub);
			}
		}
		return pubs;
	}
	
	public List<Publication> researchPublication(String nom){
		return gestionPub.searchText(nom);
	}
	
	public List<Personne> getAmis(Personne personne){
		return gestionPers.getSubset(personne.getRefAmis());
	}
	
	public List<Personne> getInteresses(Interet interet){
		return gestionPers.getSubset(interet.getRefInteresses());
	}
	
	public List<Interet> getInterets(Personne personne){
		return gestionInteret.getSubset(personne.getRefInterets());
	}
	
	public List<Personne> getAmisInterets(Personne personne){
		System.out.println("amisInterets");
		List<Personne> communs = new ArrayList<Personne>();
		List<Personne> amis = getAmis(personne);
		List<Interet> interets = getInterets(personne);
		for(Personne ami:amis){
			for(Interet interet:interets){
				if(!communs.contains(ami) && getInterets(ami).contains(interet)){ 
					System.out.println("ok");
					communs.add(ami);
				}
			}
		}
		return communs;
	}
	
	public List<Publication> researchMesPublications(Long id_user){
		return gestionPub.searchAll(id_user);
	}

	public List<Publication> researchMesFlux(Long id_user) {
		Personne user = getPersonne(id_user); 
		return gestionPub.searchFlux(user);
	}
	
	/* avoir 3 tendances au total */
	@SuppressWarnings("null")
	public List<Interet> getTendances() {
		List<Interet> tendances = gestionInteret.getAll();
		/*int val1 = 0;
		int val2 = 0;
		int val3 = 0;
		String tag1="";
		String tag2 = "";
		String tag3 = "";
		//Creation de la HashMap.
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//Initialisation de la map.
		/*for (int i = 0; i < tendances.size(); i++) {
			//Ajout de chaque interet avec une valeur de 0 associée.
			map.put(tendances.get(i).getNom(), 0);
		}*/
		
		//Parcours des publications
		/*List<Publication> publications = gestionPub.getAll();
		ArrayList<String> tagList = new ArrayList<String>();
		System.out.println("publication size = "+publications.size());
		for (int i = 0; i < publications.size(); i++) {
			tagList = publications.get(i).getTagList();
			//Parcours de la tagList.
			for (int j = 0; j < tagList.size(); j++) {
				//map.put(tagList.get(j), map.get(tagList.get(j)+1));
			}
		}
		System.out.println("gitan");
		for(Entry<String, Integer> entry : map.entrySet()) {
			String cle = entry.getKey();
			Integer valeur = entry.getValue();
			if (valeur > val1) {
				val1 = valeur;
				tag1 = cle;
			}else if (valeur > val2){
				val2 = valeur;
				tag2 = cle;
			}else if (valeur > val3){
				val3 = valeur;
				tag3 = cle;
			}
		}
		
		//On peut retourner le nombre (en plus du nom) des publications qui parle des tendances.
		List<String> retour = null;
		retour.add(tag1);
		retour.add(tag2);
		retour.add(tag3);
		//return retour;*/
		
		
		return tendances.subList(tendances.size()-3, tendances.size());
	}

	public List<Publication> researchMessagesRecus(Long id_user) {
		return gestionPub.searchMessagesRecus(id_user);
	}
}
