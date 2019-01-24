package nl.dubio.resources;

import nl.dubio.models.Right;
import nl.dubio.service.RightService;

import javax.ws.rs.Path;

@Path("/right")
public class RightResource extends GenericResource<Right> {

    protected RightResource() {
        super(new RightService());
    }
}
