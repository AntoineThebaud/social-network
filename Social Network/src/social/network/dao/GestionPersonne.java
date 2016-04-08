package social.network.dao;

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
    	List<Personne> personnes = query().filter("nom", nom).list();
        personnes.addAll(query().filter("prenom", nom).list());
    	/*
        List<Personne> personnes = query().filter("nom >=", nom).filter("nom <", nom + "\uFFFD").list();
        personnes.addAll(query().filter("nom >=", nom).filter("nom <", nom + "\uFFFD").list());
        */
        return personnes;
    }
}
