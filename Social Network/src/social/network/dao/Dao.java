package social.network.dao;

import java.util.ArrayList; 
import java.util.Collection; 
import java.util.HashMap; 
import java.util.List; 
import java.util.Map; 
 
import com.google.common.collect.Lists; 
import com.googlecode.objectify.Key; 
import com.googlecode.objectify.cmd.LoadType; 
 
import static social.network.dao.OfyService.ofy; 
 
public abstract class Dao<T> { 
    protected final Class<T> clazz; 
 
    protected Dao(final Class<T> clazz) { 
        this.clazz = clazz; 
    } 
 
    public void delete(long id) { 
        ofy().delete().type(clazz).id(id).now(); 
    } 
 
    public List<T> getAll() { 
        return query().list(); 
    } 
 
    public T put(T object) { 
        ofy().save().entity(object).now(); 
        return object; 
    } 
 
    public Collection<T> put(Iterable<T> entities) { 
        return ofy().save().entities(entities).now().values(); 
    } 
 
    public T get(Key<T> key) { 
        return ofy().load().key(key).now(); 
    } 
 
    public List<T> get(List<Key<T>> keys) { 
        return Lists.newArrayList(ofy().load().keys(keys).values()); 
    } 
 
    public T get(Long id) { 
        return query().id(id).now(); 
    } 
 
    public T get(String id) { 
        return query().id(id).now(); 
    } 
 
    public List<T> getSubset(Iterable<Long> ids) { 
        return new ArrayList<T>(query().ids(ids).values()); 
    } 
 
    public Map<Long, T> getSubsetMap(Iterable<Long> ids) { 
        return new HashMap<>(query().ids(ids)); 
    } 
 
    public void delete(T object) { 
        ofy().delete().entity(object); 
    } 
 
    public void delete(List<T> objects) { 
        ofy().delete().entities(objects); 
    } 
 
    public void delete(Object parent, 
                       Long id) { 
        ofy().delete().type(clazz).parent(parent).id(id).now(); 
    } 
 
    protected LoadType<T> query() { 
        return ofy().load().type(clazz); 
    } 
 
    public void deleteAll() { 
        List<Key<T>> keys = ofy().load().type(clazz).keys().list(); 
        ofy().delete().entities(keys).now(); 
    } 
}
