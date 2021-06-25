package filmrater;

import filmrater.domain.FilmService;
import filmrater.infrastructure.InMemoryFilmRepository;
import filmrater.ui.UserInterface;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        final FilmService filmService = new FilmService(new InMemoryFilmRepository());
        final Scanner scanner = new Scanner(System.in);
        final UserInterface userInterface = new UserInterface(filmService, scanner);
        userInterface.start();
    }
}
