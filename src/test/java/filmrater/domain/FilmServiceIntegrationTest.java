package filmrater.domain;

import filmrater.infrastructure.InMemoryFilmRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
}
