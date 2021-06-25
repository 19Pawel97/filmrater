package filmrater.domain;

public interface FilmRepository {
    void saveOrThrowIfExists(Film film);
}
