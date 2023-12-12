package christmas.domain.entity.event;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
    NONE("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String description;
    private final int priceThreshold;

    Badge(String description, int priceThreshold) {
        this.description = description;
        this.priceThreshold = priceThreshold;
    }

    public static Badge findBadge(int price) {
        return Arrays.stream(Badge.values())
                .sorted(Comparator.comparingInt(Badge::getPriceThreshold).reversed())
                .filter(badge -> price >= badge.priceThreshold)
                .findFirst()
                .orElse(NONE); 
    }

    public String getDescription() {
        return description;
    }

    public int getPriceThreshold() {
        return priceThreshold;
    }
}
