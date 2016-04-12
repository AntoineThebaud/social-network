package social.network;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.annotation.OnLoad;

@Entity
public class Interet {
	private @Id String nom;
	private @Index String interet;
	private List<Long> refInteresses = new ArrayList<Long>();
	// private @Ignore List<Long> refInteresses;

	public Interet() {}
	
	public Interet(String nom){
		this.nom = nom;
		this.interet = nom;
	}
	
	/*
	@OnLoad
	public void deRef() {
		if (refInteresses != null) {
			interesses = new ArrayList<Personne>();
			for (Ref<Personne> interesse : refInteresses) {
				if (interesse.isLoaded()) {
					interesses.add(interesse.get());
				}
			}
		}
	}
	*/

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
		this.interet = nom;
	}

	public List<Long> getRefInteresses() {
		return refInteresses;
	}
	
	public void setRefInteresses(List<Long> refInteresses) {
		this.refInteresses = refInteresses;
	}
	/*
	public List<Personne> getInteresses() {
		return interesses;
	}
	
	public void setInteresses(List<Personne> interesses) {
		this.interesses = interesses;
	}
	
	
	public Key<Interet> getKey() {
		return Key.create(Interet.class,nom);
	}
	*/
	
	public void addInteresse(Personne interesse){
		// ajout a la liste de personne ayant cet interet
		refInteresses.add(interesse.getId());
		/*
		Ref<Personne> ref = Ref.create(interesse.getKey());
		if (!refInteresses.contains(ref)) {
			refInteresses.add(ref);
		}
		*/
	}
}
