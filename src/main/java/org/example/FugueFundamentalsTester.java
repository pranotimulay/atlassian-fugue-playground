package org.example;

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

public class FugueFundamentalsTester {

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

}