package filmrater.domain;

import filmrater.infrastructure.DuplicatedKeyException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;

    public void addFilm(String title, int releaseYear) {
        try {
            filmRepository.saveOrThrowIfExists(new Film(title,releaseYear));
        } catch (DuplicatedKeyException dke) {
            throw new DuplicatedFilmException("The film already in the db.", dke);
        }
    }

    public Film getFilm(String title, int releaseYear) {
        return filmRepository.findOneFilm(title, releaseYear);
    }
}
