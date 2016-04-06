package social.network;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Personne {
	private @Index String nom;
	private @Index String prenom;
	private @Index String mail;
	private @Id Long id; //Unique
	private GregorianCalendar dateNaissance;
	private String ville;
	private List<Interet> interets;
	private List<Personne> amis;
	private String mdp;
	private String slogan;

	public Personne(){

	}

	public Personne(String nom, String prenom, String mail,int jour, int mois,int annee, String mdp, String description){
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.dateNaissance = new GregorianCalendar(annee,mois,jour);
		this.mdp = mdp;
		this.slogan = description;
		this.interets = new ArrayList<Interet>();
		this.amis = new ArrayList<Personne>();
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
	public GregorianCalendar getAge() {
		return dateNaissance;
	}
	public void setAge(GregorianCalendar age) {
		this.dateNaissance = age;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public List<Interet> getInterets() {
		return interets;
	}
	public void setInterets(List<Interet> interets) {
		this.interets = interets;
	}
	
	public List<Personne> getAmis() {
		return amis;
	}
	public void setAmis(List<Personne> amis) {
		this.amis = amis;
	}
	
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getSlogan(){
		return this.slogan;
	}

	public void setSlogan(String desciption){
		this.slogan = desciption;
	}
	

}
