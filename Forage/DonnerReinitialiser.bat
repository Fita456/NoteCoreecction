
@echo off
chcp 65001 > nul
echo ╔════════════════════════════════════════════════════════════╗
echo ║         SUPPRESSION DES DONNÉES (TABLES VIDÉES)            ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

REM Chemin MariaDB dans XAMPP
set MYSQL_PATH=C:\xampp\mysql\bin\mysql.exe

REM Dossier SQL
set SQL_DIR=%~dp0sql

REM Vérifications
if not exist "%MYSQL_PATH%" (
    echo [ERREUR] MySQL non trouve a : %MYSQL_PATH%
    pause
    exit /b 1
)

if not exist "%SQL_DIR%\DonnerSuprimer.sql" (
    echo [ERREUR] Fichier SQL non trouve : %SQL_DIR%\DonnerSuprimer.sql
    pause
    exit /b 1
)



set /p confirm="Etes-vous sur de vouloir continuer ? (O/N) : "

if /i "%confirm%"=="O" goto SUPPRIMER
if /i "%confirm%"=="OUI" goto SUPPRIMER

echo.
echo Operation annulee.
pause
exit /b 0

:SUPPRIMER
echo.
echo Suppression des donnees en cours...
echo.

"%MYSQL_PATH%" -u root < "%SQL_DIR%\DonnerSuprimer.sql"

if %ERRORLEVEL% NEQ 0 (
    echo [ERREUR] Echec de la suppression
    pause
    exit /b 1
)

echo.
echo ════════════════════════════════════════════════════════════════
echo   SUPPRESSION TERMINEE
echo ════════════════════════════════════════════════════════════════
echo.
echo   Toutes les donnees ont ete supprimees.
echo   Les tables et la structure de la base sont conservees.
echo.
echo   Pour reimporter des donnees : import_donnees.bat
echo   Pour recreer la base complete : reset_database.bat
echo.
pause
exit /b 0