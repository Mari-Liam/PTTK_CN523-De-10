@echo off
echo ===================================================
echo   KHOI DONG MYSQL VOI DOCKER COMPOSE
echo ===================================================
cd /d "%~dp0"
docker compose up -d
echo.
echo Database da san sang tai localhost:3306!
echo Quan ly CSDL truc quan (phpMyAdmin): http://localhost:8081
pause

