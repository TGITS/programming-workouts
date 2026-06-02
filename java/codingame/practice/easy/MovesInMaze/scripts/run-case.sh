#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CASE_NAME="${1:-sample}"
INPUT_FILE="${ROOT_DIR}/tests/${CASE_NAME}.in"

if [[ ! -f "${INPUT_FILE}" ]]; then
  echo "Input file not found: ${INPUT_FILE}" >&2
  exit 2
fi

jbang "${ROOT_DIR}/CodingameHarness.java" run "${INPUT_FILE}" "${ROOT_DIR}/MovesInMaze.java"
