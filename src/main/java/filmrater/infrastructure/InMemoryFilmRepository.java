package filmrater.infrastructure;

import filmrater.domain.DuplicatedFilmException;
import filmrater.domain.Film;
import filmrater.domain.FilmRepository;
import filmrater.domain.Rating;

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
            films.put(key, film);
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
        final String key = createKey(title, releaseYear);
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

    @Override
    public void update(Film film) {
        final boolean contains = films.values().contains(film);
        if (contains) {
            films.put(createKey(film), film);
        } else {
            throw new KeyNotFoundException(film.getTitle() + " not found!");
        }
    }

    @Override
    public void deleteAll() {
        films.clear();
    }

    @Override
    public double getRating(String title, int releaseYear) {
        Optional<Rating> first = films.values().stream()
                .filter(f -> f.getTitle().equals(title))
                .filter(f -> f.getReleaseYear() == releaseYear)
                .map(f -> f.getRating())
                .findFirst();
        if (first.isEmpty()) {
            System.out.println("No rating!");
            return 0;
        } else {
            double rating = first.get().getRating();
            return rating;
        }
    }

    @Override
    public List<Film> getFilmsRatedBetween(double min, double max) {
        List<Film> theFilms = films.values().stream()
                .filter(f -> f.getRating().getRating() >= min)
                .filter(f -> f.getRating().getRating() <= max)
                .collect(Collectors.toList());
        return theFilms;
    }

    private String createKey(Film film) {
        return film.getTitle() + film.getReleaseYear();
    }

    private String createKey(String title, int releaseYear) {
        return title + releaseYear;
    }
}
