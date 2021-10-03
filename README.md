# This is test task for Ciklum Hybris Academy
Software requirements::
- Java 8 or higher
- HSQLDB, MySQL databases
- JUnit4
- Eclipse IDE for Java Developers (not necessary)

How to run:
- Project can be imported into Eclipse IDE and run from it
- Just run *.bat files from bat folder as is
- It can be used already packaged jar files in standalone_jar folder
____
## There are few applications:
##### *TablesUpdater*
application for cleaning tables and putting random test data into database
##### *ShopApplication*
main appiication
____
## The repository consist of some main folders and files:
+ ### local.properties
    configuration for connection to database (to run the application from the command line or from the Eclipse IDE)
+ ### standalone_jar 
    contains all files needed to run application without building:
    - local.properties
        configuration for connection to database (should be in the same folder as the jar file)
    - runTablesUpdater.bat
        bat file for running TablesUpdater.jar (should be in the same folder as the jar file)
+ ### dbSQL 
    contains exported sql file which can be used for importing queries for creating all needed tables
+ ### bat
    contains *.bat files for building and running tests and applications from command line without Eclipse IDE
    - RunTest.bat bat file for running unit tests
    - BuildAppsClasses.bat file for compile all *.java files into *.class files
    - ShopApplication.bat and TablesUpdater.bat files for running applications
+ ### lib
    - mysql-connector.jar for using MySQL database
    - hsqldb.jar  for using HSQLDB database
    - org.hamcrest.core_1.3.0.v20180420-1519.jar and org.junit_4.13.0.v20200204-1500.jar for running JUnit tests from command line
+ ### src
    source java files
