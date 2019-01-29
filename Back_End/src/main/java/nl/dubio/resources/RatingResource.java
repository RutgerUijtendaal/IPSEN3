package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.IntParam;
import nl.dubio.auth.Authorizable;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.models.Rating;
import nl.dubio.service.RatingService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.List;

@Path("/rating")
public class RatingResource extends GenericResource<Rating> {

    protected RatingResource() {
        super(new RatingService());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Integer saveRating(
            @QueryParam("dilemmaId") IntParam dilemmaId,
            @QueryParam("rating") IntParam ratingValue
    ) throws InvalidInputException {
        Rating rating = new Rating(
                dilemmaId.get(),
                ratingValue.get(),
                new Timestamp(System.currentTimeMillis())
        );

        return ((RatingService) crudService).save(rating);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rating> getAll(@Auth Admin admin) {
        return ((RatingService) crudService).getAll();
    }

}
