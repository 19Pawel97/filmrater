package filmrater.ui;

import filmrater.domain.FilmService;
import lombok.AllArgsConstructor;

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
            final int selectedOption = input.nextInt();
            switch (selectedOption) {
                case 1:
                    addFilmCase();
                    break;
            }
            shouldContinue = shouldContinue();
        }
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

    private boolean shouldContinue(){
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
