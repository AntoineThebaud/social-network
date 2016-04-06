package social.network;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Interet {
	private @Id String nom;
	private List<Personne> interesses;

	public Interet(String nom){
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Personne> getInteresses() {
		return interesses;
	}
	public void setInteresses(List<Personne> interesses) {
		this.interesses = interesses;
	}
}
