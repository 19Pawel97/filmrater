package filmrater.ui;

import filmrater.domain.Film;
import filmrater.domain.FilmService;

import java.util.List;
import java.util.Scanner;

public class GetRatingCase extends CaseHandler {

    public GetRatingCase(Scanner scanner, FilmService filmService) {
        super(6, "Find a rating of a film titled...", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Enter the title of the film:");
        final String title = scanner.next();
        System.out.println("Enter the release year:");
        final int releaseYear = scanner.nextInt();
        List<Film> filmsByReleaseYear = filmService.getFilmsByReleaseYear(releaseYear);
        if (filmsByReleaseYear.isEmpty()) {
            System.out.println("No film found in DB.");
            return;
        }
        final List<Film> filmsByTitle = filmService.getFilmsByTitle(title);
        if (filmsByTitle.size() > 0) {
            final double rating = filmService.getRating(title, releaseYear);
            System.out.print("The rating of the film is ");
            System.out.println(String.format("%.2f", rating));
        } else {
            System.out.println("No films found!");
        }
    }
}
