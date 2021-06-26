package filmrater.ui;

import filmrater.domain.FilmService;

import java.util.Scanner;

public class GetFilmCase extends CaseHandler {
    public GetFilmCase(Scanner scanner, FilmService filmService) {
        super(2, "Find a film.", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Enter the title of the film:");
        final String title = scanner.next();
        System.out.println("Enter the release year:");
        final int releaseYear = scanner.nextInt();
        filmService.getFilm(title,releaseYear)
                .ifPresentOrElse(
                        film -> System.out.println(title + " was found."),
                        () -> System.out.println(title + " not found")
                );
    }
}
