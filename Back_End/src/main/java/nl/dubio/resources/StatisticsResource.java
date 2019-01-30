package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import nl.dubio.View;
import nl.dubio.models.*;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.persistance.DilemmaDao;
import nl.dubio.service.StatisticsService;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/statistics")
public class StatisticsResource {

    private final CoupleDao coupleDao;
    private final DilemmaDao dilemmaDao;
    private StatisticsService statisticsService;

    public StatisticsResource() {
        this.coupleDao = DaoRepository.getCoupleDao();
        this.dilemmaDao = DaoRepository.getDilemmaDao();
    }

    @Path("/")
    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public StatisticModel test () {
        statisticsService.resetModel();
        return statisticsService.getStatisticModel();
    }

    @POST
    @Timed
    @Produces
    @JsonView(View.Public.class)
    @Path("/")
    public StatisticModel getModel(@Auth Admin admin, @Valid StatisticRequestModel statisticRequestModel) {
        this.statisticsService = new StatisticsService();
        statisticsService.resetModel();
        List<Couple> couples = new ArrayList<>();
        if (statisticRequestModel.getCouples().length > 0) {
            for (Integer integer : statisticRequestModel.getCouples()) {
                couples.add(coupleDao.getById(integer));
            }
            statisticsService.filterByCouple(couples);
        }
        List<Dilemma> dilemmas = new ArrayList<>();
        if (statisticRequestModel.getDilemmas().length > 0) {
            for (Integer integer: statisticRequestModel.getDilemmas()) {
                dilemmas.add(dilemmaDao.getById(integer));
            }
            statisticsService.filterByDilemma(dilemmas);
        }
        return statisticsService.getStatisticModel();
    }

}
