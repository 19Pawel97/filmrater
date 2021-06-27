package filmrater.ui;

import filmrater.domain.FilmService;

import java.util.Scanner;

public class RateCase extends CaseHandler {

    public RateCase(Scanner scanner, FilmService filmService) {
        super(5, "Rate a film.", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Enter the title of the film:");
        String title = scanner.next();
        System.out.println("Enter the release year:");
        final int releaseYear = scanner.nextInt();
        System.out.println("Rate a film (0-10)");
        int rating = scanner.nextInt();

        try {
            filmService.rateFilm(title, releaseYear, rating);
        } catch (Exception e) {
            System.out.println("Ups..something went wrong");
            System.out.println(e.getMessage());
        }
    }
}
