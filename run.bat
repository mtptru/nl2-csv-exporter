@echo off
rem ============================================================
rem  Runs NLCSV.jar. Builds it first if it does not exist yet.
rem  Running only needs a JRE; building needs a JDK.
rem ============================================================
setlocal
cd /d "%~dp0"

if not exist NLCSV.jar (
  echo NLCSV.jar not found - building it first...
  call "%~dp0build.bat"
  if errorlevel 1 exit /b 1
)

java -version >nul 2>&1
if errorlevel 1 (
  echo [ERROR] Could not find "java". Install Java from https://adoptium.net/ and try again.
  exit /b 1
)

start "" javaw -jar NLCSV.jar
endlocal
