$RootDir = Resolve-Path (Join-Path $PSScriptRoot "..")

jbang (Join-Path $RootDir "CodingameHarness.java") judge-all (Join-Path $RootDir "tests") (Join-Path $RootDir "MovesInMaze.java")
