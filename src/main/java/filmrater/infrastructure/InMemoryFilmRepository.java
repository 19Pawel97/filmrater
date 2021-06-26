package filmrater.infrastructure;

import filmrater.domain.DuplicatedFilmException;
import filmrater.domain.Film;
import filmrater.domain.FilmRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryFilmRepository implements FilmRepository {
    private static final Map<String, Film> films = new HashMap<>();

    @Override
    public void saveOrThrowIfExists(Film film) {
        final String key = createKey(film);

        final Film theFilm = films.get(key);

        if (theFilm == null) {
            films.put(key,film);
        } else {
            throw new DuplicatedFilmException(film.getTitle() + " already in DB.");
        }
    }

    private String createKey(Film film) {
        return film.getTitle() + film.getReleaseYear();
    }
}
