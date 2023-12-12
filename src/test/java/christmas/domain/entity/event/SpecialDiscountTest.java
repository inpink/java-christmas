package christmas.domain.entity.event;

import christmas.domain.entity.DateOfVisit;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialDiscountTest {

    @ParameterizedTest
    @CsvSource({
            "03, 1000", // 별 표시된 날짜, 할인 적용
            "04, 0",    // 별 표시되지 않은 날짜, 할인 없음
            "25, 1000"  // 별 표시된 날짜, 할인 적용
    })
    void 특별_할인_계산_테스트(final int visitDate, final int expectedDiscount) {
        // Given
        final DateOfVisit date = DateOfVisit.create(visitDate);

        // When
        final int discount = SpecialDiscount.calculateDiscount(date);

        // Then
        assertEquals(expectedDiscount, discount);
    }
}
