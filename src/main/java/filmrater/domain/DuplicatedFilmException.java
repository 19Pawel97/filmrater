package filmrater.domain;

public class DuplicatedFilmException extends RuntimeException {

    public DuplicatedFilmException(String message, Throwable cause) {
        super(message, cause);
    }
}

