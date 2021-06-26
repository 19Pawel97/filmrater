package filmrater.ui;

import filmrater.domain.Film;
import filmrater.domain.FilmService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@AllArgsConstructor
public class UserInterface {
    private final FilmService filmService;
    private final Scanner input;
    private final Set<CaseHandler> caseHandlers;

    public void start() {
        boolean shouldContinue = true;
        System.out.println("Welcome to filmrater!");

        while (shouldContinue) {
            System.out.println("What do you want to do?");
            caseHandlers.forEach(caseHandler -> System.out.println(caseHandler.getId() + ". " + caseHandler.getDescription()));
            final int selectedOption = input.nextInt();
            caseHandlers.stream()
                    .filter(caseHandler -> caseHandler.getId() == selectedOption)
                    .findFirst()
                    .ifPresentOrElse(CaseHandler::handle, () -> System.out.println("Wrong option!"));
            shouldContinue = shouldContinue();
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
