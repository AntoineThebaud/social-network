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
    
    public List<Personne> search(String nom) {
    	/*
    	List<Personne> personnes = query().filter("nom", nom).list();
        personnes.addAll(query().filter("prenom", nom).list());
        */
    	List<Personne> all = super.getAll();
    	List<Personne> personnes = new ArrayList<Personne>();
    	for(Personne p : all){
    		if(p.getNom().toUpperCase().contains(nom.toUpperCase()) || p.getPrenom().toUpperCase().contains(nom.toUpperCase())){
    			personnes.add(p);
    		}
    	}
    	/*
        List<Personne> personnes = query().filter("nom >=", nom).filter("nom <", nom + "\uFFFD").list();
        personnes.addAll(query().filter("prenom >=", nom).filter("prenom <", nom + "\uFFFD").list());
        */
        return personnes;
    }
}
