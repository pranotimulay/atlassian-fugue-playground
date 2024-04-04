package tester;

/**
 * The FugueFundamentalsTester class is responsible for testing various functionalities provided by the Atlassian Fugue
 * library.
 *
 * @author Pranoti Mulay
 * @version 1.0
 * @since 2024-03-26
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import io.atlassian.fugue.Either;
import io.atlassian.fugue.Option;

public class FugueFundamentalTester {

    MyService service = new MyService();

    @Test
    public void testOptionOperation() {
        Option<String> undefOption = Option.none();
        assertFalse(undefOption.isDefined());
    }

    @Test
    public void testMapOperation() {
        Option<String> some = Option.some("value").map(String::toUpperCase);
        assertEquals("VALUE", some.get());
    }

    @Test
    public void testEitherOperation() {
        Either<Integer, String> either = Either.right(
                "right"); // We call right() if we want an Either containing the Right value
        assertTrue(either.isRight());
    }

    @Test
    public void testSomeOperation() {
        Either<String, Integer> insertResult = service.operation("INSERT");
        Either<String, Integer> updateResult = service.operation("UPDATE");

        insertResult.fold(left -> {
            System.out.println("left leg called!");
            assertEquals("Error occurred!", insertResult.left().get());
            return insertResult;
        }, right -> {
            System.out.println("right leg called!");
            assertEquals(200, updateResult.right().get());
            return updateResult;
        });

    }

}

class MyService {

    public Either<String, Integer> operation(String type) {
        switch (type) {
        case "INSERT":
            return Either.left("Error occurred!");
        case "UPDATE":
            return Either.right(200);
        default:
            return Either.left("Invalid operation");
        }
    }
}