package social.network.dao;

import social.network.Personne; 
 
public class GestionPersonne extends Dao<Personne> {
    public GestionPersonne() {
        super(Personne.class); 
    } 
 
    public Personne findByMail(String mail) { 
        return query().filter("mail", mail).first().now(); 
    } 
}
