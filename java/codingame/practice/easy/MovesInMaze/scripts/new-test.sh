#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
FORCE=false

if [[ $# -lt 1 || $# -gt 2 ]]; then
  echo "Usage: ./scripts/new-test.sh <case-name> [--force]" >&2
  exit 2
fi

CASE_NAME="$1"
if [[ $# -eq 2 ]]; then
  if [[ "$2" == "--force" ]]; then
    FORCE=true
  else
    echo "Unknown option: $2" >&2
    echo "Usage: ./scripts/new-test.sh <case-name> [--force]" >&2
    exit 2
  fi
fi

if [[ -z "${CASE_NAME// }" ]]; then
  echo "case-name cannot be empty" >&2
  exit 2
fi

INPUT_FILE="${ROOT_DIR}/tests/${CASE_NAME}.in"
EXPECTED_FILE="${ROOT_DIR}/tests/${CASE_NAME}.out"

if { [[ -f "${INPUT_FILE}" ]] || [[ -f "${EXPECTED_FILE}" ]]; } && [[ "${FORCE}" != true ]]; then
  echo "Test files already exist. Use --force to overwrite." >&2
  exit 2
fi

cat > "${INPUT_FILE}" <<EOF
# Input for case '${CASE_NAME}'
# Replace this file content with CodinGame input data.

EOF

cat > "${EXPECTED_FILE}" <<EOF
# Expected output for case '${CASE_NAME}'
# Replace this file content with expected solver output.

EOF

echo "Created: ${INPUT_FILE}"
echo "Created: ${EXPECTED_FILE}"
