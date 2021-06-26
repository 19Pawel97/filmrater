package filmrater;

import filmrater.domain.FilmService;
import filmrater.infrastructure.InMemoryFilmRepository;
import filmrater.ui.AddFilmCase;
import filmrater.ui.GetFilmCase;
import filmrater.ui.GetFilmsByTitleCase;
import filmrater.ui.UserInterface;

import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        final FilmService filmService = new FilmService(new InMemoryFilmRepository());
        final Scanner scanner = new Scanner(System.in);
        final UserInterface userInterface = new UserInterface(
                filmService,
                scanner,
                Set.of(new GetFilmsByTitleCase(scanner, filmService),
                        new GetFilmCase(scanner, filmService),
                        new AddFilmCase(scanner, filmService)));
        userInterface.start();
    }
}
