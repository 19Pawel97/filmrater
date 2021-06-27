package filmrater.domain;

public interface FilmRater {
    Rating calculateRating(Rating rating, int newRating);
}
