package filmrater.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;

    public void addFilm(String title, int releaseYear) {
        try {
            filmRepository.saveOrThrowIfExists(new Film(title,releaseYear));
        } catch (DuplicatedFilmException dfe) {
            throw new DuplicatedFilmException("The film already in the db.", dfe);
        }
    }
}
