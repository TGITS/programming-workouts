param(
    [string]$CaseName = "sample"
)

$RootDir = Resolve-Path (Join-Path $PSScriptRoot "..")
$InputFile = Join-Path $RootDir ("tests/{0}.in" -f $CaseName)
$ExpectedFile = Join-Path $RootDir ("tests/{0}.out" -f $CaseName)

if (-not (Test-Path $InputFile)) {
    Write-Error "Input file not found: $InputFile"
    exit 2
}

if (-not (Test-Path $ExpectedFile)) {
    Write-Error "Expected file not found: $ExpectedFile"
    exit 2
}

jbang (Join-Path $RootDir "CodingameHarness.java") judge $InputFile $ExpectedFile (Join-Path $RootDir "MovesInMaze.java")
