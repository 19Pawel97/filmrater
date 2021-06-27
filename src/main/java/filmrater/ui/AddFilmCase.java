package filmrater.ui;

import filmrater.domain.FilmService;

import java.util.Scanner;

public class AddFilmCase extends CaseHandler {
    public AddFilmCase(Scanner scanner, FilmService filmService) {
        super(3, "Add a film.", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Enter the title of the film:");
        final String title = scanner.next();
        System.out.println("Enter the release year:");
        final int releaseYear = scanner.nextInt();
        try {
            filmService.addFilm(title, releaseYear);
            System.out.println(title + " from " + releaseYear + " added successfully");
        } catch (Exception e) {
            System.out.println("Ups... Something went wrong. " + e.getMessage());
        }
    }
}
