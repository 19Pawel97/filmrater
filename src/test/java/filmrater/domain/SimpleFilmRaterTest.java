package filmrater.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleFilmRaterTest {
    private SimpleFilmRater simpleFilmRater;

    @BeforeEach
    void setUp() {
        simpleFilmRater = new SimpleFilmRater();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10,10,20,10",
            "7,10,10,7.2",
            "10,7,10,9.8"

    })
    void shouldCalculateCurrentRating(double actualRating, int inputRating, int counter, double expectedRating) {
        // when
        Rating rating = simpleFilmRater.calculateRating(new Rating(actualRating, counter), inputRating);

        // then
        assertEquals(expectedRating, rating.getRating());
    }
}
