package api;

import database.models.DatabaseObject;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public interface CrudService<T extends DatabaseObject<T>> {

     List<T> getAll();

     T getById(Integer id);

     Integer save(T object);

     boolean updateObject(T object);
        
     String updateValue(UriInfo ui);

     boolean delete(T object);

    boolean deleteById(Integer id);

}
