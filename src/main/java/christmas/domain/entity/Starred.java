package christmas.domain.entity;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public enum Starred {
    DATES_OF_MONTH(List.of(3, 10, 17, 24, 31, 25));

    private final List<LocalDate> starredDate;

    Starred(List<Integer> datesOfMonth) {
        this.starredDate = datesOfMonth.stream()
                .map(dateOfMonth -> LocalDate.of(
                        THIS_YEAR.getValue(),
                        THIS_MONTH.getValue(),
                        dateOfMonth))
                .collect(Collectors.toList());
    }

    public static List<LocalDate> getStarredDate() {
        return DATES_OF_MONTH.starredDate;
    }
}
