module hello_world;
import std.stdio;

// Simply return the string "Hello, World!"

string hello() {
  return "Hello, World!";
}

unittest {
  assert(hello() == "Hello, World!");
}
