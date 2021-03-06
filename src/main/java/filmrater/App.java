package filmrater;

import filmrater.domain.FilmService;
import filmrater.domain.SimpleFilmRater;
import filmrater.infrastructure.InMemoryFilmRepository;
import filmrater.ui.*;

import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        final FilmService filmService = new FilmService(new InMemoryFilmRepository(), new SimpleFilmRater());
        final Scanner scanner = new Scanner(System.in);
        final UserInterface userInterface = new UserInterface(
                filmService,
                scanner,
                Set.of(new GetFilmsByTitleCase(scanner, filmService),
                        new GetFilmCase(scanner, filmService),
                        new AddFilmCase(scanner, filmService),
                        new GetFilmsByReleaseYearCase(scanner, filmService),
                        new RateCase(scanner, filmService),
                        new GetRatingCase(scanner,filmService),
                        new GetFilmsRatedFromTo(scanner, filmService)));
        userInterface.start();
    }
}
