package filmrater.ui;

import filmrater.domain.Film;
import filmrater.domain.FilmService;

import java.util.List;
import java.util.Scanner;

public class GetFilmByReleaseYearCase extends CaseHandler{
    public GetFilmByReleaseYearCase(Scanner scanner, FilmService filmService) {
        super(4, "Find films released in...", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Enter the release year:");
        final int releaseYear = scanner.nextInt();
        List<Film> filmsByReleaseYear = filmService.getFilmsByReleaseYear(releaseYear);
        filmsByReleaseYear.forEach(film -> System.out.println(film.getTitle()));
    }
}
