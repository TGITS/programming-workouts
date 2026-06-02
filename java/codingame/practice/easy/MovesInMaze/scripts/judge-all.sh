#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

jbang "${ROOT_DIR}/CodingameHarness.java" judge-all "${ROOT_DIR}/tests" "${ROOT_DIR}/MovesInMaze.java"
