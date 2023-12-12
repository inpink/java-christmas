package christmas.domain.entity;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;

import christmas.validation.DateValidator;
import java.time.LocalDate;

public class DateOfVisit {

    private LocalDate date;

    private DateOfVisit(int dateOfMonth) {
        DateValidator.validateExistInCalendar(
                THIS_YEAR.getValue(),
                THIS_MONTH.getValue(),
                dateOfMonth);

        this.date = LocalDate.of(THIS_YEAR.getValue(),
                THIS_MONTH.getValue(),
                dateOfMonth);
    }

    public static DateOfVisit create(int dateOfMonth) {
        return new DateOfVisit(dateOfMonth);
    }

    public LocalDate getDate() {
        return date;
    }
}
