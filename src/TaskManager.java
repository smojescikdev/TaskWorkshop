package src;

import pl.coderslab.ConsoleColors;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class TaskManager {
    static ArrayList<String[]> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile("C:\\Users\\Mojescik\\IdeaProjects\\TaskWorkshop\\resources\\tasks.csv");
        startMenu();
    }

    public static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        int menu;

        do {
            System.out.println(ConsoleColors.BLUE + "//---Please select an option:---//" + ConsoleColors.RESET);
            System.out.println(" 1: ADD \n 2: REMOVE \n 3: LIST \n 4: EXIT");

            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    System.out.println("You chose the first option --ADD--");
                    addTask();
                    break;
                case 2:
                    System.out.println("You chose the second option --REMOVE--");
                    removeTask();
                    break;
                case 3:
                    System.out.println("You chose the third option --LIST--");
                    listTasks();
                    break;
                case 4:
                    System.out.println("You chose the fourth option --EXIT--");
                    System.out.println(ConsoleColors.RED + "Bye bye " + ConsoleColors.RESET);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Please select a correct option." + ConsoleColors.RESET);
            }
        } while (menu != 4);
        scanner.close();
    }

    private static void addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description: ");
        String description = scanner.nextLine();

        System.out.println("Please add task due date: ");
        String dueDate = scanner.nextLine();

        System.out.println("Is your task important: true/false ");
        String important = scanner.nextLine();

        String[] newTask = {description, dueDate, important};
        tasks.add(newTask);

        saveTasksToFile("tasks.csv");

        System.out.println(" Task saved ");
    }

    public static void saveTasksToFile(String filename) {
        try (PrintWriter output = new PrintWriter(new FileWriter(filename))) {
            for (String[] task : tasks) {
                output.println(String.join(",", task));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadTasksFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println(" File not found ");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] elements = line.split(",");
                tasks.add(elements);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" Nothing to list. ");
            return;
        }

        for (String[] task : tasks) {
            System.out.println(ConsoleColors.GREEN_BOLD + Arrays.toString(task) + "\n");
        }
    }

    private static void removeTask() {
        if (tasks.isEmpty()) {
            System.out.println("Nothing to delete");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int taskIndex;
        boolean validInput = false;

        do {
            try {
                System.out.println("Select an index of a task to remove (0 - " + (tasks.size() - 1) + "): ");
                taskIndex = scanner.nextInt();
                scanner.nextLine(); // Skonsumuj znak nowej linii po nextInt()

                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    validInput = true;
                    tasks.remove(taskIndex);
                    saveTasksToFile("tasks.csv");
                    System.out.println("Task removed successful");
                } else {
                    System.out.println("You selected wrong index of the task. Try again!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong format. Write a number!");
                scanner.next();
            }
        } while (!validInput);
    }

}