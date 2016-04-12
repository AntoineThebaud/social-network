package social.network.dao;

import java.util.ArrayList;
import java.util.List;

import social.network.Publication;

public class GestionPub extends Dao<Publication> {
    public GestionPub() {
        super(Publication.class); 
    }

	public List<Publication> searchAll(Long id_user) {
		List<Publication> all = super.getAll();
    	List<Publication> publications = new ArrayList<Publication>();
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
}
