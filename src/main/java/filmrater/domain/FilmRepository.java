package filmrater.domain;

import java.util.List;

public interface FilmRepository {
    void saveOrThrowIfExists(Film film);

    List<Film> getAll();

    Film findOneFilm(String title, int releaseYear);
}
