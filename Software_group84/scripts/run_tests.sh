#!/usr/bin/env bash
set -euo pipefail
ROOT_DIR="$(cd "$(dirname "$0")/.." && pwd)"
SERVLET_JAR="${SERVLET_API_JAR:-}"
if [ -z "$SERVLET_JAR" ]; then
  for c in /usr/share/java/servlet-api.jar /usr/share/java/servlet-api-4.0.1.jar /usr/share/maven-repo/javax/servlet/javax.servlet-api/4.0.1/javax.servlet-api-4.0.1.jar; do
    if [ -f "$c" ]; then SERVLET_JAR="$c"; break; fi
  done
fi
if [ -z "$SERVLET_JAR" ] || [ ! -f "$SERVLET_JAR" ]; then
  echo "Cannot find servlet-api jar. Set SERVLET_API_JAR=/path/to/servlet-api.jar" >&2
  exit 1
fi
rm -rf "$ROOT_DIR/build/test-classes"
mkdir -p "$ROOT_DIR/build/test-classes"
find "$ROOT_DIR/src/main/java" "$ROOT_DIR/src/test/java" -name "*.java" > "$ROOT_DIR/build/test-sources.txt"
javac -encoding UTF-8 -cp "$SERVLET_JAR" -d "$ROOT_DIR/build/test-classes" @"$ROOT_DIR/build/test-sources.txt"
java -ea -cp "$ROOT_DIR/build/test-classes:$SERVLET_JAR" cn.bupt.tarecruitment.CoreLogicTest
echo "All tests passed."
