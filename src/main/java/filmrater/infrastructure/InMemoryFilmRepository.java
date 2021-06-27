package filmrater.infrastructure;

import filmrater.domain.DuplicatedFilmException;
import filmrater.domain.Film;
import filmrater.domain.FilmRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Film> getAll() {
        return films.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Film> findOneFilm(String title, int releaseYear) {
        final String key = createKey(title,releaseYear);
        return Optional.ofNullable(films.get(key));
    }

    @Override
    public List<Film> findByTitle(String title) {
        return films.values().stream()
                .filter(film -> film.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Film> getFilmsByReleaseYear(int releaseYear) {
        return films.values().stream()
                .filter(film -> film.getReleaseYear() == releaseYear)
                .collect(Collectors.toList());
    }

    private String createKey(Film film) {
        return film.getTitle() + film.getReleaseYear();
    }

    private String createKey(String title, int releaseYear) {
        return title + releaseYear;
    }
}
