package christmas.domain.entity;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.util.List;

public enum WEEK {
    WEEKDAY(List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY)),
    WEEKEND(List.of(FRIDAY, SATURDAY));

    private final List<DayOfWeek> days;

    WEEK(List<DayOfWeek> days) {
        this.days = days;
    }

    public List<DayOfWeek> getDays() {
        return days;
    }
}
