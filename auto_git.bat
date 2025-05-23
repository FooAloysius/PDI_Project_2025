@echo off
set COMMIT_MSG=Auto commit on %date% %time%
if not "%~1"=="" set COMMIT_MSG=%~1

git add .
git commit -m "%COMMIT_MSG%"
git remote add https://github.com/FooAloysius/PDI_Project_2025.git
git push origin main