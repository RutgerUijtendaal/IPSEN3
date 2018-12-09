package nl.dubio.resources;

import nl.dubio.models.Parent;
import nl.dubio.service.ParentService;

import javax.ws.rs.Path;

@Path("/parent")
public class ParentResource extends GenericResource<Parent> {

    protected ParentResource() {
        super(new ParentService());
    }

}
