defmodule BasicGraphTest do
  use ExUnit.Case
  doctest BasicGraph

  test "greets the world" do
    assert BasicGraph.hello() == :world
  end
end
