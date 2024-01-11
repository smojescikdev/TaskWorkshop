# TaskWorkshop
Simple task manager - Workshop

This simple Java console application, TaskManager, provides a basic task management system allowing users to add, remove, list tasks, and exit the program. Tasks are stored in a CSV file for persistence between program executions.

Options:

-ADD (Option 1): Allows users to add a new task by providing a description, due date, and importance. \n
-REMOVE (Option 2): Enables the removal of a task by selecting its index. \n
-LIST (Option 3): Displays the list of tasks. \n
-EXIT (Option 4): Exits the program. \n
Data Persistence:

Tasks are saved to and loaded from a CSV file (tasks.csv) in the resources directory.
Dependencies
This program utilizes the ConsoleColors class for colorful console output. Ensure that the necessary class is available in your project.

Note
Make sure to replace the existing tasks.csv file path in the loadTasksFromFile method with the appropriate path on your system.





