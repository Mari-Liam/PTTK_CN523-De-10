@echo off
echo ===================================================
echo   CHAY KIEM THU DON VI JUNIT
echo ===================================================
cd /d "%~dp0"
"C:\Program Files\apache-maven-3.10.0\bin\mvn.cmd" test
pause

