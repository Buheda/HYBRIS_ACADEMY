ECHO OFF
cd ..\
@javac -d classes src\entity\*.java
@javac -d classes .\src\*.java
java -cp classes;lib/mysql-connector.jar CheckDB
echo.
pause