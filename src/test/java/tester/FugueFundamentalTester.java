package tester;

/**
 * The FugueFundamentalsTester class is responsible for testing various functionalities provided by the Atlassian Fugue
 * library.
 *
 * @author Pranoti Mulay
 * @version 1.0
 * @since 2024-03-26
 */

import static io.atlassian.fugue.Option.none;
import static io.atlassian.fugue.Option.some;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import io.atlassian.fugue.Either;
import io.atlassian.fugue.Option;

public class FugueFundamentalTester {

    MyService service = new MyService();

    @Test
    void testOptionOperation() {
        Option<String> undefOption = Option.none();
        assertFalse(undefOption.isDefined());
    }

    @Test
    void testMapOperation() {
        Option<String> some = Option.some("value").map(String::toUpperCase);
        assertEquals("VALUE", some.get());
    }

    @Test
    void testEitherOperation() {
        Either<Integer, String> either = Either.right(
                "right"); // We call right() if we want an Either containing the Right value
        assertTrue(either.isRight());
    }

    @Test
    void testSomeOperation() {
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

    @Test
    public void testEitherLeft() {
        Either<String, Integer> result = Either.left("Error");
        Assert.assertTrue(result.isLeft());
        Assert.assertEquals("Error", result.left().get());
    }

    @Test
    public void testEitherRight() {
        Either<String, Integer> result = Either.right(5);
        Assert.assertTrue(result.isRight());
        Assert.assertEquals(Integer.valueOf(5), result.right().get());
    }

    @Test
    public void testEitherMap() {
        Either<String, Integer> success = Either.right(5);
        Either<String, Integer> doubled = success.map(n -> n * 2);
        Assert.assertEquals(Integer.valueOf(10), doubled.right().get());
    }

    @Test
    public void testOptionSome() {
        Option<Integer> maybeNumber = some(5);
        Assert.assertTrue(maybeNumber.isDefined());
        Assert.assertEquals(Integer.valueOf(5), maybeNumber.get());
    }

    @Test
    public void testOptionNone() {
        Option<Integer> maybeNumber = none();
        Assert.assertFalse(maybeNumber.isDefined());
    }

    @Test
    public void testOptionMap() {
        Option<Integer> maybeNumber = some(5);
        Option<Integer> doubled = maybeNumber.map(n -> n * 2);
        Assert.assertEquals(Integer.valueOf(10), doubled.get());
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