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
rm -rf "$ROOT_DIR/build"
mkdir -p "$ROOT_DIR/build/WEB-INF/classes"
find "$ROOT_DIR/src/main/java" -name "*.java" > "$ROOT_DIR/build/sources.txt"
javac -encoding UTF-8 -cp "$SERVLET_JAR" -d "$ROOT_DIR/build/WEB-INF/classes" @"$ROOT_DIR/build/sources.txt"
cp -R "$ROOT_DIR/src/main/webapp"/* "$ROOT_DIR/build/"
mkdir -p "$ROOT_DIR/build/data"
cp -R "$ROOT_DIR/data"/* "$ROOT_DIR/build/data/" 2>/dev/null || true
(cd "$ROOT_DIR/build" && jar -cf ta-recruitment-web-final.war .)
echo "WAR created: $ROOT_DIR/build/ta-recruitment-web-final.war"
