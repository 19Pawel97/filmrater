package filmrater.domain;

public class TooOldFilmException extends RuntimeException {
    public TooOldFilmException(String message) {
        super(message);
    }
}
