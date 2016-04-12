package social.network;

import java.util.ArrayList;
import java.util.List;

import social.network.services.ServicePersonne;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.OnLoad;

@Entity
public class Personne {
	private @Index String nom;
	private @Index String prenom;
	private @Index String mail;
	private @Id Long id; //Unique
	private String ville;
	private List<String> refInterets = new ArrayList<String>();
	private List<Long> refAmis = new ArrayList<Long>();
	/*
	private @Ignore List<Interet> interets;
	private @Ignore List<Personne> amis;
	*/
	private int jour;
	private int mois;
	private int annee;
	private String mdp;
	private String slogan;

	public Personne() {}

	public Personne(String nom, String prenom, String mail,int jour, int mois,int annee, String mdp, String description){
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		this.mdp = mdp;
		this.slogan = description;
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
	public List<String> getRefInterets() {
		return refInterets;
	}
	public void setRefInterets(List<String> refInterets) {
		this.refInterets = refInterets;
	}
	
	public List<Long> getRefAmis() {
		return refAmis;
	}
	public void setRefAmis(List<Long> refAmis) {
		this.refAmis = refAmis;
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
	
	public void addInteret(Interet interet){
		// ajout a la liste d'interets de la personne
		if(!refInterets.contains(interet.getNom())){
			refInterets.add(interet.getNom());
		}
	}
	
	public void removeInteret(Interet interet){
		if(refInterets.contains(interet.getNom())){
			refInterets.remove(interet.getNom()); 
		}
	}
	
	public boolean estAmi(Personne personne){
		return refAmis.contains(personne.getId());
	}
	
	public void addAmi(Personne ami){
		// ajout a la liste d'amis de la personne
		if(!refAmis.contains(ami.getId())){
			refAmis.add(ami.getId());
		}
	}
	
	public void removeAmi(Personne ami){
		if(refAmis.contains(ami.getId())){
			refAmis.remove(ami.getId());
		}
	}
}
