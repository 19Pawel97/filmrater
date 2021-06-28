package filmrater.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class Rating {
    private final double rating;
    private final int counter;

    @Override
    public String toString() {
        return "Rating{" +
                "rating= " + String.format("%.2f", rating) +
                ", counter= " + counter +
                '}';
    }
}
