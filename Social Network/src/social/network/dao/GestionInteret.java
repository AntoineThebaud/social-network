package social.network.dao;

import com.googlecode.objectify.ObjectifyService;

import social.network.Interet; 

public class GestionInteret extends Dao<Interet> { 
    public GestionInteret() {
        super(Interet.class); 
    }
    
    public boolean exists(String interet) {
    	//return ObjectifyService.ofy().load().key(Key<Interet> interet).now().count()==0;
    		
    	
    	//		ObjectifyService.
    			
    			//.filterKey(thingKey).count() != 0;
    	//return query().filterKey("nom", interet).first().now(); 
    	return false;
    } 
}
