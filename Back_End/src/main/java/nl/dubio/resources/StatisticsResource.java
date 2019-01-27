package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import nl.dubio.View;
import nl.dubio.models.StatisticModel;
import nl.dubio.service.StatisticsService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/statistics")
public class StatisticsResource {

    StatisticsService statisticsService;

    public StatisticsResource() {
        this.statisticsService = new StatisticsService();
    }

    @Path("/")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public StatisticModel test () {
        statisticsService.resetModel();
        statisticsService.filterByBorn(true);
        return statisticsService.getStatisticModel();
    }

}
