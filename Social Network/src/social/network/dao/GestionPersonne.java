package social.network.dao;

import java.util.ArrayList;
import java.util.List;

import social.network.Personne;
 
public class GestionPersonne extends Dao<Personne> {
    public GestionPersonne() {
        super(Personne.class); 
    } 
 
    public Personne findByMail(String mail) { 
        return query().filter("mail", mail).first().now(); 
    }
    
    public boolean exists(String mail) { 
        return (findByMail(mail) != null); 
    }
    
    public List<Personne> searchAll(String nom) {
    	List<Personne> all = super.getAll();
    	List<Personne> personnes = new ArrayList<Personne>();
    	for(Personne p : all){
    		if(p.getNom().toUpperCase().contains(nom.toUpperCase()) || p.getPrenom().toUpperCase().contains(nom.toUpperCase())){
    			personnes.add(p);
    		}
    	}
        return personnes;
    }
    
    public List<Personne> searchAmis(Personne personne, String nom) {
    	List<Personne> all = super.getSubset(personne.getRefAmis());
    	List<Personne> personnes = new ArrayList<Personne>();
    	for(Personne p : all){
    		if(p.getNom().toUpperCase().contains(nom.toUpperCase()) || p.getPrenom().toUpperCase().contains(nom.toUpperCase())){
    			personnes.add(p);
    		}
    	}
        return personnes;
    }
    
}
