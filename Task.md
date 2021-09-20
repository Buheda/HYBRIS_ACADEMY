Non-Functional Requirements
1. Minimum Java version is 8 or higher
2. User Interaction happen through command line
3. Database MySQL or HSQLDB
4. Configuration file in local.properties where connection to database should be stored
5. Classes should be covered by UnitTests (Junit, Mockito)
6. README.md file should be present. It should contain all the necessary steps on
how to start application and run the tests
7. Application should show all possible ways how to interact with application on startup


Functional Requirements
Application should provide such operations:
1. Create Product
2. Create Order with a list of the products specified by id. User Id should be auto generated
3. Update Order Entries quantities. During order creation Order Entries should be created
in background
4. Show following views:
a. | Product Name | Product Price | Product Status | for all products
b. List all products, which have been ordered at least once, with total ordered
quantity sorted descending by the quantity. Please implement it using SQL
query instead of doing calculation in Java
c. | Order ID | Products total Price | Product Name | Products Quantity in orderEntry
| Order Created Date [YYYY-MM-DD HH:MM ] | by order Id
d. List all orders using previous view
5. Remove product by ID / Remove all products only if you enter a password