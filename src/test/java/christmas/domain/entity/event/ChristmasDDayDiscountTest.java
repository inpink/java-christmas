package christmas.domain.entity.event;

import christmas.domain.entity.DateOfVisit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChristmasDDayDiscountTest {

    @ParameterizedTest
    @CsvSource({
            "01, 1000",
            "02, 1100",
            "10, 1900",
            "24, 3300",
            "25, 3400",
            "26, 0" // 크리스마스 이후는 할인 적용되지 않음
    })
    void 크리스마스_DDay_할인_계산_테스트(final int visitDate, final int expectedDiscount) {
        // Given
        final DateOfVisit date = DateOfVisit.create(visitDate);

        // When
        final int discount = ChristmasDDayDiscount.calculateDiscount(date);

        // Then
        assertEquals(expectedDiscount, discount);
    }
}
