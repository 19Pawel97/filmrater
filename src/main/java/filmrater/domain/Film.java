package filmrater.domain;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Film {
    private final String title;
    private final int releaseYear;
    private Rating rating;

    public Film(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }
}
