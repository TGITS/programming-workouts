# ExFunctions2

Exercise heavily inspired from _Programming Elixir_ by Dave Thomas, chapter 5 but directly with named function and proper unit test.

Create and run the functions that do the following:

* Write a function `fizzbuzzer` that takes three arguments. If the first two are zero, return “FizzBuzz.” If the first is zero, return “Fizz.” If the second is zero, return “Buzz.” Otherwise return the third argument. Do not use any language features that we haven’t yet covered in this book.

* The operator rem(a, b) returns the remainder after dividing a by b. Write a function `fizzbuzz` that takes a single integer (n) and calls the function in the previous exercise, passing it rem(n,3), rem(n,5), and n. Call it seven times with the arguments 10, 11, 12, and so on. You should get “Buzz, 11, Fizz, 13, 14, FizzBuzz, 16.”

## Installation

If [available in Hex](https://hex.pm/docs/publish), the package can be installed
by adding `ex_functions_2` to your list of dependencies in `mix.exs`:

```elixir
def deps do
  [
    {:ex_functions_2, "~> 0.1.0"}
  ]
end
```

Documentation can be generated with [ExDoc](https://github.com/elixir-lang/ex_doc)
and published on [HexDocs](https://hexdocs.pm). Once published, the docs can
be found at [https://hexdocs.pm/ex_functions_2](https://hexdocs.pm/ex_functions_2).
