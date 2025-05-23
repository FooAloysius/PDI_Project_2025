@REM USE FOR GIT AUTO COMMIT AND AUTO PUSH TO GITHUB REPOSITORY
@REM THIS CODE IS USING ABANG TO GENERATE FOR EASY WAYS TO MAKE COMMITMENT

@echo off
:: get current time and date：2025-05-23 14:35:12
for /f "tokens=1-4 delims=/-. " %%a in ("%date%") do (
    set YYYY=%%d
    set MM=%%b
    set DD=%%c
)
for /f "tokens=1-2 delims=:." %%a in ("%time%") do (
    set HH=%%a
    set Min=%%b
)

set TIMESTAMP=%YYYY%-%MM%-%DD% %HH%:%Min%

set COMMIT_MSG=Auto commit on %date% %time%
if not "%~1"=="%COMMIT_MSG%" set COMMIT_MSG=%~1 *commit at: %TIMESTAMP%

git add .
git commit -m "%COMMIT_MSG%"
git remote add origin https://github.com/FooAloysius/PDI_Project_2025.git
git push origin master