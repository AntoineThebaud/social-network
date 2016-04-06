package social.network.services;

import com.googlecode.objectify.Objectify;

import social.network.Personne;
import social.network.dao.GestionPersonne;
import social.network.dao.OfyService;

public class ServicePersonne {
	private final GestionPersonne gestion = new GestionPersonne(); 
	
	public void register(Personne personne) {
	    gestion.put(personne);
	}
	
	public void unregister(Personne personne) {
	    gestion.delete(personne);
	}
	
	public boolean exists(String mail){
		if(gestion.findByMail(mail)==null){
			return false;
		}
		else{
			return true;
		}
	}
	
	public Personne getPersonne(String mail){
		return gestion.findByMail(mail);
	}
	
	public void update(Personne personne){
		gestion.put(personne);
	}
}
