ECHO OFF
cd ..
javac -d classes src/db/*.java src/db/queries/*.java src/shopApp/dao/*.java src/shopApp/entity/*.java src/shopApp/*.java

java -cp classes;lib/mysql-connector.jar;lib/hsqldb.jar shopApp/ShopApplication
echo.
pause