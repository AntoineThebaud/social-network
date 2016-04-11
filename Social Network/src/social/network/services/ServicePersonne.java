package social.network.services;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

import social.network.Interet;
import social.network.Personne;
import social.network.Publication;
import social.network.dao.GestionInteret;
import social.network.dao.GestionPersonne;
import social.network.dao.GestionPub;
import social.network.dao.OfyService;

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
	

	public void creerPublication(Personne client, String input) {
		Publication pub = new Publication(client.getId(), input);
		gestionPub.put(pub);
	}
	
	public List<Personne> researchPersonne(String nom){
		return gestionPers.search(nom);
	}
	
	public List<Interet> researchInteret(String nom){
		return gestionInteret.search(nom);
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

}
