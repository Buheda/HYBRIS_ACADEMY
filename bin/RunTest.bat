ECHO OFF
cd ..
javac -d classes src/core/util/*.java src/core/persistent/*.java src/core/db/*.java src/core/db/entity/*.java src/core/db/dao/*.java src/core/commands/*.java src/core/commands/listedArguments/*.java src/core/commands/mappedArguments/*.java src/core/commands/noArguments/*.java src/dbApp/tablesManager/*.java src/apps/*.java 
java -cp "classes;lib/mysql-connector.jar;lib/hsqldb.jar;lib/org.junit_4.13.0.v20200204-1500.jar;lib/org.hamcrest.core_1.3.0.v20180420-1519.jar;." org.junit.runner.JUnitCore tests.core.AllTests
echo.
pause