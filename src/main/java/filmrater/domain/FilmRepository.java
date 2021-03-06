package filmrater.domain;

import java.util.List;
import java.util.Optional;

public interface FilmRepository {
    void saveOrThrowIfExists(Film film);

    List<Film> getAll();

    Optional<Film> findOneFilm(String title, int releaseYear);

    List<Film> findByTitle(String title);

    List<Film> getFilmsByReleaseYear(int releaseYear);

    void update(Film film);

    void deleteAll();

    double getRating(String title, int releaseYear);

    List<Film> getFilmsRatedBetween(double min, double max);
}
