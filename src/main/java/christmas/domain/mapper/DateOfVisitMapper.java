package christmas.domain.mapper;

import christmas.domain.entity.DateOfVisit;
import christmas.util.StringUtil;
import christmas.validation.IntegerValidator;

public class DateOfVisitMapper {

    public static DateOfVisit toDateOfVisit(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        IntegerValidator.validateInteger(input);
        int converted = Integer.parseInt(deleteSpaces);

        return DateOfVisit.create(converted);
    }
}
