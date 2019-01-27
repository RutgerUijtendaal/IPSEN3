package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
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
    public StatisticModel test () {
        statisticsService.resetModel();
        statisticsService.filterByBorn(true);
        return statisticsService.getStatisticModel();
    }

}
