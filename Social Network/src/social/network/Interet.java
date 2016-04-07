package social.network;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Load;

@Entity
public class Interet {
	private @Id String nom;
	private @Load List<Ref<Personne>> interesses;

	public Interet(String nom){
		this.nom = nom;
		this.interesses = new ArrayList<Ref<Personne>>();
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Ref<Personne>> getInteresses() {
		return interesses;
	}
	public void setInteresses(List<Ref<Personne>> interesses) {
		this.interesses = interesses;
	}
}
