@echo off
echo ===================================================
echo   KHOI CHAY UNG DUNG QLST (TOMCAT 9 EMBEDDED)
echo ===================================================
cd /d "%~dp0"
"C:\Program Files\apache-maven-3.10.0\bin\mvn.cmd" clean compile exec:java -Dexec.mainClass="Main"
pause

