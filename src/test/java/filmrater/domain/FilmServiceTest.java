package filmrater.domain;

import filmrater.infrastructure.DuplicatedKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FilmServiceTest {
    private FilmService filmService;
    private FilmRepository filmRepository;
    private FilmRater filmRater;

    @BeforeEach
    void setUp() {
        filmRepository = Mockito.mock(FilmRepository.class);
        filmRater = Mockito.mock(FilmRater.class);
        filmService = new FilmService(filmRepository, filmRater);
    }

    @Test
    void shouldAddFilm() {
        // given
        final String title = "Doctor No";
        final int releaseYear = 1962;

        // when
        filmService.addFilm(title, releaseYear);

        // then
        Mockito.verify(filmRepository, Mockito.times(1)).saveOrThrowIfExists(new Film(title, releaseYear));
    }

    @Test
    void shouldThrowDuplicatedKeyExceptionIfTheFilmIsAlreadyInDB() {
        // given
        final String title = "Doctor No";
        final int releaseYear = 1962;

        // when
        Mockito.doThrow(DuplicatedKeyException.class).when(filmRepository).saveOrThrowIfExists(new Film(title, releaseYear));

        // then
        assertThrows(DuplicatedFilmException.class, () -> filmService.addFilm(title,releaseYear));
    }
}