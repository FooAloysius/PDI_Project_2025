@echo off
set COMMIT_MSG=Auto commit on %date% %time%
if not "%~1"=="" set COMMIT_MSG=%~1

git add .
git commit -m "%COMMIT_MSG%"
git push origin main