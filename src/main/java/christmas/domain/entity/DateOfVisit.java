package christmas.domain.entity;

import java.time.LocalDate;

public class DateOfVisit {

    private LocalDate date;

    private DateOfVisit(int dateOfMonth) {

    }

    public static DateOfVisit create(int dateOfMonth) {

        return new DateOfVisit(dateOfMonth);
    }


}
