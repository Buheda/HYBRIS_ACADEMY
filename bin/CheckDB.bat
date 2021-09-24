ECHO OFF
cd ..
javac -d classes src/db/*.java src/db/creator/*.java src/dbApp/*.java
java -cp classes;lib/mysql-connector.jar;lib/hsqldb.jar dbApp/CheckDB

echo.
pause