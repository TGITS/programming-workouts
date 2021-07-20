defmodule ExFunctions2Test do
  use ExUnit.Case
  doctest ExFunctions2

  test "The first 2 parameters given to fizzbuzzer are 0" do
    assert ExFunctions2.fizzbuzzer(0, 0, 15) == "FizzBuzz."
  end

  test "The first parameter given to fizzbuzzer is 0" do
    assert ExFunctions2.fizzbuzzer(0, 3, 3) == "Fizz."
  end

  test "The second parameter given to fizzbuzzer is 0" do
    assert ExFunctions2.fizzbuzzer(2, 0, 5) == "Buzz."
  end

  test "The first 2 parameters given to fizzbuzzer are not 0" do
    assert ExFunctions2.fizzbuzzer(1, 2, 7) == "7"
  end

  test "For a multiple of 3 and 5 FizzBuzz. should be outputed" do
    assert ExFunctions2.fizzbuzz(15) == "FizzBuzz."
    assert ExFunctions2.fizzbuzz(30) == "FizzBuzz."
    assert ExFunctions2.fizzbuzz(45) == "FizzBuzz."
    assert ExFunctions2.fizzbuzz(90) == "FizzBuzz."
    assert ExFunctions2.fizzbuzz(105) == "FizzBuzz."
  end

  test "For a multiple of 3 not also multiple of 5 Fizz. should be outputed" do
    assert ExFunctions2.fizzbuzz(3) == "Fizz."
    assert ExFunctions2.fizzbuzz(6) == "Fizz."
    assert ExFunctions2.fizzbuzz(9) == "Fizz."
    assert ExFunctions2.fizzbuzz(18) == "Fizz."
    assert ExFunctions2.fizzbuzz(12) == "Fizz."
    assert ExFunctions2.fizzbuzz(54) == "Fizz."
    assert ExFunctions2.fizzbuzz(72) == "Fizz."
  end

  test "For a multiple of 5 not also multiple of 3 Buzz. should be outputed" do
    assert ExFunctions2.fizzbuzz(5) == "Buzz."
    assert ExFunctions2.fizzbuzz(10) == "Buzz."
    assert ExFunctions2.fizzbuzz(50) == "Buzz."
    assert ExFunctions2.fizzbuzz(100) == "Buzz."
  end

  test "For a number not multiple of 5 or 3, the same number as a string should be outputed" do
    assert ExFunctions2.fizzbuzz(1) == "1"
    assert ExFunctions2.fizzbuzz(2) == "2"
    assert ExFunctions2.fizzbuzz(4) == "4"
    assert ExFunctions2.fizzbuzz(7) == "7"
    assert ExFunctions2.fizzbuzz(49) == "49"
    assert ExFunctions2.fizzbuzz(101) == "101"
  end
end
