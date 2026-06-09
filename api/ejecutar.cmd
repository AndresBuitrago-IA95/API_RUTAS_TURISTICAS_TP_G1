@echo off
REM Script para ejecutar la aplicación Spring Boot sin Maven instalado

echo ================================================
echo EJECUTANDO API DE RUTAS TURISTICAS
echo ================================================
echo.

REM Configurar JAVA_HOME temporalmente
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot
set PATH=%JAVA_HOME%\bin;%PATH%

REM Verificar Java
echo Verificando Java...
java -version
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java no esta configurado correctamente
    pause
    exit /b 1
)

echo.
echo Compilando y ejecutando la aplicacion...
echo.

REM Ejecutar con Maven Wrapper
mvnw.cmd spring-boot:run

pause
