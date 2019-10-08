# Cruise company system
Task #8
  
### Project description:

The Company has several **Ships**. Ships have **passenger capacity**, **route**, number of visited **ports**, duration of one **cruise**, **staff**. The **client** selects and pays for the cruise. Chooses **Excursions** upon arrival at the port for an additional fee. Ship **Administrator** indicates **bonuses** for passengers, given the **ticket class** (pool, gym, cinema, beauty salons ...).




### Project Launch Instructions

1. The computer must have MySQL Server installed. Login and Password must be root and root.

2. You need to create a "cruisecompanyspring" database structure, for this, if you have Windows, run "DB_Initialization\1_createSchema.bat". If you have another operating system, from the console or using Workbench, execute the sql query, which is located in the "DB_Initialization\schema" file.

3. Initialization of the database for working with the program:
	3.1 If you want to get a clean database initialized for the program to work, you need to fill in 2 tables: "user" and "ticketclass". To do this, from Windows, run the "DB_Initialization\2_fill_userRolesAndTicketClasses.bat" file. If you have another operating system, from the console or using Workbench, execute the sql query, which is located in the "DB_Initialization\fill_userRolesAndTicketClasses" file.
	3.2 If you want to get a database filled with test data, then in Windows, run "DB_Initialization\3_fill_AllData.bat" or run the sql query, which is located in the "DB_Initialization\fill_AllData" file. "admin", "manager" and "manager2" have password "123".
  
4. Use Maven to build the project. While in the root folder of the project, first run the command "mvn clean", and then "mvn package". After successful assembly, you will see the "target" folder with all the necessary files.

5. To start the project, you need to deploy it on the Tomcat server. The context path must be "/". If the path is different, then it will be necessary to make changes to the project. Suppose if the context path is "/cruise/", then in the file "src\main\java\ua\study\poject\cruise\util\StringStorage.java" the constant String CONTROLLER = "/controller" will need to be changed to String CONTROLLER = "/cruise/controller"

6. To stop the project, shutdown the Tomcat server.
