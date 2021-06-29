package filmrater.domain;

import filmrater.infrastructure.DuplicatedKeyException;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final FilmRater filmRater;

    public void addFilm(String title, int releaseYear) {
        try {
            filmRepository.saveOrThrowIfExists(new Film(title, releaseYear));
        } catch (DuplicatedKeyException dke) {
            throw new DuplicatedFilmException("The film already in the db.", dke);
        }
    }

    public Optional<Film> getFilm(String title, int releaseYear) {
        return filmRepository.findOneFilm(title, releaseYear);
    }

    public List<Film> getFilmsByTitle(String title) {
        return filmRepository.findByTitle(title);
    }

    public List<Film> getFilmsByReleaseYear(int releaseYear) {
        if (releaseYear < 1900) {
            throw new TooOldFilmException("The film is too old!");
        } else if (releaseYear > LocalDate.now().getYear()) {
            throw new FutureFilmException("The film cannot be released in the future!");
        }
        return filmRepository.getFilmsByReleaseYear(releaseYear);
    }

    public void rateFilm(String title, int releaseYear, int rating) {
        final Film film = filmRepository.findOneFilm(title, releaseYear)
                .orElseThrow(() -> new FilmNotFoundException(title + " not in DB!"));

        Rating newRating = filmRater.calculateRating(film.getRating(), rating);
        film.setRating(newRating);
        filmRepository.update(film);
    }

    public double getRating(String title, int releaseYear) {
        if (releaseYear < 1900) {
            throw new TooOldFilmException("The film is too old!");
        } else if (releaseYear > LocalDate.now().getYear()) {
            throw new FutureFilmException("The film cannot be released in the future!");
        }
         return filmRepository.getRating(title, releaseYear);
    }
}
