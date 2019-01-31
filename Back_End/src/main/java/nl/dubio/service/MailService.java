package nl.dubio.service;

import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Config;
import nl.dubio.models.Couple;
import nl.dubio.persistance.ConfigDao;
import nl.dubio.persistance.DaoRepository;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;

import java.util.List;

public class MailService {
    private static ConfigDao configDao;

    private static Config weekDay;
    private static Config dayTime;
    private static Config reminder;
    private static Config sent;
    private static Config reminderSent;

    private static final int INTERVAL = 60000; // 1 Minute interval

    public MailService() {
        MailService.configDao = DaoRepository.getConfigDao();

        MailService.weekDay = configDao.getByName("MAIL_WEEK_DAY");
        MailService.dayTime = configDao.getByName("MAIL_DAY_TIME");
        MailService.reminder = configDao.getByName("MAIL_REMINDER");

        MailService.sent = configDao.getByName("MAIL_SENT");
        MailService.reminderSent = configDao.getByName("MAIL_REMINDER_SENT");

        this.startMailThread();
    }

    public static void updateWeekDay(String config) {
        MailService.weekDay.setValue(config);
        MailService.configDao.update(MailService.weekDay);
    }

    public static void updateDayTime(String config) {
        MailService.dayTime.setValue(config);
        MailService.configDao.update(MailService.dayTime);
    }

    public static void updateReminder(String config) {
        MailService.reminder.setValue(config);;
        MailService.configDao.update(MailService.reminder);
    }

    private void startMailThread() {
        Runnable mailThread = () -> {
            while (true) {
                try {
                    int reminder = Integer.parseInt(MailService.reminder.getValue());

                    DateTime current = new DateTime(System.currentTimeMillis());

                    DateTime trigger = DateTime.parse(
                            LocalDate.now().toDateTime(LocalTime.parse(MailService.dayTime.getValue())).toString()
                    );

                    DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

                    DateTime previous = null;

                    try {
                        previous = new DateTime(
                                format.parseDateTime(MailService.sent.getValue())
                        );
                    } catch (IllegalArgumentException exception) {
                    }

                    if ((previous == null || current.getWeekOfWeekyear() != previous.getWeekOfWeekyear()) && current.dayOfWeek().get() == Integer.parseInt(MailService.weekDay.getValue()) && current.isAfter(trigger)) {
                        MailService.sent.setValue(new Timestamp(System.currentTimeMillis()).toString());
                        MailService.reminderSent.setValue(Boolean.toString(false));

                        MailService.configDao.update(MailService.sent);
                        MailService.configDao.update(MailService.reminderSent);

                        MailService.sendDilemma();
                    } else if (current.dayOfWeek().get() == previous.plusDays(reminder).dayOfWeek().get() && current.isAfter(trigger) && !Boolean.parseBoolean(MailService.reminderSent.getValue())) {
                        MailService.sendDilemmaReminder();

                        MailService.reminderSent.setValue(Boolean.toString(true));
                        MailService.configDao.update(MailService.reminderSent);
                    }

                    Thread.sleep(INTERVAL);
                } catch (InterruptedException exception) {

                }
            }
        };
        new Thread(mailThread).start();
    }

    private static void sendDilemma() {
        CoupleService coupleService = new CoupleService();
        List<Couple> couples = coupleService.getAll();

        for (Couple couple : couples) {
            try {
                coupleService.createResultEntry(couple);
            } catch (InvalidInputException exception) {

            }
        }
    }

    private static void sendDilemmaReminder() {
        CoupleService coupleService = new CoupleService();
        List<Couple> couples = coupleService.getAll();

        for (Couple couple : couples) {
            try {
                coupleService.remindResultEntry(couple);
            } catch (InvalidInputException exception) {

            }
        }
    }
}
