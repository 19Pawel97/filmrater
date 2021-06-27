package filmrater.domain;

import filmrater.infrastructure.InMemoryFilmRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmServiceIntegrationTest {
    private FilmService filmService;
    private FilmRepository filmRepository;

    @BeforeEach
    void setUp() {
        filmRepository = new InMemoryFilmRepository();
        filmService = new FilmService(filmRepository);
    }

    @Test
    void shouldAddFilm() {
        // when
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);

        // then
        final List<Film> films = filmRepository.getAll();
        Film film = films.get(0);
        assertEquals(FilmSample.FilmSample(), film);
        assertEquals(FilmSample.FilmSample().getTitle(), film.getTitle());
        assertEquals(FilmSample.FilmSample().getReleaseYear(), film.getReleaseYear());
        assertEquals(1, films.size());
    }

    @Test
    void shouldAddFilm2() {
        // when
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);

        // then
        final Optional<Film> film = filmService.getFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);
        Assertions.assertTrue(film.isPresent());
        assertEquals(FilmSample.FilmSample(), film.get());
        assertEquals(FilmSample.FilmSample().getTitle(), film.get().getTitle());
        assertEquals(FilmSample.FilmSample().getReleaseYear(), film.get().getReleaseYear());
    }

    @Test
    void shouldGetFilmsByTitle() {
        // given
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, 1990);
        filmService.addFilm("Doctor No", 1962);

        // when
        List<Film> films = filmService.getFilmsByTitle(FilmSample.FILM_SAMPLE_TITLE);

        // then
        assertEquals(2, films.size());
        assertEquals(FilmSample.FILM_SAMPLE_TITLE, films.get(0).getTitle());
        assertEquals(FilmSample.FILM_SAMPLE_TITLE, films.get(1).getTitle());
    }

    @Test
    void shouldGetFilmsByReleaseYear() {
        // given
        final int releaseYear = 1980;
        filmService.addFilm("Rambo", 1980);
        filmService.addFilm("Moonraker", 1980);
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);

        // when
        List<Film> films = filmService.getFilmsByReleaseYear(releaseYear);

        // then
        List<Integer> releaseYearsOfFilms = films.stream()
                .map(f -> f.getReleaseYear())
                .collect(Collectors.toList());

        Film rambo = films.stream()
                .filter(f -> f.getTitle().equals("Rambo"))
                .findFirst()
                .orElse(null);

        Film moonraker = films.stream()
                .filter(f -> f.getTitle().equals("Moonraker"))
                .findFirst()
                .orElse(null);

        assertEquals("Rambo",rambo.getTitle());
        assertEquals("Moonraker",moonraker.getTitle());
        assertEquals(releaseYear, releaseYearsOfFilms.get(0));
        assertEquals(releaseYear, releaseYearsOfFilms.get(1));
        assertEquals(2, releaseYearsOfFilms.size());
    }
}
