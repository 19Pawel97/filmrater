package filmrater.domain;

public class FilmSample {
    public static final String FILM_SAMPLE_TITLE = "Snatch";
    public static final int SAMPLE_RELEASE_YEAR = 2000;

    static Film FilmSample() {
        return new Film(FILM_SAMPLE_TITLE, SAMPLE_RELEASE_YEAR);
    }
}
