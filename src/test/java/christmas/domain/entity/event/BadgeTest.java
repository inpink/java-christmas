package christmas.domain.entity.event;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgeTest {

    @ParameterizedTest
    @CsvSource({
            "0, 없음",
            "4999, 없음",
            "5000, 별",
            "9999, 별",
            "10000, 트리",
            "19999, 트리",
            "20000, 산타",
            "30000, 산타"
    })
    void 뱃지_찾기_테스트(final int price, final String expectedBadgeDescription) {
        // When
        final Badge result = Badge.findBadge(price);

        // Then
        assertEquals(expectedBadgeDescription, result.getDescription());
    }
}
