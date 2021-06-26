package filmrater.domain;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    void saveOrThrowIfExists(Film film);

    List<Film> getAll();

    Optional<Film> findOneFilm(String title, int releaseYear);
}
