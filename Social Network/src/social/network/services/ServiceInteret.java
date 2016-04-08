package social.network.services;

import social.network.Interet;
import social.network.Personne;
import social.network.dao.GestionInteret;
import social.network.dao.GestionPersonne;

public class ServiceInteret {
	private final GestionPersonne gestionPers = new GestionPersonne(); 
	private final GestionInteret gestionInteret = new GestionInteret();
	
	public void register(Interet interet) {
		gestionInteret.put(interet);
	}
	
	public void unregister(Interet interet) {
		gestionInteret.delete(interet);
	}
	
	public boolean exists(String interet){
		return gestionInteret.exists(interet);
	}
	
	public void update(Interet interet){
		gestionInteret.put(interet);
	}
}
