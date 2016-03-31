package social.network;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.*;

@Entity
public class Personne {
	private @Index String nom;
	private @Index String prenom;
	private @Index String mail;
	private @Id Long id; //Unique
	private int age;
	private String ville;
	private ArrayList<String>interet;
	private String mdp;
	
	public Personne(){
		
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
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
