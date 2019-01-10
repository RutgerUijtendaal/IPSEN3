package nl.dubio.resources;

import nl.dubio.models.Child;
import nl.dubio.service.ChildService;

import javax.ws.rs.Path;

@Path("/child")
public class ChildResource extends GenericResource<Child> {

    protected ChildResource() {
        super(new ChildService());
    }
}
