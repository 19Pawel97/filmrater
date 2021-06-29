package filmrater.ui;

import filmrater.domain.Film;
import filmrater.domain.FilmService;

import java.util.List;
import java.util.Scanner;

public class GetFilmsRatedFromTo extends CaseHandler{


    public GetFilmsRatedFromTo(Scanner scanner, FilmService filmService) {
        super(7, "Find all films rated between...", scanner, filmService);
    }

    @Override
    void handle() {
        System.out.println("Enter the min. rating:");
        final double min = scanner.nextDouble();
        System.out.println("Enter the max. rating:");
        final double max = scanner.nextDouble();
        System.out.println(String.format("The films rated between %f and %f are:", min, max));
        List<Film> filmsRatedBetween = filmService.getFilmsRatedBetween(min, max);
        for (Film film : filmsRatedBetween) {
            System.out.println(film.getTitle() + " from " + film.getReleaseYear() + " with the rating of " + film.getRating().getRating());
        }

    }
}
