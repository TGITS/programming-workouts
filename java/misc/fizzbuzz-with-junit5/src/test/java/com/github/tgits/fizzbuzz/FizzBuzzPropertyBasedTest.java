package com.github.tgits.fizzbuzz;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;

public class FizzBuzzPropertyBasedTest {

    @Property
    boolean everyStrictlyPositiveIntegerDivisibleBy3StartsWithFizz(@ForAll("strictlyPositiveAndDivisibleBy3") int i) {
        return new FizzBuzz().execute(i).startsWith(FizzBuzz.FIZZ);
    }

    @Provide
    Arbitrary<Integer> strictlyPositiveAndDivisibleBy3() {
        return Arbitraries.integers().greaterOrEqual(1).filter(i -> i % 3 == 0);
    }

    @Property
    boolean everyStrictlyPositiveIntegerDivisibleBy5EndssWithBuzz(@ForAll("strictlyPositiveAndDivisibleBy5") int i) {
        return new FizzBuzz().execute(i).endsWith(FizzBuzz.BUZZ);
    }

    @Provide
    Arbitrary<Integer> strictlyPositiveAndDivisibleBy5() {
        return Arbitraries.integers().greaterOrEqual(1).filter(i -> i % 5 == 0);
    }

    @Property
    boolean everyStrictlyPositiveIntegerDivisibleBy3And5MatchesExactlyWithFizzBuzz(@ForAll("strictlyPositiveAndDivisibleBy3And5") int i) {
        return new FizzBuzz().execute(i).endsWith(FizzBuzz.FIZZ_BUZZ);
    }

    @Provide
    Arbitrary<Integer> strictlyPositiveAndDivisibleBy3And5() {
        return Arbitraries.integers().greaterOrEqual(1).filter(i -> i % 3 == 0 && i % 5 == 0);
    }

    @Property
    boolean everyStrictlyPositiveIntegerNotDivisibleBy3Or5IsOnlyConvertedToString(@ForAll("strictlyPositiveAndNotDivisibleBy3Or5") int i) {
        return new FizzBuzz().execute(i).endsWith(Integer.toString(i));
    }

    @Provide
    Arbitrary<Integer> strictlyPositiveAndNotDivisibleBy3Or5() {
        return Arbitraries.integers().greaterOrEqual(1).filter(i -> i % 3 != 0 && i % 5 != 0);
    }

    @Property
    void everyIntegerEqualsOrLessThan0ShouldRaisedAnIllegalArgumentException(@ForAll("equalOrLessThan0") int i) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FizzBuzz().execute(i));
    }

    @Provide
    Arbitrary<Integer> equalOrLessThan0() {
        return Arbitraries.integers().lessOrEqual(0);
    }
}
