# This is test task for Ciklum Hybris Academy
Software requirements::
- Java 8 or higher
- HSQLDB, MySQL databases
- Eclipse IDE for Java Developers (not necessary)

How to run:
- Project can be imported into Eclipse IDE and run from it
- All applications and unit tests can be run from *.bat files inside [/bat](bat) folder as is
- It can be used already packaged *.jar files in [/standalone_jar](standalone_jar) for applications
____
## There are few main parts:
##### *TablesUpdater*
Application for cleaning tables and putting random test data into database
Supports: drop tables, create tables, insert products data, insert order data. All operations are independent.
##### *ShopApplication*
Main application. 
Supported operations can be seen via the "help" command.

User Interaction happen through command line.
After running program prompts command and parameters.

Supports also running application with command and parameters. For example: 
standalone_jar:
> java -jar ShopApplication.jar update_order --orderid 10 --productid 10 --quantity 10 

bat:  
> java -cp classes;lib/mysql-connector.jar;lib/hsqldb.jar apps/ShopApplication update_order --orderid 10 --productid 10 --quantity 10

For details see 'help' and  [/standalone_jar/runShopApplicationExamples.txt](standalone_jar/runShopApplicationExamples.txt)

Password for removing all products (except ordered): fghj

##### *JUnit tests*
Can be run from eclipse or from *.bat file
____
## The repository consist of some main folders and files:
+ ### local.properties
    configuration for connection to database (to run the application from the command line or from the Eclipse IDE)
+ ### standalone_jar 
    contains all files needed to run application packaged in jar file:
    - local.properties  
        configuration for connection to database (should be in the same folder as the jar file)
    - runShopApplicationExamples.txt  
        examples how to run ShopApplication with command
    - *.jar files and corresponding *.bat  
        files for running jar
+ ### dbSQL 
    contains exported MySQL file which can be used for importing queries for creating all needed tables
+ ### bat
    contains *.bat files for compiling and running tests and applications from command line without Eclipse IDE
    - RunTest.bat  
        for unit tests
    - BuildAppsClasses.bat  
        for compiling all *.java files into *.class files
    - ShopApplication.bat and TablesUpdater.bat  
        for compiling and running applications
+ ### lib
    - mysql-connector.jar  
        for using MySQL database
    - hsqldb.jar  
        for using HSQLDB database
    - org.hamcrest.core_1.3.0.v20180420-1519.jar and org.junit_4.13.0.v20200204-1500.jar  
        for running JUnit tests from command line
+ ### src
    source java files
