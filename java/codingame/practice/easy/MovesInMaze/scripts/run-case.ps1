param(
    [string]$CaseName = "sample"
)

$RootDir = Resolve-Path (Join-Path $PSScriptRoot "..")
$InputFile = Join-Path $RootDir ("tests/{0}.in" -f $CaseName)

if (-not (Test-Path $InputFile)) {
    Write-Error "Input file not found: $InputFile"
    exit 2
}

jbang (Join-Path $RootDir "CodingameHarness.java") run $InputFile (Join-Path $RootDir "MovesInMaze.java")
