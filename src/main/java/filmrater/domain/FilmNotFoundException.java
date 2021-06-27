package filmrater.domain;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(String s) {
        super(s);
    }
}
