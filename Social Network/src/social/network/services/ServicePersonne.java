package social.network.services;

import java.util.List;

import com.googlecode.objectify.Objectify;

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
	
	public void addInteret(Personne personne, Interet interet){
		// ajout a la liste d'interets de la personne
		List<Interet> interets = personne.getInterets();
		interets.add(interet);
		personne.setInterets(interets);
		update(personne);
		
		// ajout a la liste de personne ayant cet interet
		List<Personne> interesses = interet.getInteresses();
		interesses.add(personne);
		interet.setInteresses(interesses);
		update(interet);
	}
	
	public void addAmi(Personne personne, Personne ami){
		// ajout a la liste d'amis de la personne
		List<Personne> amis = personne.getAmis();
		amis.add(ami);
		personne.setAmis(amis);
		update(personne);
	}
	
}
