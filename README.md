This project is a Java-based application that manages a list of competitors. It provides a graphical user interface (GUI) for users to interact with the data.

## Features
    Import Competitors: The application allows users to import competitor data from CSV or TXT files. This is implemented in the ImportDataGUI method in the Manager class.

    Display Competitor Details: Users can view the details of a specific competitor by entering the competitor's number. This is implemented in the displayCompetitorDetails method in the Manager class.

    Add, Edit, and Remove Competitors: The application provides functionality to add, edit, and remove competitors. This is implemented in the CompetitorGUI class.

    Generate Report: The application can generate a report that includes a full details table, the highest scoring competitor, average score, and score frequency. This is implemented in the generateReport method in the Manager class.

## Running the Application
    To run the application, execute the main method in the Manager class.

## Project Structure
    The project is structured as follows:

    src/: Contains the Java source files.
    bin/: Contains the compiled Java class files.
    README.md: This file.

## Classes
    Manager: The main class that manages the application.
    Competitor: Represents a competitor.
    CompetitorGUI: Provides the graphical user interface for the application.
    CompetitorController: Handles the logic for managing competitors.
    CompetitorList: Manages a list of competitors.
    AmateurCompetitor and ProfessionalCompetitor: Represent specific types of competitors.
