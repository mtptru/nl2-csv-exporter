#!/usr/bin/env sh
# ============================================================
#  Builds NLCSV.jar from the Java sources in src/.
#  Requires a JDK (for javac + jar). No external libraries.
# ============================================================
set -e

ROOT="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT"

# --- Locate the JDK tools (prefer JAVA_HOME, fall back to PATH) ---
JAVAC="javac"
JAR="jar"
if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/javac" ]; then
  JAVAC="$JAVA_HOME/bin/javac"
  JAR="$JAVA_HOME/bin/jar"
fi

if ! "$JAVAC" -version >/dev/null 2>&1; then
  echo "[ERROR] Could not find javac (the Java compiler)."
  echo "A JDK is required to build. Install one from https://adoptium.net/ then"
  echo "ensure javac is on PATH or set the JAVA_HOME environment variable."
  exit 1
fi

echo "Cleaning build directory..."
rm -rf build
mkdir -p build

echo "Compiling sources..."
find src -name '*.java' > build/sources.txt
"$JAVAC" -encoding UTF-8 -d build @build/sources.txt

echo "Copying assets..."
cp -r src/assets build/assets

echo "Writing manifest..."
printf 'Manifest-Version: 1.0\nMain-Class: com.buam.nlcsv.NLCSVWindow\n' > build/MANIFEST.MF

echo "Packaging NLCSV.jar..."
"$JAR" cfm NLCSV.jar build/MANIFEST.MF -C build com -C build assets

echo ""
echo "Build successful: $ROOT/NLCSV.jar"
echo "Run it with:  java -jar NLCSV.jar"
