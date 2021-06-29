package filmrater.domain;

import filmrater.infrastructure.InMemoryFilmRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class FilmServiceIntegrationTest {
    private FilmService filmService;
    private FilmRepository filmRepository;
    private FilmRater filmRater;

    @BeforeEach
    void setUp() {
        filmRepository = new InMemoryFilmRepository();
        filmRater = new SimpleFilmRater();
        filmService = new FilmService(filmRepository, filmRater);
    }

    @AfterEach
    void tearDown() {
        filmRepository.deleteAll();
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
        assertTrue(film.isPresent());
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

        assertEquals("Rambo", rambo.getTitle());
        assertEquals("Moonraker", moonraker.getTitle());
        assertEquals(releaseYear, releaseYearsOfFilms.get(0));
        assertEquals(releaseYear, releaseYearsOfFilms.get(1));
        assertEquals(2, releaseYearsOfFilms.size());
    }

    @Test
    void shouldRateFilm() {
        // given
        final int rating = 7;
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);

        // when
        filmService.rateFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR, rating);


        // then
        final Optional<Film> film = filmService.getFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);
        assertTrue(film.isPresent());
        assertNotNull(film.get().getRating());
        assertEquals(Integer.valueOf(rating).doubleValue(), film.get().getRating().getRating());
        assertEquals(1, film.get().getRating().getCounter());
    }

    @Test
    void shouldThrowExceptionWhenUserTriesToFindFilmBefore1900() {
        // given
        final int releaseYear = 1899;

        // when and then
        assertThrows(TooOldFilmException.class, () -> filmService.getFilmsByReleaseYear(releaseYear));
    }

    @Test
    void shouldThrowExceptionWhenUserTriesToFindFilmReleasedInTheFuture() {
        // given
        final int releaseYear = LocalDate.now().getYear() + 1;

        // when and then
        assertThrows(FutureFilmException.class, () -> filmService.getFilmsByReleaseYear(releaseYear));
    }

    @Test
    void shouldGetRating() {
        // given
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);
        filmService.rateFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR, 8);

        // when
        double rating = filmService.getRating(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);

        // then
        assertEquals(8, rating);
    }

    @Test
    void shouldGetFilmsRatedBetween() {
        // given
        filmService.addFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR);
        filmService.rateFilm(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR, 8);
        filmService.addFilm("Rambo", 1999);
        filmService.rateFilm("Rambo", 1999, 7);

        // when
        List<Film> filmsRatedBetween = filmService.getFilmsRatedBetween(8, 9);

        // then
        assertTrue(!(filmsRatedBetween.isEmpty()));
        assertEquals(1, filmsRatedBetween.size());
        assertEquals(new Film(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR).getTitle(), filmsRatedBetween.get(0).getTitle());
        assertEquals(new Film(FilmSample.FILM_SAMPLE_TITLE, FilmSample.SAMPLE_RELEASE_YEAR).getReleaseYear(), filmsRatedBetween.get(0).getReleaseYear());
    }
}
