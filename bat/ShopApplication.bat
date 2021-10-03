ECHO OFF
call BuildAppsClasses.bat
java -cp classes;lib/mysql-connector.jar;lib/hsqldb.jar apps/ShopApplication
echo.
pause