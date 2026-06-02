param(
    [Parameter(Mandatory = $true)]
    [string]$CaseName,
    [switch]$Force
)

$RootDir = Resolve-Path (Join-Path $PSScriptRoot "..")
$InputFile = Join-Path $RootDir ("tests/{0}.in" -f $CaseName)
$ExpectedFile = Join-Path $RootDir ("tests/{0}.out" -f $CaseName)

if (-not $CaseName.Trim()) {
    Write-Error "CaseName cannot be empty."
    exit 2
}

if (((Test-Path $InputFile) -or (Test-Path $ExpectedFile)) -and -not $Force) {
    Write-Error "Test files already exist. Use -Force to overwrite."
    exit 2
}

$inputTemplate = @"
# Input for case '$CaseName'
# Replace this file content with CodinGame input data.

"@

$expectedTemplate = @"
# Expected output for case '$CaseName'
# Replace this file content with expected solver output.

"@

Set-Content -Path $InputFile -Value $inputTemplate -NoNewline:$false
Set-Content -Path $ExpectedFile -Value $expectedTemplate -NoNewline:$false

Write-Host "Created:" $InputFile
Write-Host "Created:" $ExpectedFile
