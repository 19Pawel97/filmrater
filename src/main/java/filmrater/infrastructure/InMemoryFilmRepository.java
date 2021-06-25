package filmrater.infrastructure;

import filmrater.domain.Film;
import filmrater.domain.FilmRepository;

public class InMemoryFilmRepository implements FilmRepository {
    @Override
    public void saveOrThrowIfExists(Film film) {
        throw new RuntimeException("Not implemented yet!");
    }
}
