package social.network.services;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Ref;

import social.network.Interet;
import social.network.Personne;
import social.network.dao.GestionInteret;
import social.network.dao.GestionPersonne;
import social.network.dao.OfyService;

public class ServicePersonne {
	private final GestionPersonne gestionPers = new GestionPersonne(); 
	private final GestionInteret gestionInteret = new GestionInteret();
	
	public void register(Personne personne) {
	    gestionPers.put(personne);
	}
	
	public void unregister(Personne personne) {
	    gestionPers.delete(personne);
	}
	
	public boolean exists(String mail){
		if(gestionPers.findByMail(mail)==null){
			return false;
		}
		else{
			return true;
		}
	}
	
	public Personne getPersonne(String mail){
		return gestionPers.findByMail(mail);
	}
	
	public void update(Personne personne){
		gestionPers.put(personne);
	}
	
	public void update(Interet interet){
		gestionInteret.put(interet);
	}
	
	
	public void creerInteret(Personne personne,String nom){
		Interet interet = new Interet(nom);
		interet.addInteresse(personne);
		gestionInteret.put(interet);
		personne.addInteret(interet);
		gestionPers.put(personne);
	}
	public List<Personne> researchPersonne(String nom){
		return gestionPers.search(nom);
	}
}
