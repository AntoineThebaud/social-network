package social.network;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Personne {
	private @Index String nom;
	private @Index String prenom;
	private @Index String mail;
	private @Id Long id; //Unique
	private int dateNaissance;
	private String ville;
	private ArrayList<String>interet;
	private String mdp;
	
	public Personne(){
		
	}
	
	public Personne(String nom, String prenom, String mail,int dateNaissance,String ville,ArrayList<String>interet, String mdp){
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.dateNaissance = dateNaissance;
		this.ville = ville;
		this.interet = interet;
		this.mdp = mdp;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAge() {
		return dateNaissance;
	}
	public void setAge(int age) {
		this.dateNaissance = age;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public ArrayList<String> getInteret() {
		return interet;
	}
	public void setInteret(ArrayList<String> interet) {
		this.interet = interet;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
