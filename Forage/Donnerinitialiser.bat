@echo off
chcp 65001 > nul
echo ╔════════════════════════════════════════════════════════════╗
echo ║         IMPORTATION DES DONNÉES DE TEST                    ║
echo ╚════════════════════════════════════════════════════════════╝
echo.

REM Chemin MariaDB dans XAMPP (modifiez si nécessaire)
set MYSQL_PATH=C:\xampp\mysql\bin\mysql.exe

REM Dossier contenant les fichiers SQL
set SQL_DIR=%~dp0sql

REM Vérifier si MySQL existe
if not exist "%MYSQL_PATH%" (
    echo [ERREUR] MySQL non trouve a : %MYSQL_PATH%
    echo Veuillez modifier le chemin dans ce fichier.
    pause
    exit /b 1
)

REM Vérifier si le dossier sql existe
if not exist "%SQL_DIR%" (
    echo [ERREUR] Dossier sql non trouve : %SQL_DIR%
    echo Veuillez creer le dossier sql avec les fichiers .sql
    pause
    exit /b 1
)

REM Importer les fichiers SQL
mysql -u root < "%SQL_DIR%\DonnerEntre.sql"

echo.
echo Importation terminee avec succes !
pause