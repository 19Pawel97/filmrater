package filmrater.ui;

import filmrater.domain.Film;
import filmrater.domain.FilmService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class UserInterface {
    private final FilmService filmService;
    private final Scanner input;

    public void start() {
        boolean shouldContinue = true;
        System.out.println("Welcome to filmrater!");

        while (shouldContinue) {
            System.out.println("What do you want to do?");
            System.out.println("1. Add a film to the DB.");
            System.out.println("2. Get a film from DB.");
            System.out.println("3. Get all films titled...");
            final int selectedOption = input.nextInt();
            switch (selectedOption) {
                case 1:
                    addFilmCase();
                    break;
                case 2:
                    getFilmCase();
                    break;
                case 3:
                    getFilmsByTitleCase();
                    break;
            }
            shouldContinue = shouldContinue();
        }
    }

    private void getFilmsByTitleCase() {
        System.out.println("Enter the title of the film:");
        final String title = input.next();
        List<Film> filmsByTitle = filmService.getFilmsByTitle(title);
        System.out.println("Found films:");
        for (Film film : filmsByTitle) {
            System.out.println(film.getTitle());
        }

    }

    private void getFilmCase() {
        System.out.println("Enter the title of the film:");
        final String title = input.next();
        System.out.println("Enter the release year:");
        final int releaseYear = input.nextInt();
        filmService.getFilm(title,releaseYear)
                .ifPresentOrElse(
                        film -> System.out.println(title + " was found."),
                        () -> System.out.println(title + " not found")
                );
    }

    private void addFilmCase() {
        System.out.println("Enter the title of the film:");
        final String title = input.next();
        System.out.println("Enter the release year:");
        final int releaseYear = input.nextInt();
        try {
            filmService.addFilm(title, releaseYear);
            System.out.println(title + " from " + releaseYear + " added successfully");
        } catch (Exception e) {
            System.out.println("Ups... Something went wrong. " + e.getMessage());
        }
    }

    private boolean shouldContinue() {
        System.out.println("Do you want to continue: y - yes; other keys - no.");
        final String selectedOption = input.next();
        switch (selectedOption) {
            case "y":
                return true;
            default:
                return false;
        }
    }


}
