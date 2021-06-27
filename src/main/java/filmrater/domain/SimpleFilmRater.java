package filmrater.domain;

public class SimpleFilmRater implements FilmRater {
    @Override
    public Rating calculateRating(Rating rating, int newRating) {
        if (rating == null) {
            return new Rating(newRating, 1);
        }

        double calculateRating = rating.getRating() + (newRating - rating.getRating()) / (rating.getCounter() * 1.5d);
        return new Rating(calculateRating, rating.getCounter() + 1);
    }
}
