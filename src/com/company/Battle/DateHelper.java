package com.company.Battle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {
    private final Calendar calendar;
    protected final Date startDate;

    public DateHelper() {
        this.calendar = new GregorianCalendar();
        this.calendar.add(Calendar.YEAR, -1500);
        this.startDate = calendar.getTime();
    }

    public String getFormattedStartDate() {
        DateFormat dateFormat = new SimpleDateFormat("d MMMM y HH:mm");
        return (dateFormat.format(this.startDate));
    }

    public void skipTime() {
        this.calendar.add(Calendar.MINUTE, 1);
    }

    public String getFormattedDiff() {
        String string = "";
        Date endDate = calendar.getTime();
        Duration duration = Duration.between(startDate.toInstant(), endDate.toInstant());
        int days = (int) duration.toDays();
        int hours = (int) duration.minusDays(days).toHours();
        int minutes = (int) duration.minusDays(days).minusHours(hours).toMinutes();
        if (days != 0) {
            if (days > 20 || days < 10) {
                switch (days % 10) {
                    case 1 -> string = string + days + " день ";
                    case 2, 3, 4 -> string = string + days + " дня ";
                    default -> string = string + days + " дней ";
                }
            } else string = string + days + " дней ";
        }
        if (hours != 0) {
            if (hours > 20 || hours < 10) {
                switch (hours % 10) {
                    case 1 -> string = string + hours + " час ";
                    case 2, 3, 4 -> string = string + hours + " часа ";
                    default -> string = string + hours + " часов ";
                }
            } else string = string + hours + " часов ";
        }
        if (minutes != 0) {
            if (minutes < 10 || minutes > 20) {
                switch (minutes % 10) {
                    case 1 -> string = string + minutes + " минуту.";
                    case 2, 3, 4 -> string = string + minutes + " минуты.";
                    default -> string = string + minutes + " минут.";
                }
            } else string = string + minutes + " минут.";
        }
        return string;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("d MMMM y HH:mm");
        return (dateFormat.format(calendar.getTime()));
    }
}
