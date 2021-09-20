# This is test task for Ciklum Hybris Academy
##### *Important: Application use com.mysql.jdbc.Driver and should be connected with MySQL database*
____
## There are few applications:
##### *CheckDB* 
application for checking DB. Is connection Ok, are all tables exist
##### *GenerateData*
application for putting random test data into database
##### *ShopApplication*
main appiication
____
## The repository consist of some main folders and files:
+ ### local.properties
    configuration for connection to database (to run the application from the command line or from the Eclipse IDE)
+ ### folder standalone_jar 
    contains all files needed to run application without building:
    - local.properties
        configuration for connection to database
    - CheckDB.jar 
        jar application for checking DB. Is connection Ok, are all tables exist
    - runCheckDBJar.bat
        bat file for running CheckDB.jar
+ ### dbSQL 
    contains sql file which can be used for importing queries for creating all needed tables
+ ### bin
    contains bat files for building and running application from command line without Eclipse IDE
+ ### lib
    mysql-connector.jar for using com.mysql.jdbc.Driver 
+ ### src
    source java files
____

## Application User Manual
