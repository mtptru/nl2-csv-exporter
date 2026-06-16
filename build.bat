@echo off
rem ============================================================
rem  Builds NLCSV.jar from the Java sources in src\.
rem  Requires a JDK (for javac + jar). No external libraries.
rem ============================================================
setlocal enabledelayedexpansion

set "ROOT=%~dp0"
cd /d "%ROOT%"

rem --- Locate the JDK tools (prefer JAVA_HOME, fall back to PATH) ---
set "JAVAC=javac"
set "JAR=jar"
if defined JAVA_HOME (
  if exist "%JAVA_HOME%\bin\javac.exe" (
    set "JAVAC=%JAVA_HOME%\bin\javac.exe"
    set "JAR=%JAVA_HOME%\bin\jar.exe"
  )
)

"%JAVAC%" -version >nul 2>&1
if errorlevel 1 (
  echo [ERROR] Could not find "javac" ^(the Java compiler^).
  echo A JDK is required to build. Download one from https://adoptium.net/ then
  echo either add it to PATH or set the JAVA_HOME environment variable and re-run.
  exit /b 1
)

echo Cleaning build directory...
if exist build rmdir /s /q build
mkdir build

echo Compiling sources...
set "SOURCES="
for /r src %%f in (*.java) do set "SOURCES=!SOURCES! "%%f""
"%JAVAC%" -encoding UTF-8 -d build !SOURCES!
if errorlevel 1 (
  echo [ERROR] Compilation failed.
  exit /b 1
)

echo Copying assets...
xcopy /e /i /y src\assets build\assets >nul

echo Writing manifest...
> build\MANIFEST.MF echo Manifest-Version: 1.0
>> build\MANIFEST.MF echo Main-Class: com.buam.nlcsv.NLCSVWindow

echo Packaging NLCSV.jar...
"%JAR%" cfm NLCSV.jar build\MANIFEST.MF -C build com -C build assets
if errorlevel 1 (
  echo [ERROR] Packaging failed.
  exit /b 1
)

echo.
echo Build successful: "%ROOT%NLCSV.jar"
echo Run it with:  java -jar NLCSV.jar    ^(or double-click run.bat^)
endlocal
