package nl.dubio.service;

import java.util.regex.Pattern;

public class ConfigService {
    public void updateWeekDay(int weekDay) {
        if (weekDay > 0 && weekDay < 8)
            MailService.updateWeekDay(Integer.toString(weekDay));
    }

    public void updateDayTime(String dayTime) {
        if (Pattern.matches("([0-2])([0-9]):([0-5])([0-9])", dayTime))
            MailService.updateDayTime(dayTime);
    }

    public void updateReminder(int reminder) {
        if (reminder > 0 && reminder < 7)
            MailService.updateReminder(Integer.toString(reminder));
    }
}
