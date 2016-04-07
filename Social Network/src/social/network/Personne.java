package social.network;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Personne {
	private @Index String nom;
	private @Index String prenom;
	private @Index String mail;
	private @Id Long id; //Unique
	private String ville;
	private @Load List<Ref<Interet>> interets;
	private @Load List<Ref<Personne>> amis;
	private int jour;
	private int mois;
	private int annee;
	private String mdp;
	private String slogan;

	public Personne(){

	}

	public Personne(String nom, String prenom, String mail,int jour, int mois,int annee, String mdp, String description){
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		this.mdp = mdp;
		this.slogan = description;
		this.interets = new ArrayList<Ref<Interet>>();
		this.amis = new ArrayList<Ref<Personne>>();
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
	
	public int getJour(){
		return jour;
	}
	
	public void setJour(int jour){
		this.jour = jour;
	}
	
	public int getMois(){
		return mois;
	}
	
	public void setMois(int mois){
		this.mois = mois;
	}
	
	public int getAnnee(){
		return annee;
	}
	
	public void setAnnee(int annee){
		this.annee = annee;
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public List<Ref<Interet>> getInterets() {
		return interets;
	}
	public void setInterets(List<Ref<Interet>> interets) {
		this.interets = interets;
	}
	
	public List<Ref<Personne>> getAmis() {
		return amis;
	}
	public void setAmis(List<Ref<Personne>> amis) {
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
