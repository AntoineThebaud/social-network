package social.network.dao;

import java.util.ArrayList;
import java.util.List;

import social.network.Personne;
import social.network.Publication;

public class GestionPub extends Dao<Publication> {
    public GestionPub() {
        super(Publication.class); 
    }

	public List<Publication> searchAll(Long id_user) {
		//récupère toutes les publications :
		List<Publication> all = super.getAll();
		//valeur de retour :
    	List<Publication> publications = new ArrayList<Publication>();
    	
    	//pour chaque publication : vérifie si l'auteur est l'utilisateur
    	for(Publication p : all){
    		if(p.getAuteur().equals(id_user)) {
    			publications.add(p);
    		}
    	}
    	
        return publications;
	}
	
	public List<Publication> searchText(String nom) {
		List<Publication> all = super.getAll();
    	List<Publication> publications = new ArrayList<Publication>();
    	for(Publication p : all){
    		if(p.getContenu().contains(nom)) {
    			publications.add(p);
    		}
    	}
        return publications;
	}

	public List<Publication> searchFlux(Personne user) {
		//récupère toutes les publications :
		List<Publication> all = super.getAll();
		//récupère les intérêts de la personne :
		List<String> interets = user.getRefInterets();
		//valeur de retour :
    	List<Publication> publications = new ArrayList<Publication>();
    	
    	//pour chaque publication : vérifie si elle contient un interet de l'utilisateur
    	for(Publication p : all) {
    		for(String interet : interets) {
        		if(p.getContenu().contains(interet)) {
        			publications.add(p);
        		}
    		}
    	}
        return publications;
	}
}
