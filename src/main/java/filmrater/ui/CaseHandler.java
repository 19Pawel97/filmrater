package filmrater.ui;

import filmrater.domain.FilmService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Scanner;

@AllArgsConstructor
@Getter
public abstract class CaseHandler {
    private final int id;
    private String description;
    protected final Scanner scanner;
    protected final FilmService filmService;

    abstract void handle();
}
