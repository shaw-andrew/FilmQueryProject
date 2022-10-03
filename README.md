# FilmQueryProject

# Project Description
This is a command line application that pulls information about films from a database.  The user can either search for a movie by ID number or search using a keyword referencing the title/description of the film.

This project was aimed at getting me familiar with the Java/SQL interface and utilizing Maven to help connect the two. 


# Class Description
First, there are Film and Actor classes with fields, constructors, getters, setters, equals and to strings. I then have an abstract class for my Database Accessor Object (DAO) to outline required methods, and a DAO concrete class. This class is where all the methods that interact with the database live. Each method needs to establish a connection with the database, login, execute a query and generate objects based off the results. Finally, there is the Film Query App. The App holds the menu and processes the user input, calling the methods that will result in the desired output. 

# What I Learned
This project introduced new technologies to me. It forced me to be more comfortable with SQL querys and how Java interfaces with a database. This was a welcomed return to Java after our OCA exam prep week. This project also introduced Maven and how to install the drivers and connections needed to allow Java and SQL to communicate with each other. 

# Technologies Used
Java, Git, Maven, mySQL

# How to run
This program is designed to run in the IDE. Ensure MySQL port is assigned to 3306. Update the Maven file in the Java Package if there are issues.