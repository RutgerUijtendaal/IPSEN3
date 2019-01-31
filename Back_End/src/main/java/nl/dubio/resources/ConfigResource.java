package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import nl.dubio.models.Admin;
import nl.dubio.models.Config;
import nl.dubio.persistance.ConfigDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.service.ConfigService;
import nl.dubio.service.CrudService;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/admin/config")
public class ConfigResource {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Config> getAll(@Auth Admin admin) {
        ConfigDao dao = DaoRepository.getConfigDao();
        List<Config> configs = new ArrayList<>();

        configs.add(dao.getByName("MAIL_WEEK_DAY"));
        configs.add(dao.getByName("MAIL_DAY_TIME"));
        configs.add(dao.getByName("MAIL_REMINDER"));

        return configs;
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void update(@Auth Admin admin, @FormParam("weekday") String weekDay, @FormParam("daytime") String dayTime, @FormParam("reminder") String reminder) {
        ConfigService service = new ConfigService();

        try {
            int dayValue = Integer.parseInt(weekDay);
            int reminderValue = Integer.parseInt(reminder);

            service.updateWeekDay(dayValue);
            service.updateDayTime(dayTime);
            service.updateReminder(reminderValue);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
