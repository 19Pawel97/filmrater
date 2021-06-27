package filmrater.ui;

import filmrater.domain.Film;
import filmrater.domain.FilmService;

import java.util.List;
import java.util.Scanner;

public class GetFilmsByTitleCase extends CaseHandler {
    public GetFilmsByTitleCase(Scanner scanner, FilmService filmService) {
        super(1, "Find films titled...", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Enter the title of the film:");
        final String title = scanner.next();
        final List<Film> filmsByTitle = filmService.getFilmsByTitle(title);
        if (filmsByTitle.size() > 0) {
            System.out.println("Found films:");
            for (Film film : filmsByTitle) {
                System.out.println(film.getTitle() + " " + film.getReleaseYear() + " " + film.getRating());
            }
        } else {
            System.out.println("No films found!");
        }
    }
}
