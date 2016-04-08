package social.network.dao;

import java.util.List;

import com.googlecode.objectify.ObjectifyService;

import social.network.Interet; 
import social.network.Personne;

public class GestionInteret extends Dao<Interet> { 
    public GestionInteret() {
        super(Interet.class); 
    }
    
    public List<Interet> search(String nom) {
        //List<Interet> interets = query().filter("interet >=", nom).filter("interet <", nom + "\uFFFD").list();
    	List<Interet> interets = query().filter("interet", nom).filter("interet", nom).list();
    	return interets;
    }
    
    public boolean exists(String nom){
    	return (super.get(nom) != null);
    }
}
