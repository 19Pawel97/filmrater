package filmrater.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FilmServiceTest {
    private FilmService filmService;
    private FilmRepository filmRepository;

    @BeforeEach
    void setUp() {
        filmRepository = Mockito.mock(FilmRepository.class);
        filmService = new FilmService(filmRepository);
    }

    @Test
    void shouldAddFilm() {
        final String title = "Doctor No";
        final int releaseYear = 1962;

        filmService.addFilm(title,releaseYear);

        Mockito.verify(filmRepository,Mockito.times(1)).saveOrThrowIfExists(new Film(title, releaseYear));
    }
}