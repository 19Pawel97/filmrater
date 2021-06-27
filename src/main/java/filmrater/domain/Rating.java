package filmrater.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Rating {
    private final double rating;
    private final int counter;
}
